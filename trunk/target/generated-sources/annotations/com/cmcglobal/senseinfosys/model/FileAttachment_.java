package com.cmcglobal.senseinfosys.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FileAttachment.class)
public abstract class FileAttachment_ {

	public static volatile SingularAttribute<FileAttachment, String> fileName;
	public static volatile SingularAttribute<FileAttachment, String> attachmentType;
	public static volatile SingularAttribute<FileAttachment, Integer> attachmentIndex;
	public static volatile SingularAttribute<FileAttachment, Integer> incidentCaseId;
	public static volatile SingularAttribute<FileAttachment, String> externalId;
	public static volatile SingularAttribute<FileAttachment, Integer> id;
	public static volatile SingularAttribute<FileAttachment, String> fileContent;

}

