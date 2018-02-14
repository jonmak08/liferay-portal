var closeBtn = document.getElementsByClassName('closeBtn')[0];
var modal = document.getElementById('simpleModal');
var modalBtn = document.getElementsByClassName('portlet-title-text');
var nav = document.getElementById('navigation');
var navItems = document.getElementById('navbar');
var parentContent = document.getElementById('parentContent');
var sideNav = document.getElementById('mySidenav');
var sideNavContainer = document.getElementById('sideNavContainer');

//converts links from an HTMLcollection to an array. This may be ineffecient if there is a way to iterate over HTMLCollection object.

var buttonArr = Object.values(modalBtn);

for (let i = 1; i < buttonArr.length; i++ ) {
    buttonArr[i].addEventListener('click', openModal);
}

closeBtn.addEventListener('click', closeModal);

window.addEventListener('click', clickOutside);

function openModal(element) {
    if (!parentContent.hasChildNodes()) {
        var content = this.parentNode.cloneNode(true);
        document.getElementById('parentContent').appendChild(content);
    }  
    modal.style.display = 'block';
}

function closeModal() {
    var myNode = document.getElementById('parentContent');
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
    modal.style.display = 'none';
}

function clickOutside(event) {
    if (event.target == modal) {
        var myNode = document.getElementById('parentContent');
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }
        modal.style.display = 'none';
    }
}

function openNav() {
    sideNav.style.width = "325px";
    sideNavContainer.appendChild(navItems);
    nav.style.display = 'none';
}

function closeNav() {
    sideNav.style.width = "0";
    nav.style.display = 'block';
}