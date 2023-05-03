ALTER TABLE tutor ADD COLUMN message_id BIGINT;
ALTER TABLE tutor ADD CONSTRAINT fk_message_tutor FOREIGN KEY (message_id) REFERENCES adopet_messages (id);
ALTER TABLE shelters ADD COLUMN message_id BIGINT;
ALTER TABLE shelters ADD CONSTRAINT fk_message_shelter FOREIGN KEY (message_id) REFERENCES adopet_messages (id);
