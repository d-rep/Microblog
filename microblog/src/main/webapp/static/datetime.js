/**
 * Helper for dealing with datetimes.  It extends the built-in Date class from Javascript.
 * 
 * @author Drew Repasky
 */
Date.prototype.getDateTimeString = function() {
	var year = this.getFullYear(),
		month = this.getMonth() + 1,
		day = this.getDate(),
		hours = this.getHours(),
		minutes = this.getMinutes(),
		seconds = this.getSeconds();
	
	return year + "/" + month + "/" + day + " " + hours + ":" + minutes + ":" + seconds;
};
