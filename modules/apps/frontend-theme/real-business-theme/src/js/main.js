AUI().ready(
	'liferay-sign-in-modal',
	function(A) {
		var signIn = A.one('.sign-in > a');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}
	}
);

// STICKY NAV

function stickyNav() {
  let themeNavigationBar = document.getElementById('banner');
  let sticky = themeNavigationBar.offsetTop;
  if (window.pageYOffset > sticky) {
    themeNavigationBar.classList.add('sticky');
  }
  else {
    themeNavigationBar.classList.remove('sticky');
  }
}

window.onscroll = function() {
  stickyNav();
};

// STICKY NAV END

// BREAK FUNCTION

  var customUnorderedList = document.getElementById('footer-count'); 
  var customListItems = customUnorderedList.getElementsByTagName('li');
  
  if (customListItems.length > 4) {
    customUnorderedList.classList.add('my-wrap');
  }


// BREAK FUNCTION END

// ALLOY TOOLTIP

YUI().use(
  'aui-tooltip',
  function(Y) {
    new Y.TooltipDelegate(
      {
        trigger: '#footer-count li',
        position: 'bottom'
      }
    );
  }
);

// ALLOY TOOLTIP END