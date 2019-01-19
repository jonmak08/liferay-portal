<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

	<head>
		<title>${the_title} - ${company_name}</title>

		<meta name="viewport" content="width=device-width, initial-scale=1">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

		<link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

  </head>

	<body>

    <div class="container-fluid">
    	<div class="row top-header">
    		<div class="col-md-8">
    			<div class="row">
    				<div class="col-md-6 header-text">
    					<p><i class="fas fa-phone-square"></i>Call Us: +7.123.456.789 <i class="fas fa-envelope-square"></i>Email: hello@thbusiness.com</p>
    				</div>
    				<div class="col-md-6 header-text">
    					<p>Follow us
											<a href="#"><i class="fab fa-facebook-f" aria-hidden="true"></i></a>
										  <a href="#"><i class="fab fa-twitter" aria-hidden="true"></i></a>
											<a href="#"><i class="fab fa-google-plus-g" aria-hidden="true"></i></a>
										  <a href="#"><i class="fab fa-linkedin" aria-hidden="true"></i></a>
											<a href="#"><i class="fab fa-youtube" aria-hidden="true"></i></a>
											<a href="#"><i class="fab fa-dribbble" aria-hidden="true"></i></a>
											<a href="#"><i class="fab fa-github" aria-hidden="true"></i></a>
										  <a href="#"><i class="fab fa-flickr" aria-hidden="true"></i></a>
    		            </p>
    				</div>
    			</div>
    		</div>
    	</div>

    <!-- LOGO AND NAVBAR -->

  	<div class="row">
  		<div class="col-md-2">
  		</div>
  		<div class="col-md-8">
  			<div class="row">
  				<!-- TITLE -->
  				<div class="col-md-4 logo">
  					<img src="images/thbusiness-logo.png">
  				</div>
  				<!-- MAIN-NAV -->
  				<div class="col-md-8">
  					<nav class="main-nav">
  						<ul>
  							<li><a href="#">Home</a></li>
  			                <li><a href="#">Blog</a></li>
  			                <li><a href="#">Front Page</a></li>
  			                <li><a href="#">Level 1</a></li>
  			                <li><a href="#">About The Tests</a></li>
  			                <li><a href="#">Lorem Ipsum</a></li>
  			               <li><a href="#"><i class="fas fa-search"></i></a></li>
  			            </ul>
  					</nav>
  				</div>
  			</div>
  		</div>
  		<div class="col-md-2">
  		</div>
  	</div>

	<!-- MAIN CAROUSEL -->

	<div class="row cover-carousel">
		<div class="col-md-12">
			<div class="carousel slide" id="carousel-376754">
				<ol class="carousel-indicators">
					<li data-slide-to="0" data-target="#carousel-376754" class="active">
					</li>
					<li data-slide-to="1" data-target="#carousel-376754">
					</li>
					<li data-slide-to="2" data-target="#carousel-376754">
					</li>
					<li data-slide-to="3" data-target="#carousel-376754">
					</li>
				</ol>
				<div class="carousel-inner">
					<!-- CAROUSEL ITEM #1 -->
					<div class="carousel-item active">
						<img class="d-block w-100 cover-img" alt="Carousel Bootstrap First" src="images/carousel-1.png">
						<div class="carousel-caption">
								<div class="container cover-jumbo">
								<h4>
									AN AMAZING WORDPRESS THEME
								</h4>
								<p>
									Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec mollis. Quisque convallis libero in sapien pharetra tincidunt. Aliquam elit ante, malesuada id, tempor eu, gravida id, odio.
								</p>
								<button type="button" class="btn"> DOWNLOAD THEME NOW </button>
								</div>
						</div>
					</div>
					<!-- CAROUSEL ITEM #2 -->
					<div class="carousel-item">
						<img class="d-block w-100 cover-img" alt="Carousel Bootstrap Second" src="images/carousel-2.png">
						<div class="carousel-caption">
							<div class="container cover-jumbo">
								<h4>
									ENJOY YOUR FREEDOM
								</h4>
								<p>
									Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec mollis. Quisque convallis libero in sapien pharetra tincidunt. Aliquam elit ante, malesuada id, tempor eu, gravida id, odio.
								</p>
								<button type="button" class="btn"> READ MORE </button>
							</div>
						</div>
					</div>
					<!-- CAROUSEL ITEM #3 -->
					<div class="carousel-item">
						<img class="d-block w-100 cover-img" alt="Carousel Bootstrap Third" src="images/carousel-3.png">
						<div class="carousel-caption">
							<div class="container cover-jumbo">
								<h4>
									YOU ARE NOT ALONE
								</h4>
								<p>
									Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec mollis. Quisque convallis libero in sapien pharetra tincidunt. Aliquam elit ante, malesuada id, tempor eu, gravida id, odio.
								</p>
								<button type="button" class="btn"> GET FREE SUPPORT </button>
							</div>
						</div>
					</div>
					<!-- CAROUSEL ITEM #4 -->
					<div class="carousel-item">
						<img class="d-block w-100 cover-img" alt="Carousel Bootstrap Fourth" src="images/carousel-4.png">
						<div class="carousel-caption">
							<div class="container cover-jumbo">
								<h4>
									GET YOUR JOB DONE EASILY
								</h4>
								<p>
									Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec mollis. Quisque convallis libero in sapien pharetra tincidunt. Aliquam elit ante, malesuada id, tempor eu, gravida id, odio.
								</p>
								<button type="button" class="btn"> READ MORE </button>
							</div>
						</div>
					</div>
				</div>
				<!-- CAROUSEL CONTROLS -->
				<a class="carousel-control-prev" href="#carousel-376754" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> <a class="carousel-control-next" href="#carousel-376754" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
			</div>
		</div>
	</div>
	<!-- END MAIN CAROUSEL -->

	<!-- FEATURES/SERVICES -->
	<div class="row services container-fluid">
		<div class="col-md-12">
			<div class="row">
        <!-- SERVICE AND FEATURE #1 -->
				<div class="col-md-4 services-box">
					<div class="th-services-icon feature-icon">
					<i class="fa th-fa-services-custom fa-signal"></i>
					</div>
					<h1 class="services-widget-title"><a title="Regular Updates" href="">Regular Updates</a></h1>
					<p>Lorem ipsum dolor sit amet, ocurreret urbanitas persequeris ei vis. Cum in laoreet efficiendi, sed harum tantas ut. Nec alia illud dicunt cu, utroque docendi apeirian per eu. Usu cu…</p>
				<a class="services-link" title="Read More" href=""> Read More </a>
				</div>
        <!-- SERVICE AND FEATURE #2 -->
				<div class="col-md-4 services-box">
					<div class="th-services-icon">
					<i class="fa th-fa-services-custom fa-star"></i>
					</div>
					<h1 class="services-widget-title"><a title="Unlimited Support" href="">Unlimited Support</a></h1>
					<p>Lorem ipsum dolor sit amet, ocurreret urbanitas persequeris ei vis. Cum in laoreet efficiendi, sed harum tantas ut. Nec alia illud dicunt cu, utroque docendi apeirian per eu. Usu cu… </p>
					<a class="services-link" title="Read More" href=""> Read More </a>
				</div>
        <!-- SERVICE AND FEATURE #3 -->
				<div class="col-md-4 services-box">
					<div class="th-services-icon">
					<i class="fa th-fa-services-custom fa-thumbs-up"></i>
					</div>
					<h1 class="services-widget-title"><a title="Crafted with Love" href="">Crafted with Love</a></h1>
					<p>Lorem ipsum dolor sit amet, ocurreret urbanitas persequeris ei vis. Cum in laoreet efficiendi, sed harum tantas ut. Nec alia illud dicunt cu, utroque docendi apeirian per eu. Usu cu…</p>
					<a class="services-link" title="Read More" href=""> Read More </a>
				</div>
			</div>
		</div>
	</div>

<!-- 	CALL TO ACTION  -->
	<div class="row cta container-fluid">
		<div class="col-md-12">
			<div class="row cta-width">
        <!-- QUOTE -->
				<div class="col-md-8 cta-quote">
					<h3>Lorem ipsum dolor sit amet, ocurreret urbanitas persequeris ei vis. Cum in laoreet </h3>
					<p>Lorem ipsum dolor sit amet, ocurreret urbanitas persequeris ei vis. Cum in laoreet </p>
				</div>
        <!-- BUTTON -->
				<div class="col-md-4 cta-btn">
					<button type="button" class="btn"> Read More </button>
				</div>
			</div>
		</div>
	</div>

<!-- 	RECENT WORK -->
	<div class="row recent-work">
		<div class="col-md-12 container-fluid">
			<h1 class="recent-title"> OUR RECENT WORK </h1>
			<div class="row recent-work">
        <!-- RECENT WORK #1 -->
				<div class="col-md-3 recent-work-text">
					<img class="recent-img" src="images/work-1.jpg">
					<h5 class="recent-work-title"><a title="Recent Work 1" href="">Recent Work 1</a><h5>
					<p class="recent-work-text">Lorem ipsum dolor sit amet, ocurreret urbanitas persequeris ei vis. Cum in laoreet efficiendi, sed harum tantas ut. Nec alia illud dicunt cu, utroque docendi apeirian per eu. Usu cu…</p>
				</div>
        <!-- RECENT WORK #2 -->
				<div class="col-md-3 recent-work-text">
					<img class="recent-img" src="images/work-2.jpg">
					<h5 class="recent-work-title"><a title="Recent Work 2" href="">Recent Work 2</a><h5>
					<p class="recent-work-text">Lorem ipsum dolor sit amet, ocurreret urbanitas persequeris ei vis. Cum in laoreet efficiendi, sed harum tantas ut. Nec alia illud dicunt cu, utroque docendi apeirian per eu. Usu cu…</p>
				</div>
        <!-- RECENT WORK #3 -->
				<div class="col-md-3 recent-work-text">
					<img class="recent-img"src="images/work-3.jpg">
					<h5 class="recent-work-title"><a title="Recent Work 3" href="">Recent Work 3</a><h5>
					<p class="recent-work-text">Lorem ipsum dolor sit amet, ocurreret urbanitas persequeris ei vis. Cum in laoreet efficiendi, sed harum tantas ut. Nec alia illud dicunt cu, utroque docendi apeirian per eu. Usu cu…</p>
				</div>
        <!-- RECENT WORK #4 -->
				<div class="col-md-3 recent-work-text">
					<img class="recent-img" src="images/work-4.jpg">
					<h5 class="recent-work-title"><a title="Recent Work 4" href="">Recent Work 4</a><h5>
					<p class="recent-work-text">Lorem ipsum dolor sit amet, ocurreret urbanitas persequeris ei vis. Cum in laoreet efficiendi, sed harum tantas ut. Nec alia illud dicunt cu, utroque docendi apeirian per eu. Usu cu…</p>
				</div>
			</div>
		</div>
	</div>

	<hr>

<!-- 	SAMPLE AND CLIENTS -->
	<div class="row container-fluid">
		<div class="col-md-12">
			<div class="row sample-space">
        <!-- SAMPLE PAGE  -->
				<div class="col-md-6">
					<h1 class="sample-page-title"> SAMPLE PAGE </h1>
					<img class='sample-page-img' alt="Sample Page" src="images/sample-page.jpg">
					<p class="sample-page-p">
						This is an example page. It’s different from a blog post because it will stay in one place and will show up in your site navigation (in most themes). Most people start with an About page that introduces them to potential site visitors. It might…
					</p>
					<button type="button" class="btn">
						Read More
					</button>
				</div>
        <!-- CLIENT RESPONSE -->
				<div class="col-md-6">
					<h1 class="client-response">WHAT'S CLIENTS SAY?</h1>
          <!-- CLIENT #1 -->
					<div>
						<img class="client-photo" alt="client 1" src="images/client-1.jpg">
						<p class="client-bio">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec mollis. Quisque convallis libero in sapien pharetra tincidunt.</p>
						<p class='client-signature'> <strong>John Doe,</strong> Web Developer - Web Studio
					</div>
          <!-- CLIENT #2 -->
					<div>
						<img class="client-photo" alt="client 2" src="images/client-2.jpg">
						<p class="client-bio">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec mollis. Quisque convallis libero in sapien pharetra tincidunt.</p>
						<p class='client-signature'> ,<strong>Lisa Keran,</strong> Founder - Graphic Design Company
					</div>
          <!-- CLIENT #3 -->
					<div>
						<img class="client-photo" alt="client 3" src="images/client-3.jpg">
						<p class="client-bio">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec mollis. Quisque convallis libero in sapien pharetra tincidunt.</p>
						<p class='client-signature'> <strong>Tom Brown,</strong> Owner - Web Development Company
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- 	CALL TO ACTION CLIENTS -->
	<div class="row clients container-fluid">
		<div class="col-md-8 client-spacing">
			<div class="row">
        <!-- CLIENT COMPANY #1 -->
				<div class="col-md-3">
					<img class="client-item-image" alt="buddypress" src="images/buddypress_logo.png">
				</div>
        <!-- CLIENT COMPANY #2 -->
				<div class="col-md-3">
					<img class="client-item-image" alt="wordpress" src="images/wordpress-logo-hoz-rgb.png">
				</div>
        <!-- CLIENT COMPANY #3 -->
				<div class="col-md-3">
					<img class="client-item-image" alt="bbpress" src="images/bbpress-logo.png">
				</div>
        <!-- CLIENT COMPANY #4 -->
				<div class="col-md-3">
					<img class="client-item-image"  alt="jquery" src="images/jquery-logo.png">
				</div>
			</div>
		</div>
	</div>

<!-- 	FOOTER -->
	<div class="row footer">
		<div class="col-md-2">
		</div>
		<div class="col-md-8">
			<div class="row">
        <!-- ABOUT -->
				<div class="col-md-4">
				</div>
        <!-- HELPFUL LINKS -->
				<div class="col-md-4">
				</div>
        <!-- BLOG POSTS -->
				<div class="col-md-4">
				</div>
			</div>
		</div>
		<div class="col-md-2">
		</div>
	</div>
</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>
