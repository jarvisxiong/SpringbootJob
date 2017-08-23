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
