DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS profile_person;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS type_skills;
DROP TABLE IF EXISTS person_skill;


CREATE TABLE profile_person (
  profile_id INT AUTO_INCREMENT  PRIMARY KEY,
  profile_title VARCHAR(250) NOT NULL,
  profile_description VARCHAR(250) NOT NULL
);

INSERT INTO profile_person (profile_title, profile_description) VALUES
  ('Developpeur Junior', 'Developpeur Junior 3ans'),
  ('Developpeur Sénior', 'Developpeur Sénior 5ans'),
  ('Business Analyste Junior', 'Business Analyste Junior 1an(s)'),
  ('Business Analyste Sénior', 'Business Analyste Sénior 5ans');

  CREATE TABLE person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  mail_adresses VARCHAR(250) DEFAULT NULL,
  birth_date DATE DEFAULT NULL,
  profile_id INT NOT NULL
);

INSERT INTO person (first_name, last_name, mail_adresses, birth_date,profile_id) VALUES
  ('Aliko', 'Dangote', 'adangote@sqli.com','1973-11-17',1),
  ('Amira', 'El ouardi', 'aelouardi@sqli.com','2015-09-26',1),
  ('Ibrahim', 'El ouardi', 'ielouardi@sqli.com','2016-10-23',2),
  ('Yassine', 'El ouardi', 'yelouardi@sqli.com','1986-09-14',2);

  CREATE TABLE type_skills (
  type_id INT AUTO_INCREMENT  PRIMARY KEY,
  title_skill VARCHAR(250) NOT NULL
);

INSERT INTO type_skills (title_skill) VALUES
  ('audace'),
   ('java'),
   ('react');

CREATE TABLE skill (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  libelle VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  type_id INT NOT NULL
);

INSERT INTO skill (libelle, description, type_id) VALUES
  ('Java', 'Java 8',1),
  ('C#', 'C# description',1),
  ('C++', 'C++ description',2);


CREATE TABLE person_skill (
  person_id INT NOT NULL,
  skill_id INT NOT NULL
);
INSERT INTO person_skill (person_id, skill_id) VALUES
  (4, 1),
  (4, 2),
  (4, 3),
   (3, 1),
   (2, 2),
   (3, 3);