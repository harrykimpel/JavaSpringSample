drop table USER if exists;
CREATE TABLE USER (ID INT primary key,USERNAME VARCHAR(50) NOT NULL,PASSWORD VARCHAR(50) NOT NULL,ISACTIVE BOOLEAN);

drop table USER_ROLES if exists;
CREATE TABLE USER_ROLES (ROLEID VARCHAR(10) primary key,USERNAME VARCHAR(50) NOT NULL,ROLES VARCHAR(50) NOT NULL);

INSERT INTO USER  VALUES ('1', 'ADMIN', 'PASSWORD', 0),('2', 'CIBER', 'PASSWORD', 0);

INSERT INTO USER_ROLES  VALUES ('1','ADMIN','ROLE_ADMIN'),('2','CIBER','ROLE_USER');

INSERT INTO Customer VALUES ('12345', 'Harry', 'Kimpel', 'Strasse', 'Munich', 'BY', '80336', 'F150', 'green', '1234567890');
INSERT INTO Customer VALUES ('12346', 'Gregory', 'Bakakos', 'Street', 'Denver', 'CO', '32145', 'F150', 'blue', '12321234');
INSERT INTO Customer VALUES ('12347', 'Steve', 'Woods', 'Street 2', 'Orlando', 'FL', '12678', 'F150', 'purple', '12121212');
INSERT INTO Customer VALUES ('12348', 'Mike', 'Kurbansade', 'Street 1', 'Seattle', 'WA', '98560', 'F150', 'yellow', '1234321');
INSERT INTO Customer VALUES ('12349', 'Duraid', 'Ibrahim', 'Street 7', 'London', 'UK', '66666', 'F150', 'pink', '987654321');

drop table BATCH_JOB_INSTANCE if exists;
CREATE TABLE BATCH_JOB_INSTANCE  (JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,VERSION BIGINT NULL,JOB_NAME VARCHAR(100) NOT NULL,JOB_KEY VARCHAR(32) NOT NULL,constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)) ;

drop table BATCH_JOB_EXECUTION if exists;
CREATE TABLE BATCH_JOB_EXECUTION  (JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,VERSION BIGINT NULL,JOB_INSTANCE_ID BIGINT NOT NULL,CREATE_TIME DATETIME NOT NULL,START_TIME DATETIME DEFAULT NULL ,END_TIME DATETIME DEFAULT NULL ,STATUS VARCHAR(10) NULL,EXIT_CODE VARCHAR(2500) NULL,EXIT_MESSAGE VARCHAR(2500) NULL,LAST_UPDATED DATETIME NULL,JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)) ;

drop table BATCH_JOB_EXECUTION_PARAMS if exists;
CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (JOB_EXECUTION_ID BIGINT NOT NULL ,TYPE_CD VARCHAR(6) NOT NULL ,KEY_NAME VARCHAR(100) NOT NULL ,STRING_VAL VARCHAR(250) NULL,DATE_VAL DATETIME DEFAULT NULL ,LONG_VAL BIGINT NULL,DOUBLE_VAL DOUBLE PRECISION NULL,IDENTIFYING CHAR(1) NOT NULL ,constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)) ;

drop table BATCH_STEP_EXECUTION if exists;
CREATE TABLE BATCH_STEP_EXECUTION  (STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,VERSION BIGINT NOT NULL,STEP_NAME VARCHAR(100) NOT NULL,JOB_EXECUTION_ID BIGINT NOT NULL,START_TIME DATETIME NOT NULL ,END_TIME DATETIME DEFAULT NULL ,STATUS VARCHAR(10) NULL,COMMIT_COUNT BIGINT NULL,READ_COUNT BIGINT NULL,FILTER_COUNT BIGINT NULL,WRITE_COUNT BIGINT NULL,READ_SKIP_COUNT BIGINT NULL,WRITE_SKIP_COUNT BIGINT NULL,PROCESS_SKIP_COUNT BIGINT NULL,ROLLBACK_COUNT BIGINT NULL,EXIT_CODE VARCHAR(2500) NULL,EXIT_MESSAGE VARCHAR(2500) NULL,LAST_UPDATED DATETIME NULL,constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)) ;

drop table BATCH_STEP_EXECUTION_CONTEXT if exists;
CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,SHORT_CONTEXT VARCHAR(2500) NOT NULL,SERIALIZED_CONTEXT TEXT NULL,constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)) ;

drop table BATCH_JOB_EXECUTION_CONTEXT if exists;
CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,SHORT_CONTEXT VARCHAR(2500) NOT NULL,SERIALIZED_CONTEXT TEXT NULL,constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)) ;

drop table BATCH_STEP_EXECUTION_SEQ if exists;
CREATE TABLE BATCH_STEP_EXECUTION_SEQ (ID BIGINT IDENTITY);

drop table BATCH_JOB_EXECUTION_SEQ if exists;
CREATE TABLE BATCH_JOB_EXECUTION_SEQ (ID BIGINT IDENTITY);

drop table BATCH_JOB_SEQ if exists;
CREATE TABLE BATCH_JOB_SEQ (ID BIGINT IDENTITY);