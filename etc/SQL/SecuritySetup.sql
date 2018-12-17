CREATE TABLE USER (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  USERNAME VARCHAR(50) NOT NULL,
  PASSWORD VARCHAR(50) NOT NULL,
  ISACTIVE BOOLEAN,
  PRIMARY KEY (Id)
);

CREATE TABLE USER_ROLES (
  ROLEID VARCHAR(10) NOT NULL,
  USERNAME VARCHAR(50) NOT NULL,
  ROLES VARCHAR(50) NOT NULL,
  PRIMARY KEY (ROLEID)
);

INSERT INTO USER (ID, USERNAME, PASSWORD, ISACTIVE) VALUES
 ('1', 'ADMIN', 'PASSWORD', 0),
 ('2', 'CIBER', 'PASSWORD', 0);

INSERT INTO USER_ROLES (ROLEID,USERNAME,ROLES) VALUES 
 ('1','ADMIN','ROLE_ADMIN'),
 ('2','CIBER','ROLE_USER');