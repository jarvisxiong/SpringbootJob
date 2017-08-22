/**
 * 
 */

var endPoint = "http://localhost:9099/";

var JOB_STATUS_KEY = {
		"RUNNING_STATUS": 2,
		"FAILURE_STATUS": -1,
		"SUCCESS_STATUS": 1,
};

var JOB_STATUS_VALUE = {
		"RUNNING_STATUS": "Running",
		"FAILURE_STATUS": "Failure",
		"SUCCESS_STATUS": "Success",
};

var APP_MODE_KEY = {
		"AUTO": 10,
		"MANUAL": 11
};

var APP_MODE_VALUE = {
		"AUTO": "Auto",
		"MANUAL": "Manual"
}

var JOB_KEY = {
	    "ID": "id",
	    "GROUP_ID": "groupId",
	    "JOB_NAME": "jobName",
	    "DESC": "description",
	    "STATUS": "status",
	    "START_TIME": "startTime",
	    "END_TIME": "endTime",
	    "MODE": "mode"
}
