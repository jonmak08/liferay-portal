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
		<div id="heading">
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
		</div>

		<#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if has_navigation && is_setup_complete>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
	</header>
	<section id="content">
		<h1 class="hide-accessible">${the_title}</h1>

		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>
		<button onclick="topFunction()" id="totopbtn" title="Scroll Back Up">&#8593;</button>
	</section>

	<footer id="footer" role="contentinfo">
		 <div>
		  <div id="footer1">
			<p class="footer-heading">This is Eric's theme site</p>
				<ul>
					<li><a href="/web/guest/home">Home </a></li>
					<li><a href="/web/guest/about-us">About Us </a></li>
					<li><a href="/web/guest/contact-us">Contact Us</a></li>
				</ul>
		  </div>

		  <div id=footer2>
		  	<p class="footer-heading">Other Similar Sites:</p>
		  		<ul>
		  			<li><a href="#">Alec's Theme</a> </li>
		  			<li><a href="#">Brian's Dad's Theme</a> </li>
		  			<li><a href="#">Jon's Theme</a></li>
		  			<li><a href="#">Josh's Theme</a></li>
		  			<li><a href="#">Rebecca's Theme</a></li>
		  			
		  		</ul>
		  </div>
		 </div>
	  <div id="copyright">
	  	<p class="powered-by">
			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a> </p>
	  	<p>&copy; Eric Ji 2018</p>
	  </div>
	
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
2027
<!-- endinject -->

</body>

</html>