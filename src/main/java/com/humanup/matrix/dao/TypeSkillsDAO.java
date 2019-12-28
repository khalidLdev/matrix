package com.humanup.matrix.dao;

import com.humanup.matrix.dao.entities.TypeSkills;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeSkillsDAO extends CrudRepository<TypeSkills, Long> {

	TypeSkills findByTitleSkill(String titleSkill);
    List<TypeSkills> findAll();
    TypeSkills findByTypeId(long typeId);
    @Query("SELECT t FROM TypeSkills t WHERE lower(t.titleSkill) like %:titleSkill% ")
    List<TypeSkills> findTypeSkillsByTitle(String titleSkill);
    
}
