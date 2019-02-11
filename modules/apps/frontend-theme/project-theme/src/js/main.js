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
	const buttonNode = A.one('#myToggler button');

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

// function showHide() {
// 	var x = document.querySelector(".portlet-forms-display");
//   if (x.style.display == "none") {
//     x.style.display = "block";
//   } else {
//     x.style.display = "none";
//   }
// }




//tooltip function for recent work images

YUI().use(
  'aui-tooltip',
  function(Y) {
    new Y.Tooltip(
      {
        trigger: '#pTooltip img',
        position: 'right'
      }
    ).render();
  }
);