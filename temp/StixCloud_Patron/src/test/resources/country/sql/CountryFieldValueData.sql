CREATE TABLE if not exists country
    (countryid                     NUMBER(10) NOT NULL,
    countrycode                    VARCHAR2(2) NOT NULL,
    countrycallingcode             VARCHAR2(4) NOT NULL,
    currencysymbol                 VARCHAR2(10) NOT NULL,
    currencycode                   VARCHAR2(10));

truncate table COUNTRY;

INSERT INTO  COUNTRY (countryid,countrycode,countrycallingcode,currencysymbol,currencycode) 
VALUES(1,'SG',65,'$','SGD');
INSERT INTO COUNTRY (countryid,countrycode,countrycallingcode,currencysymbol,currencycode) 
VALUES(2,'AF',93,'?','AFN');
INSERT INTO COUNTRY (countryid,countrycode,countrycallingcode,currencysymbol,currencycode) 
VALUES(3,'AL',355,'LEK','ALL');
INSERT INTO COUNTRY (countryid,countrycode,countrycallingcode,currencysymbol,currencycode) 
VALUES(4,'AR',54,'$','ARS');
INSERT INTO COUNTRY (countryid,countrycode,countrycallingcode,currencysymbol,currencycode) 
VALUES(5,'AW',297,'ƒ','AWG');