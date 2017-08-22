package com.cmcglobal.senseinfosys.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JobLog.class)
public abstract class JobLog_ {

	public static volatile SingularAttribute<JobLog, String> jobName;
	public static volatile SingularAttribute<JobLog, Integer> mode;
	public static volatile SingularAttribute<JobLog, String> groupId;
	public static volatile SingularAttribute<JobLog, String> description;
	public static volatile SingularAttribute<JobLog, Date> startTime;
	public static volatile SingularAttribute<JobLog, String> id;
	public static volatile SingularAttribute<JobLog, Date> endTime;
	public static volatile SingularAttribute<JobLog, Integer> status;

}

