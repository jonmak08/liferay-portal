<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />


	<!--<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/jquery.slick/1.6.0/slick.css"/>-->

  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">

	<link rel="stylesheet" type="text/css" href="https://unpkg.com/flickity@2/dist/flickity.min.css">
	<script src="https://unpkg.com/flickity@2/dist/flickity.pkgd.js"></script>

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="container-fluid" id="wrapper">
	<div id="photography-theme-ctn">
		<header>

			<#if !is_signed_in>
				<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
			</#if>

			<#if has_navigation && is_setup_complete>
				<#include "${full_templates_path}/navigation.ftl" />
			</#if>

			<nav id="main-navbar">
				<div class="logo" id="nav-logo">Lance</div>
				<i class="icon-reorder" aria-hidden="true"></i>
				<i class="times icon-2x" aria-hidden="true"></i>
				<ul>
					<li><a href="/">Home</a></li>
					<li><a href="/about">About</a></li>
					<li><a href="/contact">Contact</a></li>
					<li id="portfolio-list">
						<div id="portfolio-dropdown">
							Portfolio
							<i id="menu-icon" class="icon-angle-right icon-2x menu-icon" aria-hidden="true"></i>
							<p class="animated fadeIn">Portfolio Cards</p>
							<p class="animated fadeIn">Gallery A</p>
							<p class="animated fadeIn">Gallery B</p>
							<p class="animated fadeIn">Gallery C</p>
							<p class="animated fadeIn">Gallery D</p>
						</div>
					</li>
				</ul>
				<div class="social-ctn">
					<i class="icon-facebookk" aria-hidden="true"></i>
					<i class="icon-twitter" aria-hidden="true"></i>
					<i class="icon-instagram" aria-hidden="true"></i>
				</div>
			</nav>


				<div class="carousel">
					<div id="carousel1" class="carousel-cell"></div>
					<div id="carousel2" class="carousel-cell"></div>
					<div id="carousel3" class="carousel-cell"></div>
					<div id="carousel4" class="carousel-cell"></div>
				</div>

		</header>

		<!--<section id="content">
			<h1 class="hide-accessible">${the_title}</h1>

			<nav id="breadcrumbs">
				<@liferay.breadcrumbs />
			</nav>

			<#if selectable>
				<@liferay_util["include"] page=content_include />
			<#else>
				${portletDisplay.recycle()}

				${portletDisplay.setTitle(the_title)}

				<@liferay_theme["wrap-portlet"] page="portlet.ftl">
					<@liferay_util["include"] page=content_include />
				</@>
			</#if>
		</section>-->

		<main href="#main">
			<section id="media-tiles">
				<div data-wow-offset="10" class="tile-ctn wow fadeInUp">
					<figure>
						<div class="tile" id="tile1">
							<figcaption>
								<h3 class="tile-header">Lovely Paradise</h3>
								<h5 class="italic">country&apos;s back</h5>
							</figcaption>
						</div>
					</figure>
					<figure class="tile">
						<div class="tile" id="tile2" />
						<figcaption>
							<h3 class="tile-header">The Getaway</h3>
							<h5 class="italic">road not taken</h5>
						</figcaption>
					</figure>
				</div>
				<div class="tile-ctn wow fadeInUp">
					<figure class="tile">
						<div class="tile" id="tile3" />
						<figcaption>
							<h3 class="tile-header">Book of Wisdom</h3>
							<h5 class="italic">one life</h5>
						</figcaption>
					</figure>
					<figure class="tile">
						<div class="tile" id="tile4" />
						<figcaption>
							<h3 class="tile-header">The Beautiful Bond</h3>
							<h5 class="italic">unity</h5>
						</figcaption>
					</figure>
				</div>
			</section>

			<section id="about">
				<div class="info-ctn info">
					<h2 class="wow fadeInUp">Info</h2>
					<p class="wow fadeInUp">Flo Cube is an imaginary photographer, traveling the world aiming to inspire others. Telling unforgettable tales in lands far far away, beyond mountains, across seas, flying the mechanical bird from a to b. Photography has always been a passion of Flo Cube, and videography also has a soft spot in its heart. Influenced by clean lines, subtle movements and bold typography, you’ll find Flo Cube has something to fit everyones needs. Offering you the opportunity to enhance your portfolio, and book the clients you’ve always desired.</p>
					<p class="wow fadeInUp">...</p><br/><br/>
					<a id="read-more-link" href="/about" class="wow fadeInUp">Read More</a>
				</div>
				<div class="info-ctn clients-awards">
					<h2 class="wow fadeInUp">Clients</h2>
					<ul id="client-list" class="wow fadeInUp">
						<li><a href="">Cool Co.</a></li>
						<li><a href="">Liferay</a></li>
						<li><a href="">Fresh Kicks</a></li>
						<li><a href="">Jesus Rocks</a></li>
						<li><a href="">Lift em Up</a></li>
					</ul>
					<h2 class="wow fadeInUp">Awards</h2>
					<ul class="wow fadeInUp">
						<li>
							<a href="">Wedding Guru 2016</a>
						</li>
						<li>
							<a href="">Top Gun Photographer 2015</a>
						</li>
						<li>
							<a href="">Ultimate editorial photographer 2014</a>
						</li>
					</ul>
				</div>

				<div class="info-ctn projects wow fadeInUp">
					<a href="/projects" class="no-wrap">Check All Projects</a>
				</div>

			</section>

			<section id="media-stacked">
				<div class="media-ctn wow fadeInUp">
					<div class="text-ctn">
						<h2>Light</h2>
						<h1>Ryan & Daisy</h1>
						<h3>Unforgettable</h3>
					</div>
					<div id="img1" class="wide-image" />
				</div>
				<div class="media-ctn wow fadeInUp">
					<div id="img2" class="wide-image" />
					<div class="text-ctn">
						<h2>Wedding</h2>
						<h1>Montage</h1>
					</div>
				</div>
			</section>

			<aside id="photo-mini-tiles" class="wow fadeInUp">
				<div id="mini-img1" />
				<div id="mini-img2" />
				<div id="mini-img3" />
				<div id="mini-img4" />
				<div id="mini-img5" />
				<div id="mini-img6" />
			</aside>

		</main>


		<footer class="wow fadeInUp">
			<div id="footer-desktop">
				<div id="left-ctn">
					<div class="top-ctn">
						<div class="logo" src="" alt="">Lance</div>
						<div class="nav-ctn">
							<h3>Home</h3>
							<h3>About</h3>
						</div>
						<div class="nav-ctn">
							<h3>Portfolio</h3>
							<h3>Contact</h3>
						</div>
					</div>
					<div id="copyright" class="bottom-ctn">
						<h4>© 2017 Lance Photography, Inc. | Images from pixabay</h4>
					</div>
				</div>

				<div id="right-ctn">
					<div>
						<div>
							<h2 class="italic footer-co">Lance Photography</h2>
						</div>
						<div class="text-ctn">
							<p class="italic">info@lancephotography.com &nbsp;&nbsp; |</p>
							<p class="italic">&nbsp;&nbsp;&nbsp;(661) 222-0000</p>
						</div>
					</div>
					<div class="bottom-ctn">
						<i class="icon-facebook" aria-hidden="true"></i>
						<i class="icon-twitter" aria-hidden="true"></i>
						<i class="icon-instagram" aria-hidden="true"></i>
					</div>
				</div>
			</div>

			<div id="footer-mobile">
				<div class="logo" src="" alt="">Lance</div>
				<h3><a href="/">Home</a></h3>
				<h3><a href="/about">About</a></h3>
				<h3><a href="/portfolio">Portfolio</a></h3>
				<h3><a href="/contact">Contact</a></h3>
				<h2 class="italic footer-co">Lance Photography</h2>
				<div class="contact-info">
					<p class="italic">info@lancephotography.com &nbsp;&nbsp; |</p>
					<p class="italic">&nbsp;&nbsp;&nbsp;(661) 222-0000</p>
				</div>
				<div class="bottom-ctn">
					<i class="icon-facebook" aria-hidden="true"></i>
					<i class="icon-twitter" aria-hidden="true"></i>
					<i class="icon-instagram" aria-hidden="true"></i>
				</div>
				<h4>© 2017 Lance Photography, Inc. | Images from pixabay</h4>
			</div>
		</footer>
	</div>	
</div>
<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!--<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.slick/1.6.0/slick.min.js"></script>-->
<!-- endinject -->
<!--<script src="/o/photography-theme/js/slick.min.js"></script>-->
<script type="text/javascript">

</script>


</body>

</html>