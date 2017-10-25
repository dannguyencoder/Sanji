/**
 * 
 */
$(function() {

			$('form[name="MultiForm"] input[type="submit"]').click(
					function(event) {
						var $form = $('form[name="MultiForm"]');
						if ($(this).val() == 'Multi-Delete') {
							$form.attr('action', '/admin/lawyer/deleteMulti');
							return confirm("Do you want to delete?");
						} else {
							$form.attr('action', '/admin/lawyer/updateMultiProcess');
						}
					});

		});