<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />


<div class="container-fluid" id="wrapper">
	<header id="banner" role="banner">



		<!-- Landing page description for company -->
		<h2 class="landing-description">growing and increasing as a <strong>family</strong> <br> one cup at a time.</h2>
		<div id="heading">
			<h1 class="site-title">
			
				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						Auxano
					</span>
				</#if>
			</h1>
		</div>

		<#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if has_navigation && is_setup_complete>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
	</header>

	<section id="content">



		<!-- New Arrivals Content -->
		<div class="new-arrivals">
			<div class="new-arrivals-content">
				<p id="colombia">Colombia</p>
				<h4 id="colombia-region">Tres Santos Colombia</h4>
				<hr class="new-arrivals-break">
				<a href="#"><h3 class="new-arrivals-link">New Arrivals</h3></a>
			</div>
		</div>




		<!-- Coffee Tutorial Cards -->
		<div class="card-container">

			<div class="card">
				<div id="image-learn" class="card-image">
					
				</div>
				<div id="card-description">
					<a href="#"><h5><br>Hario v60 Guide</h5></a>
					<p>The Hario V60 method we at Auxano use to brew coffee from our fresh roasted beans.</p>
					<a href="#"><h6>Learn</h6></a>
				</div>
			</div>

			<div class="card">
				<div id="image-blog" class="card-image">
					
				</div>
				<div id="card-description">
					<a href="#"><h5>Grinder Settings<br>Fine vs Coarse</h5></a>
					<p>Our findings on how the coarseness of our grounds affect the taste of coffee.</p>
					<a href="#"><h6>Blog</h6></a>
				</div>
			</div>

			<div class="card">
				<div id="image-event" class="card-image">
					
				</div>
				<div id="card-description">
					<a href="#"><h5><br>Latte Art Class</h5></a>
					<p>Come join us for an evening to perfect your latte art skills with our world-class baristas.</p>
					<a href="#"><h6>Event</h6></a>
				</div>
			</div>

			<div class="card">
				<div id="image-about" class="card-image">
					
				</div>
				<div id="card-description">
					<a href="#"><h5><br>Welcome Zach</h5></a>
					<p>We'd like to introduce you to the newest member of our family, Zach!</p>
					<a href="#"><h6>About</h6></a>
				</div>
			</div>
		</div>




		<!-- Email Sign-Up Section -->
		<div class="email-subscription-container">
			<div class="email-subscription-text">
				<H2 id="newsletter">Auxano Newsletter</H2>
				<p id="newsletter-description">We're constantly learning and growing and we want to share it with you.
				Be the first to know about our newest releases and findings.</p>
				<div id="email-subscription-links">
					<a href="#"><h6 id="color-777">Email Address</h6></a>
					<a href="#"><h6>Subscribe</h6></a>
				</div>
				<hr class="newsletter-break">
			</div>
		</div>



		<!-- Footer Contact -->
		<div class="info-footer-container">
			<ul id="info-column-1">
				<li><a href="#"><h3>Home</h3></a><br></li>
				<li><a href="#"><h3>Shop</h3></a><br></li>
				<li><a href="#"><h3>Learn</h3></a><br></li>
				<li><a href="#"><h3>About</h3></a><br></li>
			</ul>
			<ul id="info-column-2">
				<li><h3>Hours</h3><br></li>
				<li><h3>Monday - Friday<br><span id="list-body-font">8am - 10pm</span></h3></li>
				<li><h3>Saturday - Sunday<br><span id="list-body-font">10am - 9pm</span></h3></li>
			</ul>
			<ul id="info-column-3">
				<li><a href="#"><h3>Auxano Coffee Roasters<br></h3></a></li>
				<li><a href="#"><h3>1400 Montefino Ave<br>Diamond Bar, CA 91765</h3></a></li>
				<li><a href="#"><h3>+1 800 284 1923<br></h3></a></li>
				<li><a href="#"><h3>contact@auxano.com<br></h3></a></li>
			</ul>
		</div>



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
	</section>
	<footer id="footer" role="contentinfo">
		<p class="powered-by">
			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
		</p>
	</footer>
</div>
<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>