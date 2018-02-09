var modal = document.getElementById('simpleModal');
var modalBtn = document.getElementsByClassName('portlet-title-text'); 
var closeBtn = document.getElementsByClassName('closeBtn')[0];

// converts links from an HTMLcollection to an array. This may be ineffecient if there is a way to iterate over HTMLCollection object. 

var buttonArr = Object.values(modalBtn);

for (let i = 0; i < buttonArr.length; i++ ) {  
    buttonArr[i].addEventListener('click', openModal); 
}

closeBtn.addEventListener('click', closeModal);
window.addEventListener('click', clickOutside);

function openModal(element) {
    var content = this.parentNode.cloneNode(true); 
    document.getElementById('parentContent').appendChild(content);
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



