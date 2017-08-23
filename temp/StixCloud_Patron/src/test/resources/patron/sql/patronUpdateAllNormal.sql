truncate table Patron_Profile;
INSERT INTO patron_profile(patronprofileid, identityno, accno, status, firstname, lastname, emailaddress, isreceiveticketingagent, isreceivepromoter, isreceivevenue, isbillingaddresssameasmailing, isdonotsignup, organizationname, companyid, companyname, employeeid, guestof, externalid, acctremark, acc_type_mct_id, gender_mct_id, title_mct_id, nationality, countryofresidence, createddate, created_by, updateddate, updated_by, identity_no_type_mct_id) 
VALUES(1921828,'S999996E',5084729,1,'THAN BA','DO','dbthan@cmc.com.vn','T','F','F','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,249,228,'VN','VN',TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),59,TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),59,646);

truncate table patron_address;
INSERT INTO patron_address(patronaddressid, address_id, patron_profile_id, isprimary) 
VALUES(3021804,14420534,1921828,'F');

truncate table Address;
INSERT INTO Address(addressid, addresstype, lineone, linetwo, linethree, city, state, country, postcode, created_by, createddate, updated_by, updateddate) 
VALUES(14420534,'Mailing Address','10 Eunos Road 8','03-01','Singapore Post Building',NULL,NULL,'SG','408600',1,TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2016-10-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

truncate table patron_advance_profile;
INSERT INTO patron_advance_profile(patronadvancedprofileid, anniversary, dob, noofchildren, designation, race_mct_id, religion_mct_id, educational_level_mct_id, income_level_mct_id, marital_status_mct_id, industry_mct_id, patron_profile_id) 
VALUES(1663743,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1921828);

truncate table patron_phone;
INSERT INTO patron_phone(patronphoneid, isprimary, patron_profile_id, phonetype, country_id, areacode, phonenumber) 
VALUES(3135703,'T',1921828,'MOBILE',1,NULL,'3333333');
INSERT INTO patron_phone(patronphoneid, isprimary, patron_profile_id, phonetype, country_id, areacode, phonenumber) 
VALUES(3135704,'F',1921828,'HOME',1,NULL,'3333333');

truncate table patron_internet_account;
INSERT INTO patron_internet_account(patroninternetaccountid, userid, password, lastlogindate, patron_profile_id, loginfailedcount) 
VALUES(1369170,'dbthan@cmc.com.vn','b4e8d8d98cf8f9bec56cdc4da04a761d',NULL,1921828,0);