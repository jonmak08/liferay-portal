AUI().ready(
	function() {
	}
);

Liferay.Portlet.ready(
	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',
	function() {
        $(document).ready( () => {
            if ($('#sidenavContainer').children().length <= 1) { 
                $('#navbar').clone().appendTo('#sidenavContainer'); 
            }

            $('#hamburger').click( () => { 
                $('.sideNav').css('display','block');
            });

            $('.closebtn').click( () => { 
                $('.sideNav').css('display','none');
            }); 
        });
        
	}
);