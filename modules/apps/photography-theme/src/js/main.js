AUI().ready(
  'liferay-sign-in-modal',
	function(A) {
		var signIn = A.one('#sign-in');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}
	}
)

AUI().ready(function(){

  // new WOW.WOW().init()

  // ======================================================
  // Variables and state initialization
  // ======================================================
  

  const status = 'Up and running'
  
  console.log(status)

  const paddingLarge = '20px 42px'
  const paddingSmall = '5px 35px'
  let scrollPosition = 0

  // ======================================================
  // Carousel initialization
  // ======================================================

  const flkty = new Flickity('.carousel', {
    autoPlay: 5000,
    pauseAutoPlayOnHover: false,
    pageDots: false, 
    contain: true
  })  

  // $('.icon-angle-down').on('click', () => {
  //   $('html,body').animate({scrollTop:$('#media-tiles').offset().top}, 500)
  // })
  
  // ==========================================================
  // Navbar functionality & helper functions
  // ==========================================================

  const changePadding = (size) => {
    size === 'small'
      ? $('#navigation').css({'padding': paddingSmall})
      : $('#navigation').css({'padding': paddingLarge})
  }

  const changeBackground = (color) => {
    $('.navbar-default').css({'background': color})
  }

  const changeFontColor = (color) => {
    $('.logo > span, .navbar-nav span, #sign-in, .icon-bar').css({'color': color})
  }

  const changeBorder = (color) => {
    $('#sign-in').css({'border': '1px solid ' + color})
  } 

  // ==========================================================
  // adjusts the navbar css based on SCROLL position
  // ==========================================================

  $(window).scroll(() => {

    scrollPosition = $(window).scrollTop()

    if (scrollPosition > 50) {
      changePadding('small')
      changeBackground('white')
      changeFontColor('black')
      changeBorder('black')
      

    } else {
      changePadding('large')
      changeBackground('rgba(0, 0, 0, 0.1)')
      changeFontColor('white')
      changeBorder('white')
    }
  })
  
  // ==========================================================
  // Resizes the minitiles css based on WINDOW position
  // ==========================================================

  /* $(window).resize(function(e) {
    // Resize the mini tiles on bottom of home page into squares on window resize
    const ids = [ '#mini-img1', '#mini-img2', '#mini-img3', '#mini-img4', '#mini-img5', '#mini-img6']

    const height = $('#mini-img1').width()

    for (let i = 0; i < ids.length; i++) {
      $(ids[i]).height(height)
    }
  }).resize() */
  
})