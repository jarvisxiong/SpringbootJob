CREATE TABLE EVOUCHER_VIEW (
   E_VOUCHER_ID VARCHAR2(100),
   E_VOUCHER_TYPE VARCHAR2(200),
   REDEEM_VALUE NUMBER(20),
   E_VOUCHER_STATUS NUMBER(1),
   START_DATE DATE,
   END_DATE DATE,
   ORGANIZATION_ID NUMBER(10),
   PATRON_PROFILE_ID NUMBER(10),
   IS_GIFT_VOUCHER NUMBER(1),
   ATTRIBUTE_NAME VARCHAR2(50),
   ATTRIBUTE_VALUE VARCHAR2(100),
   PRIORITY NUMBER(3));
   
INSERT INTO EVOUCHER_VIEW 
VALUES('a5pQCa9p','ESPLANADE_E_VOUCHER',10,1,TO_DATE('2015-10-27 17:13:19', 'YYYY-MM-DD HH24:MI:SS'),TO_DATE('2016-10-26 23:59:59', 'YYYY-MM-DD HH24:MI:SS'),2,884928,0,'MAXIMUM_QTY','1',4);
INSERT INTO EVOUCHER_VIEW 
VALUES('EEKGP2fP','ESPLANADE_E_VOUCHER',10,1,TO_DATE('2015-10-27 17:13:19', 'YYYY-MM-DD HH24:MI:SS'),TO_DATE('2016-10-26 23:59:59', 'YYYY-MM-DD HH24:MI:SS'),2,884928,0,'MAXIMUM_QTY','1',4);
INSERT INTO EVOUCHER_VIEW 
VALUES('43SN7eMG','ESPLANADE_E_VOUCHER',10,1,TO_DATE('2015-10-27 19:08:45', 'YYYY-MM-DD HH24:MI:SS'),TO_DATE('2016-10-26 23:59:59', 'YYYY-MM-DD HH24:MI:SS'),2,262517,0,'MAXIMUM_QTY','1',4);
INSERT INTO EVOUCHER_VIEW 
VALUES('DM30744434','DBS_E_VOUCHER',10,2,TO_DATE('2014-07-18 15:43:00', 'YYYY-MM-DD HH24:MI:SS'),TO_DATE('2015-07-18 15:43:00', 'YYYY-MM-DD HH24:MI:SS'),NULL,NULL,NULL,'MAXIMUM_QTY','1',4);

CREATE TABLE PRODUCT_LIVE
    (PRODUCTID                      NUMBER(10),
    PRODUCT_GROUP_ID               NUMBER(10),
    PRODUCTNAME                    VARCHAR2(255),
    PRODUCTCODE                    VARCHAR2(50),
    GLCODE                         VARCHAR2(50),
    DESCRIPTION                    VARCHAR2(500),
    REMARKS                        VARCHAR2(1000),
    KEYWORDSEARCH                  VARCHAR2(255),
    REPORTVISIBILITY               CHAR(1),
    FINANCIALREPORTVISIBILITY      CHAR(1),
    PRODUCTOWNER                   VARCHAR2(128),
    WEIGHT                         NUMBER(5),
    SALESSTATUS                    NUMBER(1),
    ACTIVATIONSTATUS               NUMBER(1),
    MAXTICKETSAPPL                 NUMBER(3),
    STARTDATE                      TIMESTAMP (0),
    ENDDATE                        TIMESTAMP (0),
    CREATED_BY                     NUMBER(10),
    CREATEDDATE                    TIMESTAMP (0) ,
    UPDATED_BY                     NUMBER(10),
    UPDATEDDATE                    TIMESTAMP (0),
    STOP_SALES_REASON_MCT_ID       NUMBER(10),
    FESTIVAL_CODE_ID               NUMBER(10),
    SPAN_CODE_MCT_ID               NUMBER(10),
    VENUE_ID                       NUMBER(10),
    VENUELAYOUTCONFIG_ID           NUMBER(10),
    SALESPIC                       NVARCHAR2(128),
    EVENTBUILDER                   NVARCHAR2(255));
    
INSERT INTO PRODUCT_LIVE 
VALUES(1,1,'LOLA (PG) (Test)','eaft1311222000','french1013',NULL,NULL,'French Movie 2013','T','T','Melody Tang',NULL,1,1,NULL,'22-NOV-2013 8:00:00. PM +08:00',NULL,28,'28-OCT-2013 6:52:18. PM +08:00',28,'28-OCT-2013 6:52:18. PM +08:00',14,NULL,NULL,50,151,NULL,NULL);
INSERT INTO PRODUCT_LIVE 
VALUES(2,1,'LOLA (PG) (Test)','eaft1312222000','french1013',NULL,NULL,'French Movie 2013','T','T','Melody Tang',NULL,1,1,NULL,'22-DEC-2013 8:00:00. PM +08:00',NULL,28,'28-OCT-2013 6:52:18. PM +08:00',28,'28-OCT-2013 6:52:18. PM +08:00',14,NULL,NULL,50,150,NULL,NULL);
INSERT INTO PRODUCT_LIVE 
VALUES(3,1,'LOLA (PG) (Test)','eaft1310222000','french1013',NULL,NULL,'French Movie 2013','T','T','Melody Tang',NULL,0,1,NULL,'22-OCT-2013 8:00:00. PM +08:00',NULL,28,'28-OCT-2013 6:52:18. PM +08:00',28,'28-OCT-2013 6:52:18. PM +08:00',NULL,NULL,NULL,50,200,NULL,NULL);
INSERT INTO PRODUCT_LIVE 
VALUES(4,2,'TOREO (PG) (Test)','eaft1311092000','spain1113',NULL,NULL,'Spanish Movie Fiesta 2013 (Test)','T','T','allison',NULL,0,1,NULL,'17-NOV-2013 8:00:00. PM +08:00',NULL,30,'6-JAN-2014 3:09:43. PM +08:00',30,'6-JAN-2014 3:09:43. PM +08:00',NULL,NULL,NULL,50,1150,NULL,NULL);
INSERT INTO PRODUCT_LIVE 
VALUES(5,2,'TOREO (PG) (Test)','eaft1311102000','spain1113',NULL,NULL,'Spanish Movie Fiesta 2013 (Test)','T','T','allison',NULL,0,1,NULL,'18-NOV-2013 8:00:00. PM +08:00',NULL,30,'6-JAN-2014 3:09:43. PM +08:00',30,'6-JAN-2014 3:09:43. PM +08:00',NULL,NULL,NULL,50,1200,NULL,NULL);
INSERT INTO PRODUCT_LIVE 
VALUES(6,3,'Spider Man (Test)','eaft1312302000','spider1213',NULL,NULL,'Spider Man','T','T','dannytan',NULL,0,1,NULL,'31-MAR-2014 8:00:00. PM +08:00',NULL,30,'9-JUN-2014 6:56:56. PM +08:00',30,'9-JUN-2014 6:56:56. PM +08:00',NULL,NULL,30,50,1250,NULL,NULL);
INSERT INTO PRODUCT_LIVE 
VALUES(7,3,'Super Heros (Test)','eaft1312312000','super1213',NULL,NULL,'Spider Man','T','T','dannytan',NULL,0,1,NULL,'31-DEC-2013 8:00:00. PM +08:00',NULL,30,'9-JUN-2014 6:56:54. PM +08:00',30,'9-JUN-2014 6:56:54. PM +08:00',NULL,NULL,30,50,1251,NULL,NULL);
INSERT INTO PRODUCT_LIVE 
VALUES(8,4,'Pitbull 2013 World Tour (Test)','emax1312262000','pitbull1213',NULL,NULL,'Pitbull 2013 World Tour','T','T','Valerie',NULL,0,1,NULL,'26-DEC-2013 8:00:00. PM +08:00',NULL,30,'5-NOV-2013 11:46:05. AM +08:00',30,'5-NOV-2013 11:46:05. AM +08:00',NULL,NULL,NULL,100,1300,NULL,NULL);
INSERT INTO PRODUCT_LIVE 
VALUES(9,5,'Poprocks Live In Concert (Test)','emax1312072000','pop1213',NULL,NULL,'Poprocks Live In Concert (Test)','T','T','allison',NULL,0,2,NULL,'7-DEC-2013 8:00:00. PM +08:00',NULL,30,'14-JAN-2014 9:50:16. AM +08:00',30,'14-JAN-2014 9:50:16. AM +08:00',NULL,NULL,NULL,100,503761,NULL,NULL);
INSERT INTO PRODUCT_LIVE 
VALUES(10,5,'A Spanish Tenor In Concert (Test)','eech1312122000','tenor1213',NULL,NULL,'A Spanish Tenor In Concert (Test)','T','T','allison',NULL,0,1,NULL,'12-DEC-2013 8:00:00. PM +08:00',NULL,28,'25-NOV-2013 10:45:56. AM +08:00',28,'25-NOV-2013 10:45:56. AM +08:00',NULL,NULL,30,250,1616,NULL,NULL);

CREATE TABLE PRODUCT_GROUP_LIVE
    (PRODUCTGROUPID                 NUMBER(10),
    PRODUCTGROUPNAME               VARCHAR2(255),
    PRODUCTGROUPCODE               VARCHAR2(50),
    GLCODE                         VARCHAR2(50),
    DESCRIPTION                    VARCHAR2(500),
    REMARKS                        VARCHAR2(1000),
    ISTAXINCLUSIVE                 CHAR(1),
    TAX_PERCENT_ID                 NUMBER(10),
    KEYWORDSEARCH                  VARCHAR2(255),
    PORTALURL                      VARCHAR2(500),
    REPORTVISIBILITY               CHAR(1),
    FINANCIALREPORTVISIBILITY      CHAR(1),
    PRODUCTOWNER                   VARCHAR2(128),
    INTERNETCONTENTCODE            VARCHAR2(50),
    CREATED_BY                     NUMBER(10),
    CREATEDDATE                    TIMESTAMP (0),
    UPDATED_BY                     NUMBER(10,0),
    UPDATEDDATE                    TIMESTAMP (0),
    WEIGHT                         NUMBER(5)),
    FESTIVAL_CODE_ID               NUMBER(10),
    CATEGORY_CODE_MCT_ID           NUMBER(10),
    SPAN_CODE_MCT_ID               NUMBER(10),
    SALESPIC                       NVARCHAR2(128),
    EVENTBUILDER                   NVARCHAR2(255));
    
INSERT INTO Product_Group_live 
VALUES(1,'FRENCH MOVIES 2013 (Test)','french1013','french1013',NULL,NULL,'T',1,'French Movie 2013',NULL,'T','T','Melody Tang','french1013',28,'28-OCT-2013 6:52:17. PM +08:00',28,'28-OCT-2013 6:52:17. PM +08:00',NULL,NULL,26,NULL,NULL,NULL);
INSERT INTO Product_Group_live 
VALUES(2,'Spanish Movie Fiesta 2013 (Test)','spain1113','spain1113',NULL,NULL,'T',1,'Spanish Movie Fiesta 2013 (Test)','http://192.168.10.238/events/spain1113','T','T','allison','spain1113',30,'6-JAN-2014 3:09:42. PM +08:00',30,'6-JAN-2014 3:09:42. PM +08:00',NULL,NULL,26,NULL,NULL,NULL);
INSERT INTO Product_Group_live 
VALUES(3,'Singapore Marvel Film Festival (Aksaas Test)','smff2013','smff2013','Test1',NULL,'T',1,'Singapore Marvel Film Festival (Aksaas Test)',NULL,'T','T','dannytan','smff2013',30,'9-JUN-2014 6:56:49. PM +08:00',30,'9-JUN-2014 6:56:49. PM +08:00',NULL,NULL,26,NULL,NULL,NULL);
INSERT INTO Product_Group_live 
VALUES(4,'Poprocks Live In Concert (Test)','pop1213','pop1213',NULL,NULL,'T',1,'Poprocks Live In Concert (Test)',NULL,'T','T','allison','pop1213',30,'14-JAN-2014 9:50:12. AM +08:00',30,'14-JAN-2014 9:50:12. AM +08:00',NULL,NULL,26,NULL,NULL,NULL);
INSERT INTO Product_Group_live 
VALUES(5,'A Spanish Tenor In Concert (Test)','tenor2013','tenor2013',NULL,NULL,'T',1,'A Spanish Tenor In Concert (Test)',NULL,'T','T','allison','tenor1213',28,'25-NOV-2013 10:45:54. AM +08:00',28,'25-NOV-2013 10:45:54. AM +08:00',NULL,NULL,26,30,NULL,NULL);
INSERT INTO Product_Group_live 
VALUES(6,'Living Art Festival (Test)','living1113','living1113',NULL,NULL,'T',1,'Living Art Festival (Test)','http://192.168.10.238/events/living2014','T','T','allison','living1113',30,'7-JAN-2014 10:52:39. AM +08:00',30,'7-JAN-2014 10:52:39. AM +08:00',NULL,NULL,26,30,NULL,NULL);
INSERT INTO Product_Group_live 
VALUES(7,'Yoga Masters (GA Hot Show Test)','yoga0214','yoga0214',NULL,NULL,'T',1,'Yoga, Yoga Masters, Masters',NULL,'T','T','valerietan','yoga0214',30,'24-JAN-2014 2:31:28. PM +08:00',30,'24-JAN-2014 2:31:28. PM +08:00',NULL,NULL,26,30,NULL,NULL);
INSERT INTO Product_Group_live 
VALUES(8,'Magic Masters (RS Hot Show Test)','magic0214','magic0214',NULL,NULL,'T',1,'Magic Masters, Magic, magic, masters','http://www.sistic.com.sg/events/magic0214','T','T','JudiaCheow','magic0214',30,'9-JUN-2014 7:19:24. PM +08:00',30,'9-JUN-2014 7:19:24. PM +08:00',NULL,NULL,26,30,NULL,NULL);
INSERT INTO Product_Group_live 
VALUES(9,'Valentines Love Concert (Test)','valentines0214','valentines0214',NULL,NULL,'T',1,'Valentines Love Concert','http://www.sistic.com.sg/events/valentines0214','T','T','PeiyiKam',NULL,78,'20-JAN-2014 10:18:40. AM +08:00',78,'20-JAN-2014 10:18:40. AM +08:00',NULL,NULL,26,30,NULL,NULL);

CREATE TABLE PRDT_GROUP_PROMOTER_LIVE
    (PRDTGRPPROMOTERID              NUMBER(10)
    PRDT_GRP_ID                    NUMBER(10),
    PROMOTER_ID                    NUMBER(10),
    PROMOTERGLCODE                 VARCHAR2(50));

INSERT INTO PRDT_GROUP_PROMOTER_LIVE 
VALUES(1,1,1,NULL);
INSERT INTO PRDT_GROUP_PROMOTER_LIVE 
VALUES(2,2,1,NULL);
INSERT INTO PRDT_GROUP_PROMOTER_LIVE 
VALUES(3,3,1,NULL);
INSERT INTO PRDT_GROUP_PROMOTER_LIVE 
VALUES(4,4,2,NULL);
INSERT INTO PRDT_GROUP_PROMOTER_LIVE 
VALUES(5,5,3,NULL);
INSERT INTO PRDT_GROUP_PROMOTER_LIVE 
VALUES(6,6,3,NULL);
INSERT INTO PRDT_GROUP_PROMOTER_LIVE 
VALUES(7,7,2,NULL);
INSERT INTO PRDT_GROUP_PROMOTER_LIVE 
VALUES(8,8,1,NULL);
INSERT INTO PRDT_GROUP_PROMOTER_LIVE 
VALUES(9,9,4,NULL);
INSERT INTO PRDT_GROUP_PROMOTER_LIVE 
VALUES(10,10,4,NULL);

CREATE TABLE ORGANIZATION
    (ORGANIZATIONID                 NUMBER(10),
    ORGANIZATIONNAME               VARCHAR2(255),
    REGISTRATIONNO                 VARCHAR2(50),
    ISTENANT                       CHAR(1),
    KEYALIAS                       VARCHAR2(255),
    ENCRYPTIONALGORITHM            VARCHAR2(255),
    ORGANIZATIONURL                VARCHAR2(500),
    CREATEDDATE                    TIMESTAMP (0),
    CREATED_BY                     NUMBER(10),
    UPDATEDDATE                    TIMESTAMP (0),
    UPDATED_BY                     NUMBER(10));
    
INSERT INTO ORGANIZATION 
VALUES(1,'Gevorg Sargsyan',NULL,'F',NULL,NULL,NULL,'25-SEP-2013 4:42:09. PM +08:00',1,NULL,NULL);
INSERT INTO ORGANIZATION 
VALUES(2,'Reitoku Kai',NULL,'F',NULL,NULL,NULL,'25-SEP-2013 4:42:09. PM +08:00',1,NULL,NULL);
INSERT INTO ORGANIZATION 
VALUES(3,'The Esplanade Co Ltd',NULL,'F',NULL,NULL,NULL,'25-SEP-2013 4:42:09. PM +08:00',1,NULL,NULL);
INSERT INTO ORGANIZATION 
VALUES(4,'AEGThemeSTAR',NULL,'F',NULL,NULL,NULL,'25-SEP-2013 4:42:09. PM +08:00',1,NULL,NULL);
INSERT INTO ORGANIZATION 
VALUES(5,'Jade Group Intl. Pte. Ltd.','200722795D','F',NULL,NULL,NULL,'25-SEP-2013 4:42:09. PM +08:00',1,NULL,NULL);
INSERT INTO ORGANIZATION 
VALUES(6,'Datta Yoga Centre',NULL,'F',NULL,NULL,NULL,'25-SEP-2013 4:42:09. PM +08:00',1,NULL,NULL);

CREATE TABLE PATRON_PROFILE
    (PATRONPROFILEID                NUMBER(10),
    IDENTITYNO                     VARCHAR2(30),
    ACCNO                          NUMBER(10),
    STATUS                         NUMBER(1),
    FIRSTNAME                      VARCHAR2(100),
    LASTNAME                       VARCHAR2(100),
    EMAILADDRESS                   VARCHAR2(75),
    ISRECEIVETICKETINGAGENT        CHAR(1),
    ISRECEIVEPROMOTER              CHAR(1),
    ISRECEIVEVENUE                 CHAR(1),
    ISBILLINGADDRESSSAMEASMAILING  CHAR(1),
    ISDONOTSIGNUP                  CHAR(1),
    ORGANIZATIONNAME               VARCHAR2(150),
    COMPANYID                      VARCHAR2(255),
    COMPANYNAME                    VARCHAR2(255),
    EMPLOYEEID                     VARCHAR2(150),
    GUESTOF                        VARCHAR2(255),
    EXTERNALID                     VARCHAR2(255),
    ACCTREMARK                     VARCHAR2(1000),
    ACC_TYPE_MCT_ID                NUMBER(10),
    GENDER_MCT_ID                  NUMBER(10),
    TITLE_MCT_ID                   NUMBER(10),
    NATIONALITY                    VARCHAR2(2),
    COUNTRYOFRESIDENCE             VARCHAR2(2),
    CREATEDDATE                    TIMESTAMP (0),
    CREATED_BY                     NUMBER(10),
    UPDATEDDATE                    TIMESTAMP (0),
    UPDATED_BY                     NUMBER(10),
    IDENTITY_NO_TYPE_MCT_ID        NUMBER(10));

INSERT INTO Patron_Profile 
VALUES(1,NULL,5000001,1,'DEVSISTIC','PATRON',NULL,'T','T','T','T','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,249,228,'KY','BS','6-JUN-2014 12:47:01. PM +08:00',59,'6-JUN-2014 12:47:01. PM +08:00',59,647);
INSERT INTO Patron_Profile 
VALUES(2,NULL,5000002,1,'DEVSISTIC','PATRON',NULL,'F','F','F','F','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,249,228,'SG','SG','6-JUN-2014 1:05:29. PM +08:00',1210,'11-JUN-2014 11:45:56. AM +08:00',59,648);
INSERT INTO Patron_Profile 
VALUES(3,NULL,590868,1,'DEVSISTIC','PATRON',NULL,'T','T','T','F','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,250,NULL,'SG','SG','27-JUL-2003 12:00:00. AM +08:00',1,'9-JUL-2013 7:55:10. AM +08:00',1,648);
INSERT INTO Patron_Profile 
VALUES(4,NULL,590864,1,'DEVSISTIC','PATRON',NULL,'F','F','F','F','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,NULL,NULL,'SG','SG','27-JUL-2003 12:00:00. AM +08:00',1209,'9-OCT-2015 5:56:47. PM +08:00',1209,646);
INSERT INTO Patron_Profile 
VALUES(5,NULL,590834,1,'DEVSISTIC','PATRON',NULL,'T','F','F','F','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,NULL,NULL,'SG','SG','27-JUL-2003 12:00:00. AM +08:00',1,'4-NOV-2011 2:15:43. PM +08:00',1,648);
INSERT INTO Patron_Profile 
VALUES(6,NULL,590820,1,'DEVSISTIC','PATRON',NULL,'F','F','F','F','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,NULL,228,'SG','SG','27-JUL-2003 12:00:00. AM +08:00',1,'12-NOV-2012 1:44:57. PM +08:00',1,648);
INSERT INTO Patron_Profile 
VALUES(7,NULL,590818,1,'DEVSISTIC','PATRON',NULL,'F','F','F','F','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,NULL,NULL,'SG','SG','27-JUL-2003 12:00:00. AM +08:00',1,'30-JUN-2012 3:06:26. PM +08:00',1,648);
INSERT INTO Patron_Profile 
VALUES(8,NULL,590813,1,'DEVSISTIC','PATRON',NULL,'F','F','F','F','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,250,230,'SG','SG','27-JUL-2003 12:00:00. AM +08:00',1,'6-NOV-2012 4:36:41. PM +08:00',1,648);
INSERT INTO Patron_Profile 
VALUES(9,NULL,590810,1,'DEVSISTIC','PATRON',NULL,'T','T','T','F','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,250,230,'SG','SG','27-JUL-2003 12:00:00. AM +08:00',1,'2-APR-2014 6:17:01. AM +08:00',1,648);
INSERT INTO Patron_Profile 
VALUES(10,NULL,590803,1,'DEVSISTIC','PATRON',NULL,'T','T','T','F','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,250,230,'CA','SG','29-JUL-2003 12:00:00. AM +08:00',1,'18-OCT-2012 6:16:20. AM +08:00',1,648);
