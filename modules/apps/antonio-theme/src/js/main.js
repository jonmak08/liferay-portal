$(document).ready(function() {
  AUI().ready(
    'liferay-sign-in-modal',
    function(A) {
      var signIn = A.one('#signIn');

      if (signIn && signIn.getData('redirect') !== 'true') {
        signIn.plug(Liferay.SignInModal);
      }
    }
  );

  var navbarStartingPosition = 0;
  var navbarCurrentPosition = $('.navbar-custom').offset().top;
  var navbar = $('.navbar-custom');
  var controlMenu = $('.control-menu');
  var pageContent = $('.page-content');
  var navItemDropdown = $('li.dropdown');
  var pagerOne = $('.pager-one');
  var pagerTwo = $('.pager-two');
  var pagerThree = $('.pager-three');
  var carouselSlideOne = $('.slide-one');
  var carouselSlideTwo = $('.slide-two');
  var carouselSlideThree = $('.slide-three');

  // check if page loads with liferay-portal's control menu:
  if (controlMenu.length > 0) {
    pageContent.addClass('adjust-theme-for-control-menu');
    navbar.addClass('adjust-navbar-for-control-menu');

    var navbarStartingPosition = controlMenu.outerHeight() + 1;
  }

  // when page refreshes:
  if (navbarCurrentPosition > navbarStartingPosition) {
    navbar.addClass('navbar-white');
  } else {
    navbar.removeClass('navbar-white');
  }

  $(document).scroll(function() {
    var navbarCurrentPosition = navbar.offset().top;
    if (navbarCurrentPosition > navbarStartingPosition) {
      navbar.addClass('navbar-white');
    } else {
      navbar.removeClass('navbar-white');
    }
  });

  navItemDropdown.hover(
    function() {
      $(this).children('.child-menu').show();
    }, function() {
      $(this).children('.child-menu').hide();
    }
  );

  // for carousel web content will change pics on click
  pagerOne.click(function() {
    carouselSlideOne.fadeIn(500);
    carouselSlideTwo.hide();
    carouselSlideThree.hide();
  });
  pagerTwo.click(function() {
    carouselSlideTwo.fadeIn(500);
    carouselSlideOne.hide();
    carouselSlideThree.hide();
  });
  pagerThree.click(function() {
    carouselSlideThree.fadeIn(500);
    carouselSlideOne.hide();
    carouselSlideTwo.hide();
  });
});
