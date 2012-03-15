$(document).ready(function() {
	$("#createMessage").limit({
		limit : 500,
		id_result : "counter",
		alertClass : "error"
	});
});