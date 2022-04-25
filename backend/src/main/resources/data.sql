DROP TABLE IF EXISTS t_user CASCADE;
DROP TABLE IF EXISTS t_authority CASCADE;
DROP TABLE IF EXISTS t_user_authority;

create table t_user (id serial PRIMARY KEY, email varchar(255) NOT NULL UNIQUE, password varchar(255) NOT NULL);
create table t_authority (name VARCHAR(255) PRIMARY KEY);
create table t_user_authority (email varchar(255) NOT NULL, authority_name VARCHAR(255) NOT NULL, PRIMARY KEY (email),FOREIGN KEY (authority_name) REFERENCES t_authority (name),FOREIGN KEY (email) REFERENCES t_user (email));

INSERT INTO t_authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO t_authority (name) VALUES ('ROLE_USER');

INSERT INTO t_user (id, email, password) VALUES (500, 'admin@gmail.com', '$2a$10$JquPqGKGKSplSc/lvHpsJedbK/xqe2Vghqw.nE17tNdB.UWcIVQ7K');
INSERT INTO t_user_authority (email, authority_name) VALUES ('admin@gmail.com', 'ROLE_ADMIN');