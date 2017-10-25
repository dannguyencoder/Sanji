/**
 * 
 */
$(document).ready(function() {
	changePageAndSize();
});

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.location.replace("/admin/bookingList?pageSize=" + this.value + "&page=1");
	});
}