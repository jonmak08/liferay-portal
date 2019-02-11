AUI().ready(
	'liferay-sign-in-modal',
	function(A) {
		var signIn = A.one('.sign-in > a');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}
	}
);

$(document).ready(function() {    
    $(".search").click(function() {
       $(".search-box").toggle();
    });

}
);


AUI().ready(function (A){
	const buttonNode = A.one('#my-toggler button');

	function hidePortlet(){
		const portletFormNode = A.one(".portlet-forms-display");
			
		if (portletFormNode.getStyle('display') == 'none'){
			portletFormNode.setStyle('display', 'block');
		}
		else {
			portletFormNode.setStyle('display', 'none');
		}
			
	}
	
	buttonNode.on('click', hidePortlet);
});

//tooltip function for recent work images

YUI().use(
	'aui-tooltip',
	function(Y) {
	  new Y.TooltipDelegate(
		{
		  trigger: '#myTooltipDelegate img',
		  position: 'right'
		}
	  );
	}
  );