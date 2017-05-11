<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir='<@liferay.language key="lang.dir" />' lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />


	<!--<div class="left-white-column-container"></div>-->


	


</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<!--THIS IS THE HEADER OF THE SITE -->
<div class="container-fluid" id="wrapper">
	<header id="banner" role="banner">
		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-1 col-xs-6 white-banner">
				<div id="heading">
					<div class="logoR"></div>		
						<!-- <h1 class="site-title">
							<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
							<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
							</a>
							<#if show_site_name>
								<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
								${site_name}
								</span>
							</#if> -->
						</h1>				
				</div>
			</div>	
			<div class="col-lg-6 col-md-6 col-sm-11 col-xs-6 black-banner">
				<div class="menu">
					<ul>
						<li><a href="https://www.google.com">nav one</a></li>
						<li><a href="https://www.facebook.com">nav two</a></li>
						<li><a href="https://www.netflix.com">nav three</a></li>
					</ul>
				</div>
				<div class="sign-in-button">
					<div class="sign-in-line"></div>
						<#if !is_signed_in>
							<a class="sign-in li" data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
	        			</#if>
	        			<#if has_navigation && is_setup_complete>
         					<#include "${full_templates_path}/navigation.ftl" />
       					</#if>
				</div>	
			</div>	
		</div>
	</header>




<!--THIS IS THE HERO INTRO SECTION OF THE SITE -->	
<!--
<div class="hero-main-wrapper">
	<div class="hero-row row">
		<div class="col-sm-1 left-main-column-hero">
			<h2 class="vert-title"> HELLO </h2>
		</div>
		<div class="col-sm-11 right-main-column">
			<div class="hero-img-bg">
				<div class="col-md-6 col-sm-4 hero-left-side">
					<div class="hero-image-text">
						<p class="hero-text1">ryan connolly</p>
						<p class="hero-text2">senior ux/ui designer</p>
					</div>
				</div>	
				<div class="col-md-6 col-sm-8 hero-right-side">		
					<img class="imageRight" src="o/connolly-theme/images/face.png">
				</div>
			</div>	
			<section id="content" class="col-md-10 col-md-offset-1 col-sm-12">
				<div class="intro-text">Nullam eget erat eu metus tempor fringilla. Ut lacus orci, molestie ut fermentum vitae, posuere sit amet tellus. Aenean in turpis augue. Maecenas erat urna, efficitur nec porta rutrum, consectetur et erat. Nulla <a class="intro-text-link">varius odio erat</a> at viverra ante porttitor non. Vestibulum nec hendrerit enim, a rutrum lacus. Maecenas vestibulum, nunc a vulputate facilisis, purus lorem suscipit tellus, nec efficitur lacus metus at neque. Suspendisse vulputate ligula urna, non venenatis elit dapibus vel. </div>	
			</section>		
		</div>
	</div>	
</div>	

-->

<!--THIS IS THE PORTFOLIO SECTION OF THE SITE -->	
<!--
<div class="portfolio-main-wrapper">
	<div class="portfolio-row row">
		<div class="col-sm-1 left-main-column-portfolio">
			<h2 class="vert-title"> WORK </h2>
		</div>
		<div class="col-sm-11 right-main-column">
			<!-- <div id="content" class="col-md-10 col-md-offset-1"> -->
<!--
			<div class="portfolio-items-container">
				<div class="col-sm-6 left-portfolio-column">
					<div class="portfolio-item1"></div>
						<div class="portfolio-item-description">
							<div class="portfolio-item-description-left">
								<p>sample case study nam 1</p>
							</div>
							<div class="portfolio-item-description-right">+</div>
						</div>		
				</div>

				<div class="col-sm-6 right-portfolio-column">
					<div class="portfolio-item2"></div>
						<div class="portfolio-item-description">
							<div class="portfolio-item-description-left">
								<p>sample case study nam 1</p>
							</div>
							<div class="portfolio-item-description-right">+</div>
						</div>		
				</div>	

				<div class="col-sm-6 left-portfolio-column">
					<div class="portfolio-item3"></div>
						<div class="portfolio-item-description">
							<div class="portfolio-item-description-left">
								<p>sample case study nam 1</p>
							</div>
							<div class="portfolio-item-description-right">+</div>
						</div>		
				</div>

				<div class="col-sm-6 right-portfolio-column">
					<div class="portfolio-item4"></div>
						<div class="portfolio-item-description">
							<div class="portfolio-item-description-left">
								<p>sample case study nam 1</p>
							</div>
							<div class="portfolio-item-description-right">+</div>
						</div>		
				</div>		
			</div>
		</div>	
	</div>



<h1 class="hide-accessible">${the_title}</h1>

				<!--<nav id="breadcrumbs">
					<@liferay.breadcrumbs />
				</nav> -->

			<#if selectable>
				<@liferay_util["include"] page=content_include />
			<#else>
				${portletDisplay.recycle()}

				${portletDisplay.setTitle(the_title)}

				<@liferay_theme["wrap-portlet"] page="portlet.ftl">
					<@liferay_util["include"] page=content_include />
				</@>
			</#if>

</div>	




				<!-- <h1 class="hide-accessible">${the_title}</h1>

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
			</#if> -->
		



	<!--THIS IS FOOTER CONTACT SECTION OF THE SITE -->	

	<footer> <!--id="footer" role="contentinfo"> -->
	<!--<div id="footer"-->
	<div class="footer-main-wrapper">
		<div class="contact-row row">
			<div class="col-sm-1 left-main-column-contact">
				<h2 class="vert-title">${vert_title}</h2>
			</div>
			<div class="col-sm-11 right-main-column">
				<div class="col-sm-6 footer-left-column">
					<div class="footer-left-text">
						<div class="contact-info1">
							<h4>${user_name}</h4>
						</div>
						<div class="red-hr"></div>
						<div class="contact-info2">
							<ul class="footer-list">
								<li class="footer-list">${footer_street1}</li>
								<li class="footer-list">${footer_street2}</li>
								<li class="footer-list">${footer_street3}</li>	
							</ul>						
						</div>
						<p class="powered-by">
						<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
						</p>
					</div>
				</div>	
				<div class="col-sm-6 col-xs-12 footer-right-column">
					<div class="footer-right-text hello"><span>${message_1}</span>
					</div>
					<div class="footer-plus">
						<h3>+</h3>
					</div>
				</div>
			</div>
		</div>
	</div>

	</footer>


</div>



	
	</div>
</div>
<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->



</body>

</html>
