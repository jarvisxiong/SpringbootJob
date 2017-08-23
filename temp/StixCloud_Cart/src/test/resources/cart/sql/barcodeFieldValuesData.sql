truncate table BARCODE_FIELD_VIEW;

INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(245634,75616,12176,'TICKET_ID',9,'0',NULL,1,-1,0);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(245634,75616,12176,'ACSBARCODEREGENCOUNT',2,'0',NULL,1,-1,1);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(245634,75616,12176,'EVENT_ID',9,'0',NULL,1,-1,2);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(245634,75616,12176,'SECTION_CODE',2,'0',NULL,1,-1,3);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(245634,75616,12176,'PRICE_CLASS_CODE',9,'0',NULL,1,-1,4);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(245634,75616,12176,'TRANSACTION_ID',9,'0',NULL,1,-1,5);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(245634,75616,12176,'REPRINTED_COUNT',4,'0',NULL,1,-1,6);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(245634,75616,12176,'VENUE_ID',2,'0',NULL,1,-1,7);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(245634,75586,12176,'EVENT_ID',3,'0',NULL,1,1,0);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(245634,75586,12176,'ACSBARCODEREGENCOUNT',-1,'0',NULL,1,1,1);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(105297,5887,2307,'BARCODE_PREFIX',3,'0',56,1,1,0);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(105297,5887,2307,'CHECKSUM',-1,'0',1,1,1,1);
INSERT INTO BARCODE_FIELD_VIEW(product_id,price_class_id,price_category_id,data_type,length,pad_value,static_value,padprefix,encrypted_type,ordering) 
VALUES(105297,5887,2307,'ACSBARCODEREGENCOUNT',-1,'0',NULL,1,1,1);

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
VALUES(356882,51,51,'Block M','Block M',0,'RS',NULL,0,'F');
INSERT INTO VENUE_SECTION_LC (VENUESECTIONLCID,LC_ID,LEVEL_ID,SECTIONALIAS,AREAALIAS,NOOFSEATS,SECTIONTYPE,NEAREST_ENTRANCE_ID,RANK,ISSEATNUMPREVIEW) 
VALUES(200,150,150,'FREE SEATING','General Admission',236,'GA',51,0,'F');

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
VALUES(1,245634,70501);
INSERT INTO PRDT_MAPPING_CONFIG (PRDT_MAPPING_CONFIGID,INTERNAL_PRODT_ID,EXTERNAL_PRODT_ID) 
VALUES(2,122000,70502);

