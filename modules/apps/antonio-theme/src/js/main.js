$(document).ready(function() {
  var navbarStartingPosition = 0;
  var navbarCurrentPosition = $('.navbar-custom').offset().top;
  var navbar = $('.navbar-custom');
  var controlMenu = $('.control-menu');
  var pageContent = $('.page-content');

  // check if page loads with liferay-portal's control menu:
  if (controlMenu.length > 0) {
    pageContent.addClass('adjust-theme-for-control-menu');
    navbar.addClass('adjust-navbar-for-control-menu');

    var navbarStartingPosition = controlMenu.outerHeight();
  }

  // on page load:
  if (navbarCurrentPosition > navbarStartingPosition) {
    navbar.addClass('navbar-white');
  } else {
    navbar.removeClass('navbar-white');
  }

  $(document).scroll(function() {
    // will adjust navbarCurrentPosition when scrolling
    var navbarCurrentPosition = navbar.offset().top;
    if (navbarCurrentPosition > navbarStartingPosition) {
      navbar.addClass('navbar-white');
    } else {
      navbar.removeClass('navbar-white');
    }
  });

  $('li.dropdown').hover(function() {
    $(this).find('.child-menu').fadeIn(200);
  }, function() {
    $(this).find('.child-menu').fadeOut(200);
  });

  AUI().ready(
   'liferay-sign-in-modal',
    function(A) {
    var signIn = A.one('#signIn');

    if (signIn && signIn.getData('redirect') !== 'true') {
     signIn.plug(Liferay.SignInModal);
    }
   }
  );
});
