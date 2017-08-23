truncate table address;
truncate table patron_phone;
INSERT INTO patron_phone(patronphoneid, isprimary, patron_profile_id, phonetype, country_id, areacode, phonenumber) 
VALUES(3135703,'T',1921828,'MOBILE',1,NULL,'3333333');
INSERT INTO patron_phone(patronphoneid, isprimary, patron_profile_id, phonetype, country_id, areacode, phonenumber) 
VALUES(3135704,'T',1921828,'HOME',1,NULL,'4444444');
