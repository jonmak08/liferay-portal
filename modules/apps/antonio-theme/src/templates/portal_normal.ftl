<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>
	<link rel="stylesheet" href="../css/_custom.css">
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div id="antonio-theme">
	<header id="banner" role="banner">
		<!-- <div id="heading">
			<h1 class="site-title">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>
			</h1>
		</div> -->

		<#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>


		<!-- <#if has_navigation && is_setup_complete>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if> -->

	</header>

	<nav class="navbar-custom navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button id="hamburger-menu" type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<!-- <span>MENU</span> -->
				</button>
				<a class="navbar-brand brand-name" href="#">appetite</a>
			</div>

			<div class="nav-links collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">home</a></li>
					<li><a href="#">menu</a></li>
					<li><a href="#">blog</a></li>
					<li><a href="#">features</a></li>
					<li><a href="#">support</a></li>
					<li><a href="#">testimonials</a></li>
					<li><a id="search-icon" href="#"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a></li>
				</ul>
			</div>
			<!-- .navigation-links .collapse .navbar-collapse -->
		</div>
		<!-- .container-fluid -->
	</nav>

	<div id="carousel-slide-one" class="welcome-container welcome-background-one">
    <div class="welcome-content container">
      <div class="welcome-header">
        <h2>welcome to our restaurant</h2>
      </div>
			<div class="row">
				<div class="welcome-description col-md-6 col-md-offset-3">
					<p>
						Tumblr next level actually helvetica. Health goth tattooed fam mlkshk, blue bottle bicycle rights paleo. Mumblecore crucifix sartorial tote bag lyft, la croix skateboard messenger bag tacos fanny pack organic. Crucifix offal meh tbh. IPhone bitters kinfolk
						waistcoat, seitan master cleanse farm-to-table.
					</p>
					<div class="welcome-more-info">
						<a href="#">read our story</a>
					</div>
					<!-- .more-info -->
				</div>
			<!-- .welcome-description .col-md-6 .col-md-offset-3 -->
	    </div>
	    <!-- .row -->
		</div>
		<!-- .welcome-content .container -->

		<div class="col-md-6 col-md-offset-3 text-center">
			<div class="pagination">
				<div id="pager-item-one">
					<a href="#"></a>
				</div>
				<!-- .pager-item -->
				<div id="pager-item-two">
					<a href="#"></a>
				</div>
				<!-- .pager-item -->
				<div id="pager-item-three">
					<a href="#"></a>
				</div>
				<!-- .pager-item -->
			</div>
			<!-- .pagination -->
		</div>
		<!-- .col-md-6 .col-md-offset-3 -->
	</div>
	<!-- #carousel-slide-one .welcome-container .welcome-background-one -->

	<div id="carousel-slide-two" class="welcome-container welcome-background-two">
    <div class="welcome-content container">
			<div class="welcome-header">
				<h2>we serve delicious dishes</h2>
			</div>
			<div class="row">
				<div class="welcome-description col-md-6 col-md-offset-3">
					<p>
						Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer euismod lacus luctus magna. Quisque cursus, metus vitae pharetra auctor, sem massa mattis sem, at interdum magna augue eget diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Morbi lacinia molestie dui. Praesent blandit dolor. Sed non quam. In vel mi sit amet augue congue elementum.
					</p>
					<div class="welcome-more-info">
						<a href="#">read more</a>
					</div>
					<!-- .more-info -->
				</div>
			<!-- .welcome-description .col-md-6 .col-md-offset-3 -->
			</div>
			<!-- .row -->
		</div>
		<!-- .welcome-content .container -->

		<div class="col-md-6 col-md-offset-3 text-center">
			<div class="pagination">
				<div id="pager-item-one">
					<a href="#"></a>
				</div>
				<!-- .pager-item -->
				<div id="pager-item-two">
					<a href="#"></a>
				</div>
				<!-- .pager-item -->
				<div id="pager-item-three">
					<a href="#"></a>
				</div>
				<!-- .pager-item -->
			</div>
			<!-- .pagination -->
		</div>
		<!-- .col-md-6 .col-md-offset-3 -->
	</div>
	<!-- #carousel-slide-two .welcome-container .welcome-background-two -->

	<div id="carousel-slide-three" class="welcome-container welcome-background-three">
    <div class="welcome-content container">
			<div class="welcome-header">
        <h2>enjoy the best live music</h2>
      </div>
			<div class="row">
				<div class="welcome-description col-md-6 col-md-offset-3">
					<p>
						Quisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis.
					</p>
					<div class="welcome-more-info">
						<a href="#">view schedule</a>
					</div>
					<!-- .more-info -->
				</div>
			<!-- .welcome-description .col-md-6 .col-md-offset-3 -->
	    </div>
	    <!-- .row -->
		</div>
		<!-- #carousel-slide-one .welcome-content .container -->

		<div class="col-md-6 col-md-offset-3 text-center">
			<div class="pagination">
				<div id="pager-item-one">
					<a href="#"></a>
				</div>
				<!-- .pager-item -->
				<div id="pager-item-two">
					<a href="#"></a>
				</div>
				<!-- .pager-item -->
				<div id="pager-item-three">
					<a href="#"></a>
				</div>
				<!-- .pager-item -->
			</div>
			<!-- .pagination -->
		</div>
		<!-- .col-md-6 .col-md-offset-3 -->
	</div>
	<!-- #carousel-slide-three .welcome-container .welcome-background-three -->

	<div class="widgets">
    <section id="section-one" class="section-white">
      <div class="container">
				<div class="row">
					<div class="col-md-12">
						<article id="article-one">
							<div class="image-container col-md-6">
								<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg" alt="article-one-image">
							</div>
							<!-- .image-container col-md-6 -->
							<div class="content-container col-md-6">
								<header class="article-content-header">
									<h2 class="article-title">
										<a href="#">we make food that offers variety of flavors and tastes</a>
									</h2>
									<div class="article-subtitle">This is a featured image caption, and you can add any text you want.</div>
								</header>
								<div class="article-separator"></div>
								<div class="article-entry">
									<p>
										Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism. Hashtag snackwave plaid, roof party irony tote bag sustainable typewriter mlkshk synth retro vegan selfies salvia. Subway tile yr quinoa master cleanse
										twee. Man bun bespoke squid pinterest mumblecore, waistcoat VHS keytar chia.
									</p>
									<div class="more-info">
										<a href="#">read more</a>
									</div>
									<!-- .more-info -->
								</div>
								<!-- .article-entry -->
							</div>
							<!-- .content-container col-md-6 -->
						</article>
						<!-- #article-one -->
					</div>
					<!-- col-md-12 -->
				</div>
				<!-- row -->
      </div>
      <!-- .container -->
    </section>
    <!-- .section-white -->

    <section id="section-two" class="section-tan">
      <div class="container">
				<div class="row">
					<div class="col-md-12">
						<article id="article-two">
							<div class="image-container col-md-6">
								<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
							</div>
							<!-- .image-container col-md-6 -->
							<div class="content-container col-md-6">
								<header class="article-content-header">
									<h2 class="article-title">
										<a href="#">we make food that offers variety of flavors and tastes</a>
									</h2>
									<div class="article-subtitle">This is a featured image caption, and you can add any text you want.</div>
								</header>
								<div class="article-separator"></div>
								<div class="article-entry">
									<p>
										Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism. Hashtag snackwave plaid, roof party irony tote bag sustainable typewriter mlkshk synth retro vegan selfies salvia. Subway tile yr quinoa master cleanse
										twee. Man bun bespoke squid pinterest mumblecore, waistcoat VHS keytar chia.
									</p>
									<div class="more-info">
										<a href="#">read more</a>
									</div>
									<!-- .more-info -->
								</div>
								<!-- .article-entry -->
							</div>
							<!-- .content-container col-md-6 -->
						</article>
						<!-- #article-two -->
					</div>
					<!-- col-md-12 -->
				</div>
				<!-- row -->
      </div>
      <!-- .container -->
    </section>
    <!-- .section-tan -->

		<section id="section-three" class="section-white">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<h2 class="section-title">The Staff</h2>
					</div>
					<!-- .col-md-4 col-md-offset-4 -->
				</div>
				<!-- .row -->
			</div>
			<!-- .container -->

			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<article class="profile">
							<div class="col-md-4">
								<div class="image-container">
									<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
								</div>
								<!-- .image-container -->
								<div class="content-container">
									<header class="article-content-header">
										<h2 class="article-title profile-name">
											<a href="#">profile name</a>
										</h2>
										<div class="article-subtitle">profile caption</div>
									</header>
									<div class="article-separator centered"></div>
									<div class="article-entry">
										<p>
											Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism. Hashtag snackwave plaid, roof party irony tote bag sustainable typewriter mlkshk synth retro vegan selfies salvia.
										</p>
										<div class="more-info">
											<a href="#">read more</a>
										</div>
										<!-- .more-info -->
									</div>
									<!-- .article-entry -->
								</div>
								<!-- .content-container -->
							</div>
							<!-- .col-md-4 -->
						</article>
						<!-- .profile -->

						<article class="profile">
							<div class="col-md-4">
								<div class="image-container">
									<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
								</div>
								<!-- .image-container -->
								<div class="content-container">
									<header class="article-content-header">
										<h2 class="article-title profile-name">
											<a href="#">profile name</a>
										</h2>
										<div class="article-subtitle">profile caption</div>
									</header>
									<div class="article-separator centered"></div>
									<div class="article-entry">
										<p>
											Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism. Hashtag snackwave plaid, roof party irony tote bag sustainable typewriter mlkshk synth retro vegan selfies salvia.
										</p>
										<div class="more-info">
											<a href="#">read more</a>
										</div>
										<!-- .more-info -->
									</div>
									<!-- .article-entry -->
								</div>
								<!-- .content-container -->
							</div>
							<!-- .col-md-4 -->
						</article>
						<!-- .profile -->

						<article class="profile">
							<div class="col-md-4">
								<div class="image-container">
									<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
								</div>
								<!-- .image-container -->
								<div class="content-container">
									<header class="article-content-header">
										<h2 class="article-title profile-name">
											<a href="#">profile name</a>
										</h2>
										<div class="article-subtitle">profile caption</div>
									</header>
									<div class="article-separator centered"></div>
									<div class="article-entry">
										<p>
											Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism. Hashtag snackwave plaid, roof party irony tote bag sustainable typewriter mlkshk synth retro vegan selfies salvia.
										</p>
										<div class="more-info">
											<a href="#">read more</a>
										</div>
										<!-- .more-info -->
									</div>
									<!-- .article-entry -->
								</div>
								<!-- .content-container -->
							</div>
							<!-- .col-md-4 -->
						</article>
						<!-- .profile -->
					</div>
					<!-- .col-md-12 -->
				</div>
				<!-- .row -->
			</div>
			<!-- .container -->
		</section>
		<!-- .section-white -->

		<section id="section-four" class="section-tan">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<h2 class="section-title">Recent News</h2>
					</div>
					<!-- .col-md-4 .col-md-offset-4 -->
				</div>
				<!-- .row -->
			</div>
			<!-- .container -->

			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<article id="news-one" class="news">
							<div class="col-md-4">
								<div class="image-container">
									<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
								</div>
								<!-- .image-container -->
								<div class="content-container">
									<header class="article-content-header">
										<h2 class="article-title news-title">
											<a href="#">news title</a>
										</h2>
										<div class="article-subtitle">news caption</div>
									</header>
									<div class="article-separator centered"></div>
									<div class="article-entry">
										<p>
											Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism.
										</p>
										<div class="more-info">
											<a href="#">read more</a>
										</div>
										<!-- .more-info -->
									</div>
									<!-- .article-entry -->
								</div>
								<!-- .content-container -->
							</div>
							<!-- .col-md-4 -->
						</article>
						<!-- .news -->

						<article id="news-two" class="news">
							<div class="col-md-4">
								<div class="image-container">
									<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
								</div>
								<!-- .image-container -->
								<div class="content-container">
									<header class="article-content-header">
										<h2 class="article-title news-title">
											<a href="#">news title</a>
										</h2>
										<div class="article-subtitle">news caption</div>
									</header>
									<div class="article-separator centered"></div>
									<div class="article-entry">
										<p>
											Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism.
										</p>
										<div class="more-info">
											<a href="#">read more</a>
										</div>
										<!-- .more-info -->
									</div>
									<!-- .article-entry -->
								</div>
								<!-- .content-container -->
							</div>
							<!-- .col-md-4 -->
						</article>
						<!-- .news -->

						<article id="news-three" class="news">
							<div class="col-md-4">
								<div class="image-container">
									<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
								</div>
								<!-- .image-container -->
								<div class="content-container">
									<header class="article-content-header">
										<h2 class="article-title news-title">
											<a href="#">news title</a>
										</h2>
										<div class="article-subtitle">news caption</div>
									</header>
									<div class="article-separator centered"></div>
									<div class="article-entry">
										<p>
											Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism.
										</p>
										<div class="more-info">
											<a href="#">read more</a>
										</div>
										<!-- .more-info -->
									</div>
									<!-- .article-entry -->
								</div>
								<!-- .content-container -->
							</div>
							<!-- .col-md-4 -->
						</article>
						<!-- .news -->

						<article id="news-four" class="news">
							<div class="col-md-4">
								<div class="image-container">
									<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
								</div>
								<!-- .image-container -->
								<div class="content-container">
									<header class="article-content-header">
										<h2 class="article-title news-title">
											<a href="#">news title</a>
										</h2>
										<div class="article-subtitle">news caption</div>
									</header>
									<div class="article-separator centered"></div>
									<div class="article-entry">
										<p>
											Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism.
										</p>
										<div class="more-info">
											<a href="#">read more</a>
										</div>
										<!-- .more-info -->
									</div>
									<!-- .article-entry -->
								</div>
								<!-- .content-container -->
							</div>
							<!-- .col-md-4 -->
						</article>
						<!-- .news -->

						<article id="news-five" class="news">
							<div class="col-md-4">
								<div class="image-container">
									<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
								</div>
								<!-- .image-container -->
								<div class="content-container">
									<header class="article-content-header">
										<h2 class="article-title news-title">
											<a href="#">news title</a>
										</h2>
										<div class="article-subtitle">news caption</div>
									</header>
									<div class="article-separator centered"></div>
									<div class="article-entry">
										<p>
											Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism.
										</p>
										<div class="more-info">
											<a href="#">read more</a>
										</div>
										<!-- .more-info -->
									</div>
									<!-- .article-entry -->
								</div>
								<!-- .content-container -->
							</div>
							<!-- .col-md-4 -->
						</article>
						<!-- .news -->

						<article id="news-six" class="news">
							<div class="col-md-4">
								<div class="image-container">
									<img src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
								</div>
								<!-- .image-container -->
								<div class="content-container">
									<header class="article-content-header">
										<h2 class="article-title news-title">
											<a href="#">news title</a>
										</h2>
										<div class="article-subtitle">news caption</div>
									</header>
									<div class="article-separator centered"></div>
									<div class="article-entry">
										<p>
											Poke lomo keytar, pour-over disrupt pabst twee live-edge tattooed etsy subway tile mlkshk tote bag prism.
										</p>
										<div class="more-info">
											<a href="#">read more</a>
										</div>
										<!-- .more-info -->
									</div>
									<!-- .article-entry -->
								</div>
								<!-- .content-container -->
							</div>
							<!-- .col-md-4 -->
						</article>
						<!-- .news -->
					</div>
					<!-- .col-md-12 -->
				</div>
				<!-- .row -->
			</div>
			<!-- .container -->
		</section>
		<!-- .section-white -->

		<section id="section-five" class="section-white">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<h2 class="section-title">Testimonials</h2>
					</div>
					<!-- .col-md-4 .col-md-offset-4 -->
					<div class="col-md-10 col-md-offset-1">
						<p class="testimonial-entry">
							Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed a elit massa. Proin vel consequat mi. Integer viverra enim a vulputate porttitor
							<br>
							<br>
							<span class="testimonial-author">Antonio</span>
						</p>
					</div>
					<!-- .col-md-6 .col-md-offset-3 -->
				</div>
				<!-- .row -->
				<div class="col-md-6 col-md-offset-3 text-center">
					<ul class="testimonial-list">
						<li>
							<a href="#">
								<img id="testimonial-one" class="testimonial-thumb" src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
							</a>
						</li>
						<li>
							<a href="#">
								<img id="testimonial-two" class="testimonial-thumb" src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
							</a>
						</li>
						<li>
							<a href="#">
								<img id="testimonial-three" class="testimonial-thumb" src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
							</a>
						</li>
						<li>
							<a href="#">
								<img id="testimonial-four" class="testimonial-thumb" src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
							</a>
						</li>
						<li>
							<a href="#">
								<img id="testimonial-five" class="testimonial-thumb" src="https://appetitedemo.files.wordpress.com/2015/07/smachno_19.jpg">
							</a>
						</li>
					</ul>
					<!-- .testimonial-list -->
				</div>
				<!-- .col-md-6 .col-md-offset-3 .text-center -->
			</div>
			<!-- .container -->

		</section>
		<!-- #section-five .section-white -->
	</div>
	<!-- .widgets -->
	<footer class="site-footer">
		<div class="container">
			<div class="footer-content col-md-12">
				<div class="col-md-4 footer-section">
					<div class="footer-section-title">
						<h4>Opening Hours</h4>
					</div>
					<!-- .footer-section-title -->
					<ul class="opening-hours">
						<li>08:00am - 05:00pm</li>
						<li>08:00am - 05:00pm</li>
						<li>08:00am - 05:00pm</li>
						<li>08:00am - 05:00pm</li>
						<li>08:00am - 05:00pm</li>
					</ul>
					<!-- .opening-hours -->
				</div>
				<!-- .col-md-4 .footer-section-1 -->
				<div class="col-md-4 footer-section">
					<div class="footer-section-title">
						<h4>Recent Posts</h4>
					</div>
					<ul class="recent-posts">
						<li><a href="#">This is a recent post.</a></li>
						<hr>
						<li><a href="#">This is a recent post.</a></li>
						<hr>
						<li><a href="#">This is a recent post.</a></li>
					</ul>
					<!-- .footer-section-title -->
				</div>
				<!-- .col-md-4 .footer-section-2 -->
				<div class="col-md-4 footer-section">
					<div class="footer-section-title">
						<h4>About Appetite</h4>
					</div>
					<p class="footer-text">
						Praesent leo lacus, gravida ac quam non, eleifend posuere justo. Ut et est tempor, congue elit vitae, varius nibh. Integer vehicula, nulla sed condimentum convallis, urna elit pellentesque enim, in sollicitudin mauris tortor a odio.
					</p>
					<!-- .footer-section-title -->
				</div>
				<!-- .col-md-4 .footer-section-3 -->
			</div>
			<!-- .footer-content .col-md-10 .col-md-offset-1 -->
			<div class="col-md-12 text-center social-media">
				<ul class="social-media-list">
					<li>
						<a href="#"><span class="glyphicon glyphicon-camera social-media-icon" aria-hidden="true"></span></a>
					</li>
					<li>
						<a href="#"><span class="glyphicon glyphicon-camera social-media-icon" aria-hidden="true"></span></a>
					</li>
					<li>
						<a href="#"><span class="glyphicon glyphicon-camera social-media-icon" aria-hidden="true"></span></a>
					</li>
					<li>
						<a href="#"><span class="glyphicon glyphicon-camera social-media-icon" aria-hidden="true"></span></a>
					</li>
					<li>
						<a href="#"><span class="glyphicon glyphicon-camera social-media-icon" aria-hidden="true"></span></a>
					</li>
					<li>
						<a href="#"><span class="glyphicon glyphicon-camera social-media-icon" aria-hidden="true"></span></a>
					</li>
					<li>
						<a href="#"><span class="glyphicon glyphicon-camera social-media-icon" aria-hidden="true"></span></a>
					</li>
				</ul>
			</div>
			<!-- .col-md-12 .text-center -->

			<div class="col-md-12 text-center contact-info">
				<span class="contact-info">Put your contact info here</span>
			</div>
			<!-- .col-md-12 .text-center .contact-info -->

			<div class="col-md-12 text-center site-info">
				<a href="#"><span>Site info goes here</span></a>
			</div>
			<!-- .col-md-12 .text-center .site-info -->
		</div>
		<!-- .container -->
	</footer>
</div>
<!-- #antonio-theme -->

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

</body>
<script src="../js/main.js" charset="utf-8"></script>
</html>
