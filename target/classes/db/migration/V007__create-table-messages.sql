CREATE TABLE adopet_messages (
        id BIGINT NOT NULL AUTO_INCREMENT,
        tutor_id BIGINT NOT NULL,
        shelter_id BIGINT NOT NULL,
        pet_id BIGINT NOT NULL,
        message TEXT,

        PRIMARY KEY(id)
);
ALTER TABLE adopet_messages ADD CONSTRAINT fk_tutor_message FOREIGN KEY (tutor_id) REFERENCES tutor (id);
ALTER TABLE adopet_messages ADD CONSTRAINT fk_shelter_message FOREIGN KEY (shelter_id) REFERENCES shelters (id);
ALTER TABLE adopet_messages ADD CONSTRAINT fk_pet_message FOREIGN KEY (pet_id) REFERENCES pets (id);