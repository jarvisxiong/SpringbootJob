CREATE TABLE IF NOT EXISTS email_template
    (emailtemplateid                NUMBER(10),
    type                           VARCHAR2(128),
    is_default                     CHAR(1),
    created_by                     NUMBER(10),
    createddate                    TIMESTAMP (0),
    updated_by                     NUMBER(10),
    updateddate                    TIMESTAMP (0),
    path                           VARCHAR2(1000),
    language_code                  VARCHAR2(5));
    
CREATE TABLE IF NOT EXISTS email_template_mapping
    (emailtemplatemappingid         NUMBER(10),
    entity_id                      NUMBER(10),
    email_template_id              NUMBER(10),
    created_by                     NUMBER(10),
    createddate                    TIMESTAMP (0),
    updated_by                     NUMBER(10),
    updateddate                    TIMESTAMP (0),
    type                           NUMBER(1));
    
CREATE TABLE IF NOT EXISTS profile_info
    (profileinfoid                  NUMBER(10),
    profilecode                    VARCHAR2(50),
    profilename                    VARCHAR2(50),
    sellerstatus                   CHAR(1),
    profile_channel_id             NUMBER(10),
    ispaymentoffline               CHAR(1),
    paymentmode                    NUMBER(1),
    immediateprintingstatus        CHAR(1),
    created_by                     NUMBER(10),
    createddate                    TIMESTAMP (0),
    updated_by                     NUMBER(10),
    updateddate                    TIMESTAMP (0),
    usethermalprinter              CHAR(1));
    
truncate table email_template;
truncate table email_template_mapping;
truncate table profile_info;

INSERT INTO EMAIL_TEMPLATE (EMAILTEMPLATEID, TYPE, IS_DEFAULT, CREATED_BY, CREATEDDATE, UPDATED_BY, UPDATEDDATE, PATH, LANGUAGE_CODE) VALUES (1, 'Registration', 'Y', 1, sysdate, 1, sysdate,'/SISTIC/velocity/newPatronInternetAccount.vm', 'en');  
INSERT INTO EMAIL_TEMPLATE_MAPPING (EMAILTEMPLATEMAPPINGID, ENTITY_ID, EMAIL_TEMPLATE_ID, CREATED_BY, CREATEDDATE, UPDATED_BY, UPDATEDDATE, TYPE) VALUES (1, 1, (select max(emailtemplateid) from email_template), 1, sysdate, 1, sysdate, 2); 
INSERT INTO EMAIL_TEMPLATE (EMAILTEMPLATEID, TYPE, IS_DEFAULT, CREATED_BY, CREATEDDATE, UPDATED_BY, UPDATEDDATE, PATH, LANGUAGE_CODE) VALUES (2, 'ForgotPassword', 'Y', 1, sysdate, 1, sysdate,'/SISTIC/velocity/sendToken.vm', 'en');  
INSERT INTO EMAIL_TEMPLATE_MAPPING (EMAILTEMPLATEMAPPINGID, ENTITY_ID, EMAIL_TEMPLATE_ID, CREATED_BY, CREATEDDATE, UPDATED_BY, UPDATEDDATE, TYPE) VALUES (2, 1, (select max(emailtemplateid) from email_template), 1, sysdate, 1, sysdate, 2);
    
INSERT INTO profile_info(profileinfoid, profilecode, profilename, sellerstatus, profile_channel_id, ispaymentoffline, paymentmode,
immediateprintingstatus, created_by, createddate, updated_by, updateddate, usethermalprinter) VALUES (11,null,'SISTIC Internet','Y',1,0,1,'N',4,sysdate,3,sysdate,'N');