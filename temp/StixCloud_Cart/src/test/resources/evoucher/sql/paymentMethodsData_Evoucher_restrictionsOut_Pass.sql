truncate TABLE PRODUCT_PAYMENT_METHOD;
truncate TABLE USER_PAYMENT_METHOD;

INSERT INTO PRODUCT_PAYMENT_METHOD(PRODUCT_ID, PAYMENT_METHOD_ID, PAYMENT_METHOD_CODE) VALUES(245632, 131, 'DBS_E_VOUCHER');
INSERT INTO PRODUCT_PAYMENT_METHOD(PRODUCT_ID, PAYMENT_METHOD_ID, PAYMENT_METHOD_CODE) VALUES(245632, 133, 'OCBC_E_VOUCHER');
INSERT INTO PRODUCT_PAYMENT_METHOD(PRODUCT_ID, PAYMENT_METHOD_ID, PAYMENT_METHOD_CODE) VALUES(245632, 137, 'MASTER_E_VOUCHER');

INSERT INTO USER_PAYMENT_METHOD(PAYMENT_METHOD_ID, PAYMENT_METHOD_CODE, PROFILE_INFO_ID, USER_INFO_ID) VALUES(140, 'SISTIC_E_VOUCHER', 11, 59);
INSERT INTO USER_PAYMENT_METHOD(PAYMENT_METHOD_ID, PAYMENT_METHOD_CODE, PROFILE_INFO_ID, USER_INFO_ID) VALUES(135, 'NG_RENEWAL_EVOUCHER', 11, 59);
INSERT INTO USER_PAYMENT_METHOD(PAYMENT_METHOD_ID, PAYMENT_METHOD_CODE, PROFILE_INFO_ID, USER_INFO_ID) VALUES(139, 'NGS_E_VOUCHER', 11, 59);
INSERT INTO USER_PAYMENT_METHOD(PAYMENT_METHOD_ID, PAYMENT_METHOD_CODE, PROFILE_INFO_ID, USER_INFO_ID) VALUES(140, 'SISTIC_E_VOUCHER', 11, 1198);
INSERT INTO USER_PAYMENT_METHOD(PAYMENT_METHOD_ID, PAYMENT_METHOD_CODE, PROFILE_INFO_ID, USER_INFO_ID) VALUES(139, 'NGS_E_VOUCHER', 11, 1198);
INSERT INTO USER_PAYMENT_METHOD(PAYMENT_METHOD_ID, PAYMENT_METHOD_CODE, PROFILE_INFO_ID, USER_INFO_ID) VALUES(138, 'ESPLANADE_E_VOUCHER', 11, 1198);