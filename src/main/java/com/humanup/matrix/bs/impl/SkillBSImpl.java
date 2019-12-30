package com.humanup.matrix.bs.impl;

import com.humanup.matrix.bs.SkillBS;
import com.humanup.matrix.dao.SkillDAO;
import com.humanup.matrix.dao.TypeSkillsDAO;
import com.humanup.matrix.dao.entities.Skill;
import com.humanup.matrix.dao.entities.TypeSkills;
import com.humanup.matrix.vo.SkillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillBSImpl implements SkillBS {

    @Autowired
    private SkillDAO skillDAO;

    @Autowired
    private TypeSkillsDAO typeSkillsDAO;

    @Override
    public boolean createSkill(SkillVO skillVO) {
        TypeSkills typeSkills = typeSkillsDAO.findByTitleSkill(skillVO.getTypeSkills());

        Skill skillToSave = new Skill.Builder()
                .setLibelle(skillVO.getLibelle())
                .setDescription(skillVO.getDescription())
                .setTypeSkills(typeSkills)
                .build();
        return skillDAO.save(skillToSave) != null;
    }

    @Override
    public SkillVO findSkillByLibelle(String libelle) {
        Optional<Skill> skillFinded = Optional.ofNullable(skillDAO.findSkillByLibelle(libelle));
        if (skillFinded.isPresent()) {
            return new SkillVO.Builder()
                    .setLibelle(skillFinded.get().getLibelle())
                    .setDescription(skillFinded.get().getDescription())
                    .setTypeSkills(skillFinded.get().getTypeSkills().getTitleSkill())
                    .build();
        }
        return null;
    }

    @Override
    public List<SkillVO> findListSkillByTypeTitle(String titleSkill) {
		return skillDAO.findListSkillByTypeTitle(titleSkill)
				.stream()
				.map(skillFinded -> new SkillVO.Builder()
						.setLibelle(skillFinded.getLibelle())
						.setDescription(skillFinded.getDescription())
						.setTypeSkills(skillFinded.getTypeSkills().getTitleSkill())
						.build())
				.collect(Collectors.toList());
    }

    @Override
    public List<SkillVO> findListSkill() {
        return skillDAO.findAll()
                .stream()
                .map(skillFinded -> new SkillVO.Builder()
                        .setLibelle(skillFinded.getLibelle())
                        .setDescription(skillFinded.getDescription())
                        .setTypeSkills(skillFinded.getTypeSkills().getTitleSkill())
                        .build())
                .collect(Collectors.toList());
    }

}
