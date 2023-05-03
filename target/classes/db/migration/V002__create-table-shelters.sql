CREATE TABLE shelters (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(80) NOT NULL,
        email VARCHAR(80) NOT NULL UNIQUE,
        fone VARCHAR(20) NOT NULL,
        pet_id BIGINT,

        PRIMARY KEY(id)
);

CREATE TABLE pets (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(80) NOT NULL,
        years_old VARCHAR(80) NOT NULL,
        animal_size VARCHAR(20) NOT NULL,
        description VARCHAR(80) NOT NULL,
        adopted BIT NOT NULL,
        image VARCHAR(255) NOT NULL,
        shelter_id BIGINT NOT NULL,

        PRIMARY KEY(id)
);

ALTER TABLE shelters ADD CONSTRAINT fk_pet FOREIGN KEY (pet_id) REFERENCES pets (id);
ALTER TABLE pets ADD CONSTRAINT fk_shelter FOREIGN KEY (shelter_id) REFERENCES shelters (id);