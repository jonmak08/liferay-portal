
AUI().ready(

	/*
  This function gets loaded when all the HTML, not including the portlets, is
  loaded.
  */

  function() {
  }
);

Liferay.Portlet.ready(

  /*
  This function gets loaded after each and every portlet on the page.

  portletId: the current portlet's id
  node: the Alloy Node object of the current portlet
  */

  function(portletId, node) {
  }
);

Liferay.on(
  'allPortletsReady',

  /*
  This function gets loaded when everything, including the portlets, is on
  the page.
  */

  function() {
    let $mainContent = $("#wrapper #main-content");

    //add animated class to first row of layout
    let $firstRowPortlets = $mainContent.find('> div:first-child .portlet-content');
    $firstRowPortlets.addClass('animated');

    //create an overlay for banner navigation
    $('#navigation').prepend('<div id="bars-nav-icon"><i class="icon-align-justify"></i></div>');
    $('#navigation').prepend('<div id="slide-nav" class="overlay"></div>');
    $('#slide-nav').append('<a href="javascript:void(0)" class="closebtn">&times;</a>');
    $('#navigation ul').addClass('overlay-content');
    $('#slide-nav').append($('#navigation ul'));
    $('body').append($('#slide-nav'));
    $('#bars-nav-icon').click(openNav);
    $('.closebtn').click(closeNav);

    function openNav() {
      let controlMenuHeight = $('.control-menu').css('height');

      $('.overlay').css('top', controlMenuHeight);
      $('#slide-nav').css('width', '30%');
      $('#bars-nav-icon').hide();
    }

    function closeNav() {
      $('#slide-nav').css('width', '0');
      $('#bars-nav-icon').show();
    }

    //when resize window 
    $( window ).resize(() => {
      //responsive overlay
      let isOverlayPresent = Number($('#slide-nav').css('width').slice(0, -2)) > 0;
      if(isOverlayPresent) {
        if (window.innerWidth < 600) {
          $('#slide-nav').css('width', '100%');
        } else if (window.innerWidth >= 600) {
          $('#slide-nav').css('width', '30%');
        }
      }

      //when control menu changes size, reset top for overlay
      let controlMenuHeight = $('.control-menu').css('height');
      let overlayTop = $('.overlay').css('top');

      if(isOverlayPresent && controlMenuHeight !== overlayTop) {
        $('.overlay').css('top', controlMenuHeight);
      }

    });


  }

);