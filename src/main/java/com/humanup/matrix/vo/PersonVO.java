package com.humanup.matrix.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class PersonVO {
    private String firstName;
    private String lastName;
    private String mailAdresses;
    private Date birthDate;
    private String profile;
    @JsonIgnore
    private List<SkillVO> skillVOList;

    public PersonVO() {
    }

    public PersonVO(String firstName, String lastName, String mailAdresses, Date birthDate, String profile, List<SkillVO> skillVOList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailAdresses = mailAdresses;
        this.birthDate = birthDate;
        this.profile = profile;
        this.skillVOList = skillVOList;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMailAdresses() {
        return mailAdresses;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getProfile() {
        return profile;
    }


    @JsonProperty
    public List<SkillVO> getSkillVOList() {
        return skillVOList;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String mailAdresses;
        private Date birthDate;
        private String profile;
        private List<SkillVO> skillVOList;

        public Builder() {
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setMailAdresses(String mailAdresses) {
            this.mailAdresses = mailAdresses;
            return this;
        }

        public Builder setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder setProfile(String profile) {
            this.profile = profile;
            return this;
        }

        public Builder setSkills(List<SkillVO> skillVOList) {
            this.skillVOList = skillVOList;
            return this;
        }

        public PersonVO build() {
            return new PersonVO(firstName, lastName, mailAdresses, birthDate, profile,skillVOList);
        }
    }
}
