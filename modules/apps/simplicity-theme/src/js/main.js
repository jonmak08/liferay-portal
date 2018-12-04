window.onscroll = function() {checkOffset()};

var navigation = document.getElementById("navigation-top");
var navigationOffsetTop = navigation.offsetTop;

console.log("### navigation is: " + navigation);
console.log("###" + navigationOffsetTop);

function checkOffset() {
  if (window.pageYOffset >= navigationOffsetTop) {
    navigation.classList.add("navigationOffsetTop");
  }
  else {
    navigation.classList.remove("navigationOffsetTop");
  }
}