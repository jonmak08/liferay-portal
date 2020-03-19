window.onscroll = () => {
    const nav = document.querySelector('#themeNav');
    if(this.scrollY <= 10) navbar.className = ''; else navbar.className = 'scroll';
  };

  function displayDate()
{
    document.getElementById("dateDisplay").innerHTML = Date();
}

