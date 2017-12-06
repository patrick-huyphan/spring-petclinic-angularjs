DROP TABLE vet_specialties IF EXISTS;
DROP TABLE vets IF EXISTS;
DROP TABLE specialties IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE pets IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE owners IF EXISTS;
DROP TABLE companies IF EXISTS;
DROP TABLE comments IF EXISTS;
DROP TABLE fileInfo IF EXISTS;

CREATE TABLE vets (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX vets_last_name ON vets (last_name);

CREATE TABLE specialties (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE vet_specialties (
  vet_id       INTEGER NOT NULL,
  specialty_id INTEGER NOT NULL
);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE owners (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  telephone  VARCHAR(20)
);
CREATE INDEX owners_last_name ON owners (last_name);

CREATE TABLE companies (
  id         INTEGER IDENTITY PRIMARY KEY,
  name      VARCHAR(30),
  taxCode  VARCHAR(30),
  address    VARCHAR(255),
  info       VARCHAR(255),
  telephone  VARCHAR(20),
  owner_id   INTEGER NOT NULL
);
ALTER TABLE companies ADD CONSTRAINT fk_companies_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
CREATE INDEX taxCode ON companies (taxCode);

CREATE TABLE pets (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  birth_date DATE,
  type_id    INTEGER NOT NULL,
  owner_id   INTEGER NOT NULL
);
ALTER TABLE pets ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
ALTER TABLE pets ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name ON pets (name);

CREATE TABLE visits (
  id          INTEGER IDENTITY PRIMARY KEY,
  pet_id      INTEGER NOT NULL,
  visit_date  DATE,
  description VARCHAR(8192)
);
ALTER TABLE visits ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
CREATE INDEX visits_pet_id ON visits (pet_id);

CREATE TABLE comments (
  id        INTEGER IDENTITY PRIMARY KEY,
  com_id    INTEGER NOT NULL,
  owner_id    INTEGER NOT NULL,   
  detail    VARCHAR(250),
  ctime     DATE
);
ALTER TABLE comments ADD CONSTRAINT fk_comments_companies FOREIGN KEY (com_id) REFERENCES companies (id);
ALTER TABLE comments ADD CONSTRAINT fk_comments_owners FOREIGN KEY (owner_id) REFERENCES owners (id);


CREATE TABLE fileInfo (
  id        INTEGER IDENTITY PRIMARY KEY,
  name    VARCHAR(250),
  com_id    INTEGER NOT NULL,
  owner_id    INTEGER NOT NULL,   
  detail    VARCHAR(250),
  ctime     DATE
);
ALTER TABLE fileInfo ADD CONSTRAINT fk_fileInfo_companies FOREIGN KEY (com_id) REFERENCES companies (id);
ALTER TABLE fileInfo ADD CONSTRAINT fk_fileInfo_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
CREATE INDEX file_name ON fileInfo (name);