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

<div class="pt-0" id="wrapper">
	<#if show_header>
		<header id="banner">
			<div class="navbar navbar-classic navbar-top py-3">
				<div class="container user-personal-bar">
					<div class="align-items-center autofit-row">

						<#assign preferences = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "barebone", "destination": "/search"}) />

					</div>
				</div>
			</div>

			<#--  SITE LOGO AND NAME  -->
			<div class="nav-flex mb-4">
				<a class="${logo_css_class} align-items-center d-md-inline-flex d-sm-none d-none logo-md" href="${site_default_url}" title="<@liferay.language_format arguments="" key="go-to-x" />">
					<img alt="${logo_description}" class="mr-2" height="56" src="${site_logo}" />

					<#if show_site_name>
						<h1 class="font-weight-bold h2 mb-0 text-dark">${site_name}</h1>
					</#if>
				</a>
				<#--  SITE LOGO AND NAME END  -->

				<#--  NAVIGATION  -->
				<div class="navbar navbar-classic navbar-expand-md navbar-light">
					<div class="container">
						<a class="${logo_css_class} d-inline-flex d-md-none logo-xs" href="${site_default_url}" rel="nofollow">
							<img alt="${logo_description}" class="mr-2" height="56" src="${site_logo}" />

							<#if show_site_name>
								<h1 class="font-weight-bold h2 mb-0 text-dark">${site_name}</h1>
							</#if>
						</a>

						<#include "${full_templates_path}/navigation.ftl" />
						<#if show_header_search>
							<div class="justify-content-md-end mr-4 navbar-form" role="search">
								<@liferay.search_bar default_preferences="${preferences}" />
								<svg aria-hidden="true" class="lexicon-icon ml-2 lexicon-icon-add-column lexicon-icon-search">
									<use xlink:href="${themeDisplay.getPathThemeImages()}/clay/icons.svg#search" />
								</svg>
							</div>
						</#if>
						<@liferay.user_personal_bar />
					</div>
				</div>
			</div>


			<#--  NAVIGATION END  -->
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
		<footer id="footer" role="contentinfo">
			<div class="container">
				<div class="row">
					<div class="footer-info">
							<p>COPYRIGHT &copy; all rights reserved</p>
							<p>Proudly powered by 
							<a class="text-white" href="http://www.liferay.com" rel="external">Liferay</a> | Brian Vales</p>
						</div>
				</div>

			</div>
		</footer>
	</#if>

</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>