// STICKY NAV

window.onscroll = function() {stickyNav()};

let myNavbar = document.getElementById("banner");
let mySearchBar = document.getElementById("mySearch")

let sticky = myNavbar.offsetTop;

function stickyNav() {
  if (window.pageYOffset > sticky) {
    myNavbar.classList.add("sticky");
    mySearchBar.classList.add("stickySearch");
  } else {
    myNavbar.classList.remove("sticky");
    mySearchBar.classList.remove("stickySearch");
  }
}

// STICKY NAV END

// SEARCH BAR
function searchToggle() {
  let onOff = document.getElementById('mySearch');
  if (onOff.style.display === "flex") {
    onOff.style.display = "none";
  } else {
    onOff.style.display = "flex";
  }
}
// SEARCH BAR END

// BREAK FUNCTION
let ul = document.getElementById("footer-count");
let lis = ul.getElementsByTagName("li");
console.log(ul, lis.length);

if (lis.length > 4) {
  ul.classList.add('my-wrap');
}
// BREAK FUNCTION END