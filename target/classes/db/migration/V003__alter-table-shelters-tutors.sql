ALTER TABLE tutor
ADD COLUMN about TEXT,
MODIFY password VARCHAR(255);

ALTER TABLE shelters ADD COLUMN password VARCHAR(255), ADD COLUMN adress VARCHAR(255);