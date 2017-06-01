$(document).ready(function() {
  var navbarStartingPosition = 0;
  var navbarCurrentPosition = $('.navbar-custom').offset().top;
  var navbar = $('.navbar-custom');
  var controlMenu = $('.control-menu');
  var pageContent = $('.page-content');
  var searchIcon = $('#searchIcon');

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

  searchIcon.click(function(event) {
    event.preventDefault();
    console.log('opening Google search!');
    window.open('https://www.google.com');
  });

  $('.pager-item-one').click(function() {
    $('#carouselSlideOne').fadeIn(500);
    $('#carouselSlideTwo').hide();
    $('#carouselSlideThree').hide();
  });
  $('.pager-item-two').click(function() {
    $('#carouselSlideTwo').fadeIn(500);
    $('#carouselSlideOne').hide();
    $('#carouselSlideThree').hide();
  });
  $('.pager-item-three').click(function() {
    $('#carouselSlideThree').fadeIn(500);
    $('#carouselSlideOne').hide();
    $('#carouselSlideTwo').hide();
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
