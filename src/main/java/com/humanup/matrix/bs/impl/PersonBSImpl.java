package com.humanup.matrix.bs.impl;

import com.humanup.matrix.bs.PersonBS;
import com.humanup.matrix.dao.PersonDAO;
import com.humanup.matrix.dao.ProfileDAO;
import com.humanup.matrix.dao.SkillDAO;
import com.humanup.matrix.dao.TypeSkillsDAO;
import com.humanup.matrix.dao.entities.Person;
import com.humanup.matrix.dao.entities.Profile;
import com.humanup.matrix.dao.entities.Skill;
import com.humanup.matrix.dao.entities.TypeSkills;
import com.humanup.matrix.exceptions.ProfileException;
import com.humanup.matrix.vo.PersonVO;
import com.humanup.matrix.vo.SkillVO;
import com.humanup.matrix.vo.TypeSkillsVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PersonBSImpl implements PersonBS {

  @Autowired
  private PersonDAO personDAO;

  @Autowired
  private ProfileDAO profileDAO;

  @Autowired
  private SkillDAO skillDAO;

  @Override
  @Transactional(rollbackFor = ProfileException.class)
  public boolean createPerson(PersonVO personVO) throws ProfileException {
    Profile profile = profileDAO.findByProfileTitle(personVO.getProfile());
    String email =  personVO.getMailAdresses();

    if(null == profile || null == email || StringUtils.isEmpty(email)){
      throw new ProfileException();
    }
    Person personToSave =new Person.Builder()
          .setFirstName(personVO.getFirstName())
          .setLastName(personVO.getLastName())
          .setMailAdresses(email)
          .setBirthDate(personVO.getBirthDate())
           .setProfile(profile)
          .build();
    return  personDAO.save(personToSave)!=null;
  }

  @Override
  @Transactional(rollbackFor = ProfileException.class)
  public boolean addSkillsPerson(List<Integer> skills,String email) throws ProfileException {
    Person personToUpdate = personDAO.findByMailAdresses(email);
    if(null == personToUpdate || null == email || StringUtils.isEmpty(email)){
      throw new ProfileException();
    }
    Set<Skill> collected = skills.stream().map(id -> skillDAO.findById(id)).collect(Collectors.toSet());
    Set<Skill> personSkills = personToUpdate.getSkills();
    if(!personSkills.isEmpty()){
      personSkills.addAll(collected);
    }else{
      personToUpdate.setSkills(collected);
    }
    return personDAO.save(personToUpdate)!=null;
  }

  @Override
  public PersonVO findPersonByMailAdresses(String mailAdresses) {
    Optional<Person>  personFinded = Optional.ofNullable(personDAO.findByMailAdresses(mailAdresses));
    if(personFinded.isPresent()) {
      return new PersonVO.Builder()
          .setMailAdresses(personFinded.get().getMailAdresses())
          .setBirthDate(personFinded.get().getBirthDate())
          .setFirstName(personFinded.get().getFirstName())
          .setLastName(personFinded.get().getLastName())
              .setProfile(personFinded.get().getProfile().getProfileTitle())
              .setSkills(personFinded.get().getSkills().stream()
              .map(skill -> new SkillVO.Builder()
              .setLibelle(skill.getLibelle())
              .setDescription(skill.getDescription()).build())
              .collect(Collectors.toList()))
              .build();
    }
    return null;
  }

  @Override
  public List<PersonVO> findListPerson() {
    return personDAO.findAll()
        .stream()
        .map(personFinded -> new PersonVO.Builder()
            .setMailAdresses(personFinded.getMailAdresses())
            .setBirthDate(personFinded.getBirthDate())
            .setFirstName(personFinded.getFirstName())
            .setLastName(personFinded.getLastName())
            .setProfile(personFinded.getProfile().getProfileTitle())
                .setSkills(personFinded.getSkills().stream()
                        .filter(skill -> skill!=null).map(skill -> new SkillVO.Builder()
                                .setLibelle(skill.getLibelle())
                                .setDescription(skill.getDescription()).build())
                        .collect(Collectors.toList()))
            .build())
        .collect(Collectors.toList());
  }

  @Override
  public List<PersonVO> findListProfilesByProfileTitle(String profileTitle) {
    return personDAO.findListProfilesByProfileTitle(profileTitle)
            .stream()
            .map(personFinded -> new PersonVO.Builder()
                    .setMailAdresses(personFinded.getMailAdresses())
                    .setBirthDate(personFinded.getBirthDate())
                    .setFirstName(personFinded.getFirstName())
                    .setLastName(personFinded.getLastName())
                    .setProfile(personFinded.getProfile().getProfileTitle())
                    .setSkills(personFinded.getSkills().stream()
                            .map(skill -> new SkillVO.Builder()
                                    .setLibelle(skill.getLibelle())
                                    .setDescription(skill.getDescription()).build())
                            .collect(Collectors.toList()))
                    .build())
            .collect(Collectors.toList());
  }
}
