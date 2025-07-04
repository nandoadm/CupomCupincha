CREATE TABLE users
(
    id       CHAR(36) NOT NULL,
    username VARCHAR(40),
    password VARCHAR(80),
    CONSTRAINT pk_user PRIMARY KEY (id)
);