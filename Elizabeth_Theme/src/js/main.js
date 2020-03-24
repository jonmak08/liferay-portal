window.addEventListener('scroll', function() { 
  if (document.documentElement.scrollTop > 150) {
    document.querySelector('header').classList.add('scrolled');
  }

  else {
    document.querySelector('header').classList.remove('scrolled');
  }
})