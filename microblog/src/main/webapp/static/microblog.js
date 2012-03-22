var POST_REFRESH_RATE_IN_SECONDS = 30;

$(document).ready(function() {
	$("#createMessage").limit({
		limit : 500,
		id_result : "counter",
		alertClass : "error"
	});
	
	if($("#posts").length > 0) {
		retrievalDateUpdate();
		setTimeout(postAutoUpdater, POST_REFRESH_RATE_IN_SECONDS * 1000);
	}
});

var retrievalDateUpdate = function() {
	var lastUpdate = $("#lastUpdate");
	
	retrievalDateElements = $(".retrievalDate:first");
	
	if(retrievalDateElements.length > 0) {
		millisString = retrievalDateElements.text();
		millis = parseInt(millisString);
		var retrievalDate = new Date(millis);
		console.log(retrievalDate.getDateTimeString());
		lastUpdate.val(retrievalDate.getDateTimeString());
	}
};

var postAutoUpdater = function() {
	var currentStamp = $("#lastUpdate").val();
	$.ajax({
		url: 'livePosts',
		data: "createdAfter=" + currentStamp,
		success: function(postsData) {

			var element = $("#posts > tbody"),
				className = $("#posts > tbody > tr:first").attr("class");

			jQuery.each(postsData, function(index, post) {
	    		
				className = (className === "even") ? "odd" : "even";
	    		
				var template = '' +
	    	    			'<tr class="' + className + '">\n' +
	    						'<td class="postCell">\n' +
	    							'<div class="postHeader">\n' +
	    								'<span class="username">' + post.username + '</span>\n' +
	    								'<span class="age">' + post.age + '</span>\n' +
	    							'</div>\n' +
	    							'<div class="message">' + post.message + '</div>\n' +
	    							'<div class="retrievalDate">' + post.retrievalDate + '</div>'
	    						'</td>\n' +
	    					'</tr>\n';
				element.prepend(template);
				
			});
	    },
	    complete: function() {
	    	retrievalDateUpdate();
	    	setTimeout(postAutoUpdater, POST_REFRESH_RATE_IN_SECONDS * 1000);
	    }
	});
};

