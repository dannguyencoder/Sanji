function format ( d ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>Note:</td>'+
            '<td>'+d.note+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Booking time:</td>'+
            '<td>'+d.time+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Case catalog id:</td>'+
            '<td>'+d.case_cat_id+'</td>'+
        '</tr>'+
    '</table>';
}

$(document).ready( function () {
	 var table = $('#bookingsTable').DataTable({
		 stateSave	: true,
		 "sAjaxSource": "/bookingList",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
				 {
		                "className":      'details-control',
		                "orderable":      false,
		                "data":           null,
		                "defaultContent": ''
		            },
			      { "mData": "bookingId"},
		          { "mData": "lastName" },
				  { "mData": "firstName" },
				  { "mData": "gender" },
				  { "mData": "email" },
				  { "mData": "phone" },
				  { "mData": "address" },
				  { "mData": "prefer_date" },
				  { "mData": "status" }
			]
	 });
	 
	// Add event listener for opening and closing details
	    $('#bookingsTable tbody').on('click', 'td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = table.row( tr );
	 
	        if ( row.child.isShown() ) {
	            // This row is already open - close it
	            row.child.hide();
	            tr.removeClass('shown');
	        }
	        else {
	            // Open this row
	            row.child( format(row.data()) ).show();
	            tr.addClass('shown');
	        }
	    });
});




