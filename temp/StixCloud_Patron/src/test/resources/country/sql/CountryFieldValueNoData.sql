CREATE TABLE if not exists country
    (countryid                     NUMBER(10) NOT NULL,
    countrycode                    VARCHAR2(2) NOT NULL,
    countrycallingcode             VARCHAR2(4) NOT NULL,
    currencysymbol                 VARCHAR2(10) NOT NULL,
    currencycode                   VARCHAR2(10));

truncate table COUNTRY;