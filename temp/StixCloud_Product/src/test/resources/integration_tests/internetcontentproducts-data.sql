CREATE TABLE HAS_PROMOTION_VIEW (
  PRODUCT_ID            NUMBER(10),
  PROFILE_INFO_ID       NUMBER(10),
  PRICE_CLASS_ID        NUMBER(10),
  PROMOTION_PASSWORD_ID NUMBER(10)
);

INSERT INTO HAS_PROMOTION_VIEW (PRODUCT_ID, PROFILE_INFO_ID, PRICE_CLASS_ID, PROMOTION_PASSWORD_ID)
VALUES (244186, 4, 84607, 6);
INSERT INTO HAS_PROMOTION_VIEW (PRODUCT_ID, PROFILE_INFO_ID, PRICE_CLASS_ID, PROMOTION_PASSWORD_ID)
VALUES (244187, 9, 84607, 6);
INSERT INTO HAS_PROMOTION_VIEW (PRODUCT_ID, PROFILE_INFO_ID, PRICE_CLASS_ID, PROMOTION_PASSWORD_ID)
VALUES (244188, 9, 84607, 6);
INSERT INTO HAS_PROMOTION_VIEW (PRODUCT_ID, PROFILE_INFO_ID, PRICE_CLASS_ID, PROMOTION_PASSWORD_ID)
VALUES (244188, 182, 84607, 6);
INSERT INTO HAS_PROMOTION_VIEW (PRODUCT_ID, PROFILE_INFO_ID, PRICE_CLASS_ID, PROMOTION_PASSWORD_ID)
VALUES (244189, 2, 84607, 6);
INSERT INTO HAS_PROMOTION_VIEW (PRODUCT_ID, PROFILE_INFO_ID, PRICE_CLASS_ID, PROMOTION_PASSWORD_ID)
VALUES (244190, 4, 84607, 6);
INSERT INTO HAS_PROMOTION_VIEW (PRODUCT_ID, PROFILE_INFO_ID, PRICE_CLASS_ID, PROMOTION_PASSWORD_ID)
VALUES (244191, 206, 84607, 6);
INSERT INTO HAS_PROMOTION_VIEW (PRODUCT_ID, PROFILE_INFO_ID, PRICE_CLASS_ID, PROMOTION_PASSWORD_ID)
VALUES (244191, 275, 84607, 6);
INSERT INTO HAS_PROMOTION_VIEW (PRODUCT_ID, PROFILE_INFO_ID, PRICE_CLASS_ID, PROMOTION_PASSWORD_ID)
VALUES (244192, 97, 84607, 6);
INSERT INTO HAS_PROMOTION_VIEW (PRODUCT_ID, PROFILE_INFO_ID, PRICE_CLASS_ID, PROMOTION_PASSWORD_ID)
VALUES (244192, 3, 84607, 6);

CREATE TABLE PROMOTION_CODE_USABLE_VIEW (
  PROMOTION_PASSWORD_ID NUMBER(10),
  REGEX_PATTERN         VARCHAR2(96)
);

INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (3, '^spanishtenor\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (11, '^Snoopy\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (13, '^asandler\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (13, '^Asandler\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (19, '^bigbangfan\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (20, '^emurphy\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (33, '^finalfantasy\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (34, '^BABYFACE\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (6, '^TOMANDJERRY\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (41, '^tennis\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (44, '^Garfield\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (45, '^soinlove\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (45, '^SOINLOVE\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (56, '^FROZEN');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (58, '^Labidabiwawoo\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (82, '^Run Man 5');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (57, '^Snuggly Friends\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (65, '^8300\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (65, '^5264\d{0}$');
INSERT INTO PROMOTION_CODE_USABLE_VIEW (PROMOTION_PASSWORD_ID, REGEX_PATTERN)
VALUES (120, '^safra\d{0}$');

INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (429, '^spanishtenor\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (1615, '^Snoopy\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (1726, '^asandler\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (1726, '^Asandler\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (1688, '^bigbangfan\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (1714, '^emurphy\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (1603, '^finalfantasy\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (1628, '^BABYFACE\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (84607, '^TOMANDJERRY\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (1143, '^TOMANDJERRY\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (2947, '^tennis\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (2962, '^Garfield\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (2998, '^soinlove\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (2998, '^SOINLOVE\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (7190, '^Run Man 5');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (3904, '^Snuggly Friends\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (5932, '^8300\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (5931, '^8300\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (5932, '^5264\d{0}$');
INSERT INTO PROMOTION_PRICE_CLASS_VIEW (PRICE_CLASS_ID, REGEX_PATTERN)
VALUES (5931, '^5264\d{0}$');

INSERT INTO INTERNET_CONTENT_PRODUCTS (CODE, PRODUCTID, PRODUCTNAME, STARTDATE, VENUENAME, PROFILE_INFO_ID, SUMMARYIMAGEPATH, ICATTRIBUTES, AVAILABLITY_STATUS)
VALUES ('rent2016', 243851, 'RENT', '2016-10-29 15:00:00', 'Drama Centre', 11, NULL, 1, 19);
INSERT INTO INTERNET_CONTENT_PRODUCTS (CODE, PRODUCTID, PRODUCTNAME, STARTDATE, VENUENAME, PROFILE_INFO_ID, SUMMARYIMAGEPATH, ICATTRIBUTES, AVAILABLITY_STATUS)
VALUES ('rent2016', 243853, 'RENT', '2016-10-29 20:00:00', 'Drama Centre', 11, NULL, 1, 19);
INSERT INTO INTERNET_CONTENT_PRODUCTS (CODE, PRODUCTID, PRODUCTNAME, STARTDATE, VENUENAME, PROFILE_INFO_ID, SUMMARYIMAGEPATH, ICATTRIBUTES, AVAILABLITY_STATUS)
VALUES ('rent2016', 243852, 'RENT', '2016-10-30 15:00:00', 'Drama Centre', 11, NULL, 1, 19);
INSERT INTO INTERNET_CONTENT_PRODUCTS (CODE, PRODUCTID, PRODUCTNAME, STARTDATE, VENUENAME, PROFILE_INFO_ID, SUMMARYIMAGEPATH, ICATTRIBUTES, AVAILABLITY_STATUS)
VALUES ('rent2016', 243854, 'RENT', '2016-10-30 20:00:00', 'Drama Centre', 11, NULL, 1, 19);


