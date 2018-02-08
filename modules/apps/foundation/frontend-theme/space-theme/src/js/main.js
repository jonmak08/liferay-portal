var modal = document.getElementById('simpleModal');
var modalBtn = document.getElementsByClassName('modalBtn'); 
var closeBtn = document.getElementsByClassName('closeBtn')[0];

 //converts buttons from an HTMLcollection to an array. This may be ineffecient if there is a way to iterate over HTMLCollection object. 
const buttonArr = Object.values(modalBtn);



for (let i = 0; i < buttonArr.length; i++ ) {  
    buttonArr[i].addEventListener('click', openModal); 
}


closeBtn.addEventListener('click', closeModal);
window.addEventListener('click', clickOutside);

function openModal(element) {
    const content = this.parentNode.cloneNode(true); 
    document.getElementById("parentContent").appendChild(content);
    modal.style.display = 'block';
}


function closeModal() {
    const myNode = document.getElementById("parentContent");
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
    modal.style.display = 'none';
}


function clickOutside(e) {
    if (e.target == modal) {
        const myNode = document.getElementById("parentContent");
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }
        modal.style.display = 'none';
    }
}



// window.onscroll = function() {stickyNav()};
// var navbar = document.getElementById("navbar"); 
// var sticky = navbar.offsetTop;
// function stickyNav() {
//   if (window.pageYOffset >= sticky) {
//     navbar.classList.add("sticky")
//   } else {
//     navbar.classList.remove("sticky");
//   }
// }