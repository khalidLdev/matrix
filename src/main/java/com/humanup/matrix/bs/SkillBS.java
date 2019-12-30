package com.humanup.matrix.bs;

import java.util.List;

import com.humanup.matrix.vo.SkillVO;

public interface SkillBS {
    boolean createSkill(SkillVO Skill);
    SkillVO findSkillByLibelle(String libelle);
    List<SkillVO> findListSkill();
    List<SkillVO> findListSkillByTypeTitle(String titleSkill);
}
