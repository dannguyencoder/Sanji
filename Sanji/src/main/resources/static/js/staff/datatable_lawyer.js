function format ( d ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>Law type ID::</td>'+
            '<td>'+d.law_type_id+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Salary ID:</td>'+
            '<td>'+d.salary_id+'</td>'+
        '</tr>'+
    '</table>';
}

$(document).ready( function () {
	 var table = $('#lawyerssTable').DataTable({
		 stateSave	: true,
		 "sAjaxSource": "/lawyerList",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
				 {
		                "className":      'details-control',
		                "orderable":      false,
		                "data":           null,
		                "defaultContent": ''
		            },
			      { "mData": "lawyer_id"},
		          { "mData": "lawyer_firstname" },
				  { "mData": "lawyer_lastname" },
				  { "mData": "dateofbirth" },
				  { "mData": "gender" },
				  { "mData": "email" },
				  { "mData": "phone" },
				  { "mData": "address" },
				  { "mData": "join_date" },
				  { "mData": "certification" }
			]
	 });
	 
	// Add event listener for opening and closing details
	    $('#lawyerssTable tbody').on('click', 'td.details-control', function () {
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




