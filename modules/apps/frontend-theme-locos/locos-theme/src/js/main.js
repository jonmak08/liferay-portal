
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
    // console.log(`Current portlet: ID${portletId} Object:${node}`);
  }
);

Liferay.on(
  'allPortletsReady',

  /*
  This function gets loaded when everything, including the portlets, is on
  the page.
  */

  function() {

    //add animated class to first row of layout
    var childNodes = document.getElementById("main-content").childNodes;
    for (var i = 0; i < childNodes.length; i++) {
      if (childNodes[i].nodeType !== 3) {
        var firstChild = childNodes[i];
        var components = firstChild.getElementsByClassName("portlet-content");
        for (let i = 0; i < components.length; i++) {
          components[i].className += " animated";
        }
        break;
      }
    }

    //sticky navigation
    window.onscroll = function() {stick()};
    var banner = document.getElementById("banner");
    var sticky = banner.offsetTop;
    function stick() {
      if (window.pageYOffset >= sticky) {
        banner.classList.add("sticky")
      } else {
        banner.classList.remove("sticky");
      }
    }

  }
);