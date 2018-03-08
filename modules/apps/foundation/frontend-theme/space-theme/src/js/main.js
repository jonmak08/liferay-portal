AUI().ready(
	function() {
        console.log(`test44`);
        if ($('#side-nav-container').children().length <= 1) { 
            $('#navbar').clone().appendTo('#side-nav-container');
        }

        $('#hamburger').click( () => {
            if ($('.side-nav').css('display') == 'none') {
                $('#hamburger').toggleClass('glyphicon glyphicon-menu-hamburger');
                $('#hamburger').toggleClass('glyphicon glyphicon-remove');
                $('.side-nav').css('display','block');
            } else {
                $('#hamburger').toggleClass('glyphicon glyphicon-remove');
                $('.dropdown-menu').css('display', 'none');
                $('.child-menu').css('display', 'none');
                $('#hamburger').toggleClass('glyphicon glyphicon-menu-hamburger');
                $('.side-nav').css('display','none');
            }
        });

        $('.btn').click( function(element) {
            if($(this).next().css('display') == 'none') {
                $(this).next().css('display', 'block');
            } else {
                $(this).next().css('display', 'none');
            }
        });
	}
);

Liferay.Portlet.ready(
	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',
	function() {        
	}
);