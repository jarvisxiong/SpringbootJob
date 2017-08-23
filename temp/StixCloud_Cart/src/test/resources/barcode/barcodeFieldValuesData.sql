truncate table BARCODE_FIELD_VIEW;

INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,1801,'TICKET_ID',9,'0',NULL,1,-1,0);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,1801,'ACSBARCODEREGENCOUNT',2,'0',NULL,1,-1,1);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,1801,'EVENT_ID',9,'0',NULL,1,-1,2);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,1801,'SECTION_CODE',2,'0',NULL,1,-1,3);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,1801,'PRICE_CLASS_CODE',9,'0',NULL,1,-1,4);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,1801,'TRANSACTION_ID',9,'0',NULL,1,-1,5);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,1801,'REPRINTED_COUNT',4,'0',NULL,1,-1,6);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,1801,'VENUE_ID',2,'0',NULL,1,-1,7);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,2300,'EVENT_ID',3,'0',NULL,1,1,0);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,2300,'ACSBARCODEREGENCOUNT',-1,'0',NULL,1,1,1);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,2307,'BARCODE_PREFIX',3,'0',56,1,1,0);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,2307,'CHECKSUM',-1,'0',1,1,1,1);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(3,4,2307,'ACSBARCODEREGENCOUNT',-1,'0',NULL,1,1,1);

CREATE TABLE if not exists venue_section_lc
    (venuesectionlcid               NUMBER(10) NOT NULL,
    lc_id                          NUMBER(10) NOT NULL,
    level_id                       NUMBER(10) NOT NULL,
    sectionalias                   VARCHAR2(50),
    areaalias                      VARCHAR2(255),
    noofseats                      NUMBER(5),
    sectiontype                    VARCHAR2(2),
    nearest_entrance_id            NUMBER(10),
    rank                           NUMBER(10),
    isseatnumpreview               CHAR(1));

truncate table VENUE_SECTION_LC;
    
INSERT INTO VENUE_SECTION_LC (VENUESECTIONLCID,LC_ID,LEVEL_ID,SECTIONALIAS,AREAALIAS,NOOFSEATS,SECTIONTYPE,NEAREST_ENTRANCE_ID,RANK,ISSEATNUMPREVIEW) 
VALUES(112,51,51,'Block C','Block C',0,'RS',NULL,0,'F');
INSERT INTO VENUE_SECTION_LC (VENUESECTIONLCID,LC_ID,LEVEL_ID,SECTIONALIAS,AREAALIAS,NOOFSEATS,SECTIONTYPE,NEAREST_ENTRANCE_ID,RANK,ISSEATNUMPREVIEW) 
VALUES(113,51,51,'Block M','Block M',0,'RS',NULL,0,'F');
INSERT INTO VENUE_SECTION_LC (VENUESECTIONLCID,LC_ID,LEVEL_ID,SECTIONALIAS,AREAALIAS,NOOFSEATS,SECTIONTYPE,NEAREST_ENTRANCE_ID,RANK,ISSEATNUMPREVIEW) 
VALUES(200,150,150,'FREE SEATING','General Admission',236,'GA',51,0,'F');

CREATE TABLE if not exists sales_seat_inventory
    (salesseatinventoryid           NUMBER(10) NOT NULL,
    product_id                     NUMBER(10),
    rank                           NUMBER(10),
    seatsalesstatus                NUMBER(1),
    ticketable                     NUMBER(1),
    price_cat_id                   NUMBER(10),
    price_class_id                 NUMBER(10),
    patron_id                      NUMBER(10),
    reserveexpirydate              TIMESTAMP (0),
    system_sale_code_id            NUMBER(10),
    hold_by                        NUMBER(10),
    holddate                       TIMESTAMP (0),
    sold_by                        NUMBER(10),
    solddate                       TIMESTAMP (0),
    updatedtime                    TIMESTAMP (0),
    updated_by                     NUMBER(10) NOT NULL,
    holdreason                     VARCHAR2(500),
    holdexpirynotified             TIMESTAMP (0),
    sectionrank                    NUMBER(10),
    spankey                        VARCHAR2(512),
    venue_seat_lc_id               NUMBER(10),
    venue_section_lc_id            NUMBER(10),
    salescyclestarttime            TIMESTAMP (0),
    package_id                     NUMBER(10),
    packagerequirement_id          NUMBER(10),
    pricevalue                     NUMBER(20),
    sessionid                      VARCHAR2(100),
    holdexpirydate                 TIMESTAMP (0));
    
truncate table SALES_SEAT_INVENTORY;

INSERT INTO SALES_SEAT_INVENTORY (SALESSEATINVENTORYID,PRODUCT_ID,RANK,SEATSALESSTATUS,TICKETABLE,PRICE_CAT_ID,PRICE_CLASS_ID,PATRON_ID,RESERVEEXPIRYDATE,SYSTEM_SALE_CODE_ID,HOLD_BY,HOLDDATE,SOLD_BY,SOLDDATE,UPDATEDTIME,UPDATED_BY,HOLDREASON,HOLDEXPIRYNOTIFIED,SECTIONRANK,SPANKEY,VENUE_SEAT_LC_ID,VENUE_SECTION_LC_ID,SALESCYCLESTARTTIME,PACKAGE_ID,PACKAGEREQUIREMENT_ID,PRICEVALUE,SESSIONID,HOLDEXPIRYDATE) 
VALUES(774,NULL,67,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),30,NULL,NULL,0,'Esplanade Recital StudioBlock EStallsE18',115,113,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO SALES_SEAT_INVENTORY (SALESSEATINVENTORYID,PRODUCT_ID,RANK,SEATSALESSTATUS,TICKETABLE,PRICE_CAT_ID,PRICE_CLASS_ID,PATRON_ID,RESERVEEXPIRYDATE,SYSTEM_SALE_CODE_ID,HOLD_BY,HOLDDATE,SOLD_BY,SOLDDATE,UPDATEDTIME,UPDATED_BY,HOLDREASON,HOLDEXPIRYNOTIFIED,SECTIONRANK,SPANKEY,VENUE_SEAT_LC_ID,VENUE_SECTION_LC_ID,SALESCYCLESTARTTIME,PACKAGE_ID,PACKAGEREQUIREMENT_ID,PRICEVALUE,SESSIONID,HOLDEXPIRYDATE) 
VALUES(775,NULL,88,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),30,NULL,NULL,0,'Esplanade Recital StudioBlock EStallsF12',116,113,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO SALES_SEAT_INVENTORY (SALESSEATINVENTORYID,PRODUCT_ID,RANK,SEATSALESSTATUS,TICKETABLE,PRICE_CAT_ID,PRICE_CLASS_ID,PATRON_ID,RESERVEEXPIRYDATE,SYSTEM_SALE_CODE_ID,HOLD_BY,HOLDDATE,SOLD_BY,SOLDDATE,UPDATEDTIME,UPDATED_BY,HOLDREASON,HOLDEXPIRYNOTIFIED,SECTIONRANK,SPANKEY,VENUE_SEAT_LC_ID,VENUE_SECTION_LC_ID,SALESCYCLESTARTTIME,PACKAGE_ID,PACKAGEREQUIREMENT_ID,PRICEVALUE,SESSIONID,HOLDEXPIRYDATE) 
VALUES(776,NULL,87,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),30,NULL,NULL,0,'Esplanade Recital StudioBlock EStallsF13',117,103,NULL,NULL,NULL,NULL,NULL,NULL);

CREATE TABLE if not exists price_class_live
    (priceclassid                   NUMBER(10) NOT NULL,
    priceclassname                 VARCHAR2(100) NOT NULL,
    priceclasscode                 VARCHAR2(10) NOT NULL,
    priceclassdescription          VARCHAR2(255),
    iscomplimentaryticket          CHAR(1) NOT NULL,
    ispackageclass                 CHAR(1) NOT NULL,
    discountrule                   NUMBER(1) NOT NULL,
    pricevaluerule                 NUMBER(1) NOT NULL,
    spanquota                      NUMBER(10),
    buyticketsquantity             NUMBER(3),
    getticketquantity              NUMBER(3),
    offvalue                       NUMBER(20),
    pvpriceclass_id                NUMBER(10),
    discountpriceclass_id          NUMBER(10),
    typeofdiscount_mct_id          NUMBER(10),
    promotionpassword_id           NUMBER(10),
    creditcardrange_id             NUMBER(10),
    productgroup_id                NUMBER(10) NOT NULL,
    validity_period                VARCHAR2(255),
    channels                       VARCHAR2(255),
    createddate                    TIMESTAMP (0),
    created_by                     NUMBER(10) NOT NULL,
    updateddate                    TIMESTAMP (0),
    updated_by                     NUMBER(10) NOT NULL,
    ordering                       NUMBER(3) NOT NULL);
    
truncate table PRICE_CLASS_LIVE;
    
INSERT INTO PRICE_CLASS_LIVE (PRICECLASSID,PRICECLASSNAME,PRICECLASSCODE,PRICECLASSDESCRIPTION,ISCOMPLIMENTARYTICKET,ISPACKAGECLASS,DISCOUNTRULE,PRICEVALUERULE,SPANQUOTA,BUYTICKETSQUANTITY,GETTICKETQUANTITY,OFFVALUE,PVPRICECLASS_ID,DISCOUNTPRICECLASS_ID,TYPEOFDISCOUNT_MCT_ID,PROMOTIONPASSWORD_ID,CREDITCARDRANGE_ID,PRODUCTGROUP_ID,VALIDITY_PERIOD,CHANNELS,CREATEDDATE,CREATED_BY,UPDATEDDATE,UPDATED_BY,ORDERING) 
VALUES(4,'Complimentary','C','Complimentary Tickets ','T','F',1,1,0,0,0,0,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),28,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),28,0);
INSERT INTO PRICE_CLASS_LIVE (PRICECLASSID,PRICECLASSNAME,PRICECLASSCODE,PRICECLASSDESCRIPTION,ISCOMPLIMENTARYTICKET,ISPACKAGECLASS,DISCOUNTRULE,PRICEVALUERULE,SPANQUOTA,BUYTICKETSQUANTITY,GETTICKETQUANTITY,OFFVALUE,PVPRICECLASS_ID,DISCOUNTPRICECLASS_ID,TYPEOFDISCOUNT_MCT_ID,PROMOTIONPASSWORD_ID,CREDITCARDRANGE_ID,PRODUCTGROUP_ID,VALIDITY_PERIOD,CHANNELS,CREATEDDATE,CREATED_BY,UPDATEDDATE,UPDATED_BY,ORDERING) 
VALUES(1,'Standard','A','Standard Price ','F','F',1,1,0,0,0,0,NULL,NULL,NULL,NULL,NULL,1,NULL,'All',TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),28,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),28,0);
INSERT INTO PRICE_CLASS_LIVE (PRICECLASSID,PRICECLASSNAME,PRICECLASSCODE,PRICECLASSDESCRIPTION,ISCOMPLIMENTARYTICKET,ISPACKAGECLASS,DISCOUNTRULE,PRICEVALUERULE,SPANQUOTA,BUYTICKETSQUANTITY,GETTICKETQUANTITY,OFFVALUE,PVPRICECLASS_ID,DISCOUNTPRICECLASS_ID,TYPEOFDISCOUNT_MCT_ID,PROMOTIONPASSWORD_ID,CREDITCARDRANGE_ID,PRODUCTGROUP_ID,VALIDITY_PERIOD,CHANNELS,CREATEDDATE,CREATED_BY,UPDATEDDATE,UPDATED_BY,ORDERING) 
VALUES(3,'Members of Singapore Film Society','DA','Members Discounts ','F','F',1,1,0,0,0,0,NULL,NULL,NULL,NULL,NULL,1,NULL,'Through SISTIC Authorized Agents Only ',TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),28,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),28,0);

CREATE TABLE if not exists product_live
    (productid                      NUMBER(10) NOT NULL,
    product_group_id               NUMBER(10) NOT NULL,
    productname                    VARCHAR2(255) NOT NULL,
    productcode                    VARCHAR2(50) NOT NULL,
    glcode                         VARCHAR2(50) NOT NULL,
    description                    VARCHAR2(500),
    remarks                        VARCHAR2(1000),
    keywordsearch                  VARCHAR2(255),
    reportvisibility               CHAR(1) NOT NULL,
    financialreportvisibility      CHAR(1) NOT NULL,
    productowner                   VARCHAR2(128),
    weight                         NUMBER(5),
    salesstatus                    NUMBER(1) NOT NULL,
    activationstatus               NUMBER(1) NOT NULL,
    maxticketsappl                 NUMBER(3),
    startdate                      TIMESTAMP (0),
    enddate                        TIMESTAMP (0),
    created_by                     NUMBER(10),
    createddate                    TIMESTAMP (0),
    updated_by                     NUMBER(10) NOT NULL,
    updateddate                    TIMESTAMP (0),
    stop_sales_reason_mct_id       NUMBER(10),
    festival_code_id               NUMBER(10),
    span_code_mct_id               NUMBER(10),
    venue_id                       NUMBER(10) NOT NULL,
    venuelayoutconfig_id           NUMBER(10),
    salespic                       VARCHAR2(128),
    eventbuilder                   VARCHAR2(255));
    
truncate table product_live;
    
INSERT INTO PRODUCT_LIVE (PRODUCTID,PRODUCT_GROUP_ID,PRODUCTNAME,PRODUCTCODE,GLCODE,DESCRIPTION,REMARKS,KEYWORDSEARCH,REPORTVISIBILITY,FINANCIALREPORTVISIBILITY,PRODUCTOWNER,WEIGHT,SALESSTATUS,ACTIVATIONSTATUS,MAXTICKETSAPPL,STARTDATE,ENDDATE,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE,STOP_SALES_REASON_MCT_ID,FESTIVAL_CODE_ID,SPAN_CODE_MCT_ID,VENUE_ID,VENUELAYOUTCONFIG_ID,SALESPIC,EVENTBUILDER) 
VALUES(3,1,'LOLA (PG) (Test)','eaft1311222000','french1013',NULL,NULL,'French Movie 2013','T','T','Melody Tang',NULL,1,1,NULL,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),NULL,28,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),28,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),14,NULL,NULL,50,151,NULL,NULL);
INSERT INTO PRODUCT_LIVE (PRODUCTID,PRODUCT_GROUP_ID,PRODUCTNAME,PRODUCTCODE,GLCODE,DESCRIPTION,REMARKS,KEYWORDSEARCH,REPORTVISIBILITY,FINANCIALREPORTVISIBILITY,PRODUCTOWNER,WEIGHT,SALESSTATUS,ACTIVATIONSTATUS,MAXTICKETSAPPL,STARTDATE,ENDDATE,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE,STOP_SALES_REASON_MCT_ID,FESTIVAL_CODE_ID,SPAN_CODE_MCT_ID,VENUE_ID,VENUELAYOUTCONFIG_ID,SALESPIC,EVENTBUILDER) 
VALUES(2,1,'LOLA (PG) (Test)','eaft1312222000','french1013',NULL,NULL,'French Movie 2013','T','T','Melody Tang',NULL,1,1,NULL,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),NULL,28,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),28,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),14,NULL,NULL,50,150,NULL,NULL);
INSERT INTO PRODUCT_LIVE (PRODUCTID,PRODUCT_GROUP_ID,PRODUCTNAME,PRODUCTCODE,GLCODE,DESCRIPTION,REMARKS,KEYWORDSEARCH,REPORTVISIBILITY,FINANCIALREPORTVISIBILITY,PRODUCTOWNER,WEIGHT,SALESSTATUS,ACTIVATIONSTATUS,MAXTICKETSAPPL,STARTDATE,ENDDATE,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE,STOP_SALES_REASON_MCT_ID,FESTIVAL_CODE_ID,SPAN_CODE_MCT_ID,VENUE_ID,VENUELAYOUTCONFIG_ID,SALESPIC,EVENTBUILDER) 
VALUES(16,1,'LOLA (PG) (Test)','eaft1310222000','french1013',NULL,NULL,'French Movie 2013','T','T','Melody Tang',NULL,0,1,NULL,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),NULL,28,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),28,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),NULL,NULL,NULL,50,200,NULL,NULL);

CREATE TABLE if not exists acs_venue_config
    (acsvenueconfigid               NUMBER(10) NOT NULL,
    venue_id                       NUMBER(10),
    created_by                     NUMBER(10) NOT NULL,
    createddate                    TIMESTAMP (0),
    updated_by                     NUMBER(10) NOT NULL,
    updateddate                    TIMESTAMP (0),
    acs_config_id                  NUMBER(10) NOT NULL);
    
truncate table acs_venue_config;
    
INSERT INTO ACS_VENUE_CONFIG (ACSVENUECONFIGID,VENUE_ID,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE,ACS_CONFIG_ID) 
VALUES(3,50,25,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),25,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),4);
INSERT INTO ACS_VENUE_CONFIG (ACSVENUECONFIGID,VENUE_ID,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE,ACS_CONFIG_ID) 
VALUES(4,51,25,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),25,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),4);
INSERT INTO ACS_VENUE_CONFIG (ACSVENUECONFIGID,VENUE_ID,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE,ACS_CONFIG_ID) 
VALUES(5,250,25,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),25,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),4);

CREATE TABLE if not exists acs_config
    (acsconfigid                    NUMBER(10) NOT NULL,
    alias                          VARCHAR2(50),
    serverip                       VARCHAR2(150) NOT NULL,
    serverport                     NUMBER(10),
    isactive                       CHAR(1) NOT NULL,
    acs_interface_id               NUMBER(10),
    location_id                    NUMBER(10) NOT NULL,
    created_by                     NUMBER(10) NOT NULL,
    createddate                    TIMESTAMP (0),
    updated_by                     NUMBER(10) NOT NULL,
    updateddate                    TIMESTAMP (0));
    
truncate table acs_config;
    
INSERT INTO ACS_CONFIG (ACSCONFIGID,ALIAS,SERVERIP,SERVERPORT,ISACTIVE,ACS_INTERFACE_ID,LOCATION_ID,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE) 
VALUES(4,'SISTIC_ACS@Esplanade','192.168.11.57',28080,'1',1,51,25,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),25,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO ACS_CONFIG (ACSCONFIGID,ALIAS,SERVERIP,SERVERPORT,ISACTIVE,ACS_INTERFACE_ID,LOCATION_ID,CREATED_BY,CREATEDDATE,UPDATED_BY,UPDATEDDATE) 
VALUES(5,'SISTIC_ACS@NGS','192.168.11.57',28080,'1',3,3500,25,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'),25,TO_TIMESTAMP('2017-01-03 12:20:59', 'YYYY-MM-DD HH24:MI:SS'));

CREATE TABLE if not exists access_control_interface
    (accesscontrolinterfaceid       NUMBER(10) NOT NULL,
    pacmodule                      VARCHAR2(255));
    
truncate table access_control_interface;

INSERT INTO ACCESS_CONTROL_INTERFACE (ACCESSCONTROLINTERFACEID,PACMODULE) 
VALUES(1,'STAR');
INSERT INTO ACCESS_CONTROL_INTERFACE (ACCESSCONTROLINTERFACEID,PACMODULE) 
VALUES(2,'SISTIC');
INSERT INTO ACCESS_CONTROL_INTERFACE (ACCESSCONTROLINTERFACEID,PACMODULE) 
VALUES(3,'CLOUDACS');

CREATE TABLE if not exists prdt_mapping_config
    (prdt_mapping_configid          NUMBER(10) NOT NULL,
    internal_prodt_id              NUMBER(10) NOT NULL,
    external_prodt_id              NUMBER(10) NOT NULL);
    
truncate table prdt_mapping_config;
    
INSERT INTO PRDT_MAPPING_CONFIG (PRDT_MAPPING_CONFIGID,INTERNAL_PRODT_ID,EXTERNAL_PRODT_ID) 
VALUES(1,3,70501);
INSERT INTO PRDT_MAPPING_CONFIG (PRDT_MAPPING_CONFIGID,INTERNAL_PRODT_ID,EXTERNAL_PRODT_ID) 
VALUES(2,122000,70502);

