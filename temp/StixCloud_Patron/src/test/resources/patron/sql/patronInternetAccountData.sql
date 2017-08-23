truncate TABLE patron_internet_account;

INSERT INTO patron_internet_account(patroninternetaccountid, userid, password, lastlogindate, patron_profile_id, loginfailedcount) VALUES(1542542, 'dbthan@cmc.com.vn', '12345678', sysdate, 182827, 1);
INSERT INTO patron_internet_account(patroninternetaccountid, userid, password, lastlogindate, patron_profile_id, loginfailedcount) VALUES(1542543, 'cmc1@gmail.com', '12333678', sysdate, 182828, 0);
INSERT INTO patron_internet_account(patroninternetaccountid, userid, password, lastlogindate, patron_profile_id, loginfailedcount) VALUES(1542544, 'dbthancmc@gmail.com', '12333678', sysdate, 182828, 7);