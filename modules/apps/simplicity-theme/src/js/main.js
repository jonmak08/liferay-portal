window.onscroll = function() {checkOffset()};

var navigation = document.getElementById("navigation-top");
var navigationOffsetTop = navigation.offsetTop;

console.log("### navigation is: " + navigation);
console.log("###" + navigationOffsetTop);

function checkOffset() {
  if (window.pageYOffset >= navigationOffsetTop) {
    navigation.classList.add("navigation-offset-top");
  }
  else {
    navigation.classList.remove("navigation-offset-top");
  }
}