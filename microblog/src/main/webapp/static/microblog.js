var POST_REFRESH_RATE_IN_SECONDS = 30;

$(document).ready(function() {
	$("#createMessage").limit({
		limit : 500,
		id_result : "counter",
		alertClass : "error"
	});
	
	if($("#posts").length > 0) {
		
		var lastUpdate = $("#lastUpdate");
		
		if(lastUpdate.val().length === 0) {
			lastUpdate.val((new Date()).getDateTimeString());
		}
		
		postAutoUpdater();
	}
});

function postAutoUpdater() {
	var currentStamp = $("#lastUpdate").val();
	$.ajax({
		url: 'livePosts',
		data: "createdAfter=" + currentStamp,
		success: function(postsData) {

			var element = $("#posts > tbody"),
				className = $("#posts > tbody > tr").attr("class");

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
	    						'</td>\n' +
	    					'</tr>\n';
				element.prepend(template);
				
			});
	    },
	    complete: function() {
	    	var updateStamp = (new Date()).getDateTimeString();
	    	$("#lastUpdate").val(updateStamp);
	    	setTimeout(postAutoUpdater, POST_REFRESH_RATE_IN_SECONDS * 1000);
	    }
	});
};

