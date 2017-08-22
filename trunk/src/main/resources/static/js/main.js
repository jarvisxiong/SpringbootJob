$(document).ready(function() {
	$("#fromdatepicker").datepicker({
		maxDate : 'now'
	});
	$("#todatepicker").datepicker({
		maxDate : 'now'
	});

	$('#btnSubmit').on('click', function() {
		if (!doSent()) {
			event.preventDefault();
			return;
		}
	});

	getStatus();
});

var validateForm = function(from, to) {
	if ($("#fromdatepicker").datepicker("getDate") === null) {
		$("#lblError").text("Please select From date.");
		return false;
	}
	if ($("#todatepicker").datepicker("getDate") === null) {
		$("#lblError").text("Please select To date.");
		return false;
	}

	if (Date.parse(from) >= Date.parse(to)) {
		$("#lblError").text("Invalid Date Range.");
		return false;
	}
	// if(daydiff(parseDate(from), parseDate(to)) > 365){
	// $("#lblError").text("From and to date must be within 1 year period.");
	// return false;
	// }
	return true;
};

function doSent() {
	var from = $('#fromdatepicker').datepicker().val();
	var to = $('#todatepicker').datepicker().val();
	if (!validateForm(from, to)) {
		return false;
	} else {
		$("#lblError").text("");
	}

	var url = endPoint + "syncData?dateFrom=" + from + "&dateTo=" + to;
	function reactiveButton() {
		setTimeout(function() {
			$('#btnSubmit').removeAttr("disabled", "disabled");
		}, 600000);
	}

	$.ajax({
		url : url,
		headers : {
			'Content-Type' : 'application/json'
		},
		type : "GET",
		success : function(data, textStatus, xhr) {
			if (xhr.status === 200) {
				$("#lblProcess").show();
				var lastRun = new Date();
				$("#lblLastRun").text(formatDate(lastRun));
				$("#lblStatus").text(JOB_STATUS_VALUE.RUNNING_STATUS);
				$("#lblError").text("");
				$("#lblModeRun").text(APP_MODE_VALUE.MANUAL);
			}
			reactiveButton();
		},
		error : function(xhr) {
			if (xhr.status === 400 || xhr.status === 500) {
				$("#lblError").text(xhr.responseText);
			}
			reactiveButton();
		}
	});
	$('#btnSubmit').attr("disabled", "disabled");
};

function getStatus() {
	$.ajax({
		url : endPoint + "status",
		headers : {
			'Content-Type' : 'application/json'
		},
		type : "GET",
		success : function(data) {
			if (data == null) {
				$("#lblError").text("There are no jobs.");
			} else {
				if (data.mode === APP_MODE_KEY.AUTO) {
					$("#lblModeRun").text(APP_MODE_VALUE.AUTO);
				} else if (data.mode === APP_MODE_KEY.MANUAL) {
					$("#lblModeRun").text(APP_MODE_VALUE.MANUAL);
				}

				if (data.status === JOB_STATUS_KEY.FAILURE_STATUS) {
					$("#lblStatus").text(JOB_STATUS_VALUE.FAILURE_STATUS);
					$("#lblError").text(data.description);
					$("#btnSubmit").removeAttr("disabled", "disabled");
					$("#lblProcess").hide();
				} else if (data.status === JOB_STATUS_KEY.RUNNING_STATUS) {
					$("#lblStatus").text(JOB_STATUS_VALUE.RUNNING_STATUS);
					$('#btnSubmit').attr("disabled", "disabled");
					$("#lblError").text("");
					$("#lblProcess").show();
				} else if (data.status === JOB_STATUS_KEY.SUCCESS_STATUS) {
					$("#lblStatus").text(JOB_STATUS_VALUE.SUCCESS_STATUS);
					$("#btnSubmit").removeAttr('disabled', "false");
					$("#lblError").text("");
					$("#lblProcess").hide();
				}

				var lastRun = new Date(data.startTime);
				$("#lblLastRun").text(formatDate(lastRun));
			}
			reCallStatus();
		},
		error : function() {
			reCallStatus();
		},
		dataType : "json"
	});
}

Number.prototype.padLeft = function(base, chr) {
	var len = (String(base || 10).length - String(this).length) + 1;
	return len > 0 ? new Array(len).join(chr || '0') + this : this;
}

function reCallStatus() {
	setTimeout(function() {
		getStatus();
	}, 120000);
}

function formatDate(d) {
	var dformat = [ (d.getMonth() + 1).padLeft(), d.getDate().padLeft(),
			d.getFullYear() ].join('-')
			+ ' '
			+ [ d.getHours().padLeft(), d.getMinutes().padLeft(),
					d.getSeconds().padLeft() ].join(':');
	return dformat;
}

function parseDate(str) {
	var mdy = str.split('/');
	return new Date(mdy[2], mdy[0] - 1, mdy[1]);
}

function daydiff(first, second) {
	return Math.round((second - first) / (1000 * 60 * 60 * 24));
}