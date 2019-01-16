window.onscroll = function() {checkOffset()};

var navigation = document.getElementById("navigation-top");
var navigationOffsetTop = navigation.offsetTop;

function checkOffset() {
  if (window.pageYOffset >= navigationOffsetTop) {
    navigation.classList.add("navigation-offset-top");
  }
  else {
    navigation.classList.remove("navigation-offset-top");
  }
}

function checkNavBarToggled() {
	var navBar = document.getElementById("navigation-top");

	if (navBar.classList.value === "navigation-top") {
		navBar.classList.add("toggled");
	}
	else {
		navBar.classList.remove("toggled");
	}
}