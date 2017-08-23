truncate table patron_profile;
truncate table patron_advance_profile;
truncate table patron_phone;
truncate table patron_internet_account;
truncate table patron_address;
truncate table address;

DROP SEQUENCE IF EXISTS PATRON_PROFILE_ACCNO_SEQ;
DROP SEQUENCE IF EXISTS ADDRESS_ID_SEQ;
DROP SEQUENCE IF EXISTS PATRON_ADDRESS_ID_SEQ;
DROP SEQUENCE IF EXISTS PATRON_ADVANCE_PROFILE_ID_SEQ;
DROP SEQUENCE IF EXISTS PATRON_INTERNET_ACCOUNT_ID_SEQ;
DROP SEQUENCE IF EXISTS PATRON_PROFILE_ID_SEQ;
DROP SEQUENCE IF EXISTS PATRON_PHONE_ID_SEQ;
DROP SEQUENCE IF EXISTS PATRON_SOLICITATION_ID_SEQ;

CREATE SEQUENCE IF NOT EXISTS PATRON_PROFILE_ACCNO_SEQ START WITH 1
INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999;
CREATE SEQUENCE IF NOT EXISTS PATRON_ADDRESS_ID_SEQ START WITH 1
INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999;
CREATE SEQUENCE IF NOT EXISTS PATRON_ADVANCE_PROFILE_ID_SEQ START WITH 1
INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999;
CREATE SEQUENCE IF NOT EXISTS ADDRESS_ID_SEQ START WITH 1
INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999;
CREATE SEQUENCE IF NOT EXISTS PATRON_INTERNET_ACCOUNT_ID_SEQ START WITH 1
INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999;
CREATE SEQUENCE IF NOT EXISTS PATRON_PROFILE_ID_SEQ START WITH 1
INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999;
CREATE SEQUENCE IF NOT EXISTS PATRON_PHONE_ID_SEQ START WITH 1
INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999;
CREATE SEQUENCE IF NOT EXISTS PATRON_SOLICITATION_ID_SEQ START WITH 1
INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999;

truncate table master_code_table;

INSERT INTO MASTER_CODE_TABLE(mastercodeid, categorycode, status, description1, description2) 
VALUES(185,'Identification Number Type',1,'Passport','P');
INSERT INTO MASTER_CODE_TABLE(mastercodeid, categorycode, status, description1, description2) 
VALUES(189,'Identification Number Type',1,'NRIC/FIN','N');
INSERT INTO MASTER_CODE_TABLE(mastercodeid, categorycode, status, description1, description2)  
VALUES(186,'Title',1,'MR','MR');
INSERT INTO MASTER_CODE_TABLE(mastercodeid, categorycode, status, description1, description2)  
VALUES(190,'Title',1,'MRS','MRS');
INSERT INTO MASTER_CODE_TABLE(mastercodeid, categorycode, status, description1, description2)  
VALUES(187,'Gender',1,'Male','M');
INSERT INTO MASTER_CODE_TABLE(mastercodeid, categorycode, status, description1, description2)  
VALUES(188,'Gender',1,'Female','F');
INSERT INTO MASTER_CODE_TABLE(mastercodeid, categorycode, status, description1, description2) 
VALUES(200,'Solicitation Type',1,'Tenant','1');
INSERT INTO MASTER_CODE_TABLE(mastercodeid, categorycode, status, description1, description2) 
VALUES(201,'Solicitation Type',1,'Promoter','2');
INSERT INTO MASTER_CODE_TABLE(mastercodeid, categorycode, status, description1, description2)  
VALUES(202,'Solicitation Type',1,'Venue','3');
INSERT INTO MASTER_CODE_TABLE(mastercodeid, categorycode, status, description1, description2)  
VALUES(203,'Solicitation Label',1,'TenantLabel','SISTIC.com Pte Ltd');


truncate table country;

INSERT INTO country(countryid, countrycode, countrycallingcode, currencysymbol, currencycode) 
VALUES(48,'VN','84','VN','VND');
INSERT INTO country(countryid, countrycode, countrycallingcode, currencysymbol, currencycode)  
VALUES(49,'SG','65','SG','SGD');
INSERT INTO country(countryid, countrycode, countrycallingcode, currencysymbol, currencycode)  
VALUES(50,'BG','359','??','BGN');
INSERT INTO country(countryid, countrycode, countrycallingcode, currencysymbol, currencycode)  
VALUES(51,'BM','1441','$','BMD');
INSERT INTO country(countryid, countrycode, countrycallingcode, currencysymbol, currencycode)  
VALUES(52,'BN','673','$','BND');

CREATE TABLE IF NOT EXISTS user_info
    (userinfoid                     NUMBER(10) NOT NULL,
    lastname                       VARCHAR2(50) NOT NULL,
    firstname                      VARCHAR2(50) NOT NULL,
    userid                         VARCHAR2(50) NOT NULL,
    userpassword                   VARCHAR2(255) NOT NULL,
    emailaddress                   VARCHAR2(75),
    sessiontimeout                 NUMBER(10),
    maxticketcount                 NUMBER(10),
    contactno                      VARCHAR2(50) NOT NULL,
    passwordexpiryon               TIMESTAMP,
    userstatus                     NUMBER(1) NOT NULL,
    loginfailedcount               NUMBER(2),
    approvalstatus                 NUMBER(1) NOT NULL,
    default_profile_info_id        NUMBER(10) NOT NULL,
    created_by                     NUMBER(10),
    createddate                    TIMESTAMP,
    updated_by                     NUMBER(10),
    updateddate                    TIMESTAMP,
    organization_id                NUMBER(10),
    usertype                       NUMBER(1) NOT NULL,
    macaddressrequired             NUMBER(1) NOT NULL,
    changepwonlogin                NUMBER(1),
    defaultexpirydays              NUMBER(10));

truncate TABLE user_info;

--INSERT INTO user_info (USERINFOID,LASTNAME,FIRSTNAME,USERID,USERPASSWORD,EMAILADDRESS,SESSIONTIMEOUT,MAXTICKETCOUNT,CONTACTNO,PASSWORDEXPIRYON,USERSTATUS,LOGINFAILEDCOUNT,APPROVALSTATUS,DEFAULT_PROFILE_INFO_ID,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE,ORGANIZATION_ID,USERTYPE,MACADDRESSREQUIRED,CHANGEPWONLOGIN,DEFAULTEXPIRYDAYS) 
--VALUES(62,'Seet','Stacey','staceyseet','5788c1dbcdae9cec4b8087d8005743e1','staceyseet@sistic.com.sg',3600,50,'0',TO_TIMESTAMP('2013-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),1,0,4,9,4,TO_TIMESTAMP('2013-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),62,TO_TIMESTAMP('2013-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),1,2,2,0,90);
--INSERT INTO user_info (USERINFOID,LASTNAME,FIRSTNAME,USERID,USERPASSWORD,EMAILADDRESS,SESSIONTIMEOUT,MAXTICKETCOUNT,CONTACTNO,PASSWORDEXPIRYON,USERSTATUS,LOGINFAILEDCOUNT,APPROVALSTATUS,DEFAULT_PROFILE_INFO_ID,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE,ORGANIZATION_ID,USERTYPE,MACADDRESSREQUIRED,CHANGEPWONLOGIN,DEFAULTEXPIRYDAYS) 
--VALUES(59,'Arun','Aida','sitinorhaidah_vivo','5788c1dbcdae9cec4b8087d8005743e1','siti.arun@vivocity.com.sg',14400,50,'96921769',TO_TIMESTAMP('2013-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),1,0,4,126,1,TO_TIMESTAMP('2013-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),523,TO_TIMESTAMP('2013-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),747,2,2,0,90);
--INSERT INTO user_info (USERINFOID,LASTNAME,FIRSTNAME,USERID,USERPASSWORD,EMAILADDRESS,SESSIONTIMEOUT,MAXTICKETCOUNT,CONTACTNO,PASSWORDEXPIRYON,USERSTATUS,LOGINFAILEDCOUNT,APPROVALSTATUS,DEFAULT_PROFILE_INFO_ID,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE,ORGANIZATION_ID,USERTYPE,MACADDRESSREQUIRED,CHANGEPWONLOGIN,DEFAULTEXPIRYDAYS) 
--VALUES(524,'Sussax','Robert','robertsussax_jem','11da11e6086bd5bd05d89920eafc4cf6','robert.nichlose@gmail.com',14400,50,'62255536',TO_TIMESTAMP('2013-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),1,0,4,93,1,TO_TIMESTAMP('2013-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),524,TO_TIMESTAMP('2013-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),1397,2,2,0,90);

