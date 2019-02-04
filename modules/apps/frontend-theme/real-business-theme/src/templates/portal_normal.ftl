<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="pt-0" id="wrapper">
	<#if show_header>
		<header id="banner">
			<div class="navbar navbar-classic navbar-top py-3">
				<div class="container user-personal-bar">
					<div class="align-items-center autofit-row">

						<#assign
							preferences = freeMarkerPortletPreferences.getPreferences(
									{
										"portletSetupPortletDecoratorId": "barebone",
										"destination": "/search"
									}
							)
						/>

					</div>
				</div>
			</div>

			<#--  SITE LOGO AND NAME  -->
			<div class="mb-4 nav-flex">
				<@liferay.user_personal_bar />
				<a class="${logo_css_class} align-items-center d-md-inline-flex d-none d-sm-none logo-md" href="${site_default_url}" title="<@liferay.language_format arguments="" key="go-to-x" />">
					<img alt="${logo_description}" class="mr-2" height="56" src="${site_logo}" />
				</a>
				<#--  SITE LOGO AND NAME END  -->

				<#--  NAVIGATION  -->
				<div class="navbar navbar-classic navbar-expand-lg navbar-light">
					<div class="container">
						<a class="${logo_css_class} d-inline-flex d-md-none logo-xs" href="${site_default_url}" rel="nofollow">
							<img alt="${logo_description}" class="mr-2" height="56" src="${site_logo}" />
						</a>

						<#include "${full_templates_path}/navigation.ftl" />

					</div>
				</div>
			</div>
			<#--  NAVIGATION END  -->
		<#if show_header_search>
			<div class="search-margin" role="search">
				<@liferay.search_bar default_preferences="${preferences}" />
			</div>
		</#if>
		</header>
	</#if>

	<section class="${portal_content_css_class}" id="content">
		<h1 class="sr-only">${the_title}</h1>

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

	<#if show_footer>
			<!-- FOOTER -->

		<footer class="footer-wrapper">

			<div class="footer-flex">

				<div class="footer-logo">
					<img alt="logo" src="${site_logo}">
				</div>

				<div class="footer-layout">
					<div class="company-info">
						<h3 class="h3">${site_name}</h3>
						<p>${site_description}</p>
					</div>

					<div class="site-navigation">
						<h3 class="h3">Site Navigation</h3>
							<div class="footer-nav">
									<#include "${full_templates_path}/footer.ftl" />
							</div>
					</div>

					<div class="footer-social">
						<h3 class="h3">Stay Connected</h3>
						<ul class="carousel-ul" id="footer-count">
							<#if show_facebook>
								<li class="carousel-social" title="Facebook"><a class="fa-facebook-f fab" href="http://${facebook_url}"></a></li>
							</#if>
							<#if show_twitter>
								<li class="carousel-social" title="Twitter"><a class="fa-twitter fab" href="http://${twitter_url}"></a></li>
							</#if>
							<#if show_pinterest>
								<li class="carousel-social" title="Pinterest"><a class="fa-pinterest-p fab" href="http://${pinterest_url}"></a></li>
							</#if>
							<#if show_google_plus>
								<li class="carousel-social" title="Google +"><a class="fa-google-plus-g fab" href="http://${google_plus_url}"></a></li>
							</#if>
							<#if show_skype>
								<li class="carousel-social" title="Skype"><a class="fa-skype fab" href="http://${skype_url}"></a></li>
							</#if>
							<#if show_instagram>
								<li class="carousel-social" title="Instagram"><a class="fa-instagram fab" href="http://${instagram_url}"></a></li>
							</#if>
							<#if show_youtube>
								<li class="carousel-social" title="Youtube"><a class="fa-youtube fab" href="http://${youtube_url}"></a></li>
							</#if>
							<#if show_linkedin>
								<li class="carousel-social" title="LinkedIn"><a class="fa-linkedin-in fab" href="http://${linkedin_url}"></a></li>
							</#if>
						</ul>
					</div>

				</div>

				<div class="footer-info">
					<p><@liferay.language key="COPYRIGHT &copy all rights reserved" /></p>
					<p><@liferay.language key="powered-by" /> <a class="text-white" href="http://www.liferay.com" rel="external">Liferay</a></p>
				</div>

			</div>

		</footer>
	</#if>

</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>