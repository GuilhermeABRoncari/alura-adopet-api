ALTER TABLE shelters DROP COLUMN adress,
ADD COLUMN cep VARCHAR(8), ADD COLUMN state VARCHAR(2),
ADD COLUMN city VARCHAR(80), ADD COLUMN neighborhood VARCHAR(150),
ADD COLUMN street VARCHAR(150), ADD COLUMN number VARCHAR(10);