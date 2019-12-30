package com.humanup.matrix.dao;

import java.util.List;

import com.humanup.matrix.dao.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.humanup.matrix.dao.entities.Skill;

public interface SkillDAO extends CrudRepository<Skill, Long> {

	  Skill findSkillByLibelle(String libelle);
	  List<Skill> findAll();
	  Skill findById(long id);
	  @Query("SELECT s FROM Skill s WHERE lower(s.typeSkills.titleSkill) like %:titleSkill% ")
      List<Skill> findListSkillByTypeTitle(String titleSkill);
}
