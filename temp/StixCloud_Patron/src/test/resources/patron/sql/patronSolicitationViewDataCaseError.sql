truncate TABLE patron_solicitation_view;

INSERT INTO PATRON_SOLICITATION_VIEW(solicitation_type, organization_id, organization_name, organization_url, subcribed, patron_profile_id) 
VALUES('Tenant',152552,'SISTIC','http://abcd.com',1,5555);
INSERT INTO PATRON_SOLICITATION_VIEW(solicitation_type, organization_id, organization_name, organization_url, subcribed, patron_profile_id)  
VALUES('Promoter',152552,'Esplanade','http://ghijk.com',0,5555);