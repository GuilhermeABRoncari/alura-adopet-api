CREATE TABLE tutor (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(80) NOT NULL,
        email VARCHAR(80) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        confirmation_password VARCHAR(255) NOT NULL,

        PRIMARY KEY(id)
);