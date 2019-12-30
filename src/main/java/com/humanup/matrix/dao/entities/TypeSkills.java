package com.humanup.matrix.dao.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_skills")
public class TypeSkills {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long typeId;
	private String titleSkill;

	@OneToMany(mappedBy="typeSkills",fetch=FetchType.LAZY)
	private List<Skill> skillList;

	protected TypeSkills() {
	}

	public TypeSkills(String titleSkill) {
		this.titleSkill = titleSkill;
	}

	@Override
	public String toString() {
		return "TypeSkills [typeId=" + typeId + ", titleSkill=" + titleSkill + "]";
	}

	public Long getTypeId() {
		return typeId;
	}

	public String getTitleSkill() {
		return titleSkill;
	}

	public List<Skill> getSkillList() {
		return skillList;
	}


	public static class Builder {

		private String titleSkill;

		public Builder() {
		}

		public Builder setTitleSkill(String titleSkill) {
			this.titleSkill = titleSkill;
			return this;
		}
		public TypeSkills build(){
			return new TypeSkills(titleSkill);
		}
	}

}
