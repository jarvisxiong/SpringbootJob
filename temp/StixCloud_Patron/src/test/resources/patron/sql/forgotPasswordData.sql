truncate TABLE PATRON_TOKEN_FORGOT_PWD;
truncate table Patron_Profile;

DROP SEQUENCE IF EXISTS PATRON_PROFILE_ACCNO_SEQ;
DROP TABLE IF EXISTS APPLICATION_CONFIG;

CREATE SEQUENCE IF NOT EXISTS PATRON_TOKEN_SEQ START WITH 1
INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999;

INSERT INTO PATRON_TOKEN_FORGOT_PWD(tokenid, tokenhash, patron, patronemail, expirytime, requeston, requestby, requestbyuser, updatedon, activestatus, origin) 
VALUES(5,'47d59bfe-e1ab-477d-90c7-6db409394b2c',735008,'dbthan@cmc.com.vn',TO_TIMESTAMP('2018-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),735008,'Patron',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),1,'Booking Engine');
INSERT INTO PATRON_TOKEN_FORGOT_PWD(tokenid, tokenhash, patron, patronemail, expirytime, requeston, requestby, requestbyuser, updatedon, activestatus, origin) 
VALUES(331,'bbfb991b-2166-4690-aaa9-90f24c2f327a',489864,'cmc1@gmail.com',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),489864,'Patron',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),1,'Booking Engine');
INSERT INTO PATRON_TOKEN_FORGOT_PWD(tokenid, tokenhash, patron, patronemail, expirytime, requeston, requestby, requestbyuser, updatedon, activestatus, origin) 
VALUES(336,'114ecb3e-c1a9-4cbb-b7a3-ec1f72b9745b',959872,'fionaong@live.com',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),959872,'Patron',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),0,'Booking Engine');
INSERT INTO PATRON_TOKEN_FORGOT_PWD(tokenid, tokenhash, patron, patronemail, expirytime, requeston, requestby, requestbyuser, updatedon, activestatus, origin) 
VALUES(339,'348bbead-2b28-4bad-a390-08e6eeadebb9',27851,'positively39@hotmail.com',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),27851,'Patron',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),0,'Booking Engine');
INSERT INTO PATRON_TOKEN_FORGOT_PWD(tokenid, tokenhash, patron, patronemail, expirytime, requeston, requestby, requestbyuser, updatedon, activestatus, origin) 
VALUES(346,'f85c229f-097d-4786-a58e-3c5dd9ec0dc6',676756,'amandalim.1990@gmail.com',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),676756,'Patron',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),0,'Booking Engine');

INSERT INTO patron_profile(patronprofileid, identityno, accno, status, firstname, lastname, emailaddress, isreceiveticketingagent, isreceivepromoter, isreceivevenue, isbillingaddresssameasmailing, isdonotsignup, organizationname, companyid, companyname, employeeid, guestof, externalid, acctremark, acc_type_mct_id, gender_mct_id, title_mct_id, nationality, countryofresidence, createddate, created_by, updateddate, updated_by, identity_no_type_mct_id) 
VALUES(1921828,'S999996E',5084729,1,'THAN BA','DO','dbthan@cmc.com.vn','T','F','F','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,249,228,'VN','VN',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),59,TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),59,646);
--INSERT INTO patron_profile(patronprofileid, identityno, accno, status, firstname, lastname, emailaddress, isreceiveticketingagent, isreceivepromoter, isreceivevenue, isbillingaddresssameasmailing, isdonotsignup, organizationname, companyid, companyname, employeeid, guestof, externalid, acctremark, acc_type_mct_id, gender_mct_id, title_mct_id, nationality, countryofresidence, createddate, created_by, updateddate, updated_by, identity_no_type_mct_id) 
--VALUES(1921829,'S999996E',5084729,1,'THAN BA','DO','cmc1@gmail.com','T','F','F','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,249,228,'VN','VN',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),59,TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),59,646);


create table APPLICATION_CONFIG(
	APPCONFIGID                    NUMBER(10),
ORG_ID                         NUMBER(10),
TYPE                           VARCHAR2(100),
PARAM1                         VARCHAR2(200),
PARAM2                         VARCHAR2(100),
PARAM3                         VARCHAR2(100),
CATEGORY1                      VARCHAR2(100),
CATEGORY2                      VARCHAR2(100),
CATEGORY3                      VARCHAR2(100)
);

INSERT INTO APPLICATION_CONFIG(APPCONFIGID, ORG_ID, TYPE, PARAM1, PARAM2, PARAM3, CATEGORY1, CATEGORY2, CATEGORY3) 
VALUES(13,NULL,'TOKEN_EXPIRY_TIME','20',NULL,NULL,NULL,NULL,NULL);
INSERT INTO APPLICATION_CONFIG(APPCONFIGID, ORG_ID, TYPE, PARAM1, PARAM2, PARAM3, CATEGORY1, CATEGORY2, CATEGORY3)  
VALUES(14,NULL,'SEARCH_EVENTS_WS_PAGE_SIZE','20',NULL,NULL,NULL,NULL,NULL);
INSERT INTO APPLICATION_CONFIG(APPCONFIGID, ORG_ID, TYPE, PARAM1, PARAM2, PARAM3, CATEGORY1, CATEGORY2, CATEGORY3) 
VALUES(15,NULL,'ACTIVATION_LINK_ESP','https://secure-uat.esplanade.com/web/booking/ResetPassword.do?',NULL,NULL,'TOKEN',NULL,NULL);
INSERT INTO APPLICATION_CONFIG(APPCONFIGID, ORG_ID, TYPE, PARAM1, PARAM2, PARAM3, CATEGORY1, CATEGORY2, CATEGORY3)  
VALUES(16,NULL,'ACTIVATION_LINK_NGS','https://stagingtickets.nationalgallery.sg/web/ResetPassword.do?',NULL,NULL,'TOKEN',NULL,NULL);
INSERT INTO APPLICATION_CONFIG(APPCONFIGID, ORG_ID, TYPE, PARAM1, PARAM2, PARAM3, CATEGORY1, CATEGORY2, CATEGORY3)  
VALUES(17,NULL,'ACTIVATION_LINK','http://sistic.stixclouduat.com/SisticWebApp/ResetPassword.do?',NULL,NULL,'TOKEN',NULL,NULL);
INSERT INTO APPLICATION_CONFIG(APPCONFIGID, ORG_ID, TYPE, PARAM1, PARAM2, PARAM3, CATEGORY1, CATEGORY2, CATEGORY3) 
VALUES(18,NULL,'BE_LOGINFAILED_CONFIG','3',NULL,NULL,NULL,NULL,NULL);
