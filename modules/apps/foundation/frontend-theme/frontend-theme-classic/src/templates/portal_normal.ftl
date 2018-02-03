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

<<<<<<< HEAD
<div class="pt-0" id="wrapper">
	<header id="banner">
		<div class="navbar navbar-top navigation-bar-secondary">
			<div class="container user-personal-bar">
				<#assign preferences = freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />

				<#if show_header_search>
					<div class="ml-auto mr-4 navbar-form" role="search">
						<@liferay.search default_preferences="${preferences}" />
					</div>
				</#if>

				<@liferay.user_personal_bar />
			</div>
		</div>

		<div class="mb-4 navbar navbar-expand-md navbar-light navbar-classic py-4">
			<div class="container">
				<a class="${logo_css_class} align-items-center d-inline-flex" href="${site_default_url}" title="<@liferay.language_format arguments="" key="go-to-x" />">
					<img alt="${logo_description}" class="mr-3" height="48" src="${site_logo}" />

					<h1 class="font-weight-bold h2 mb-0 text-dark">${site_name}</h1>
				</a>

				<#include "${full_templates_path}/navigation.ftl" />
			</div>
		</div>
	</header>

	<section class="container" id="content">
		<h1 class="sr-only">${the_title}</h1>
=======
<div id="wrapper">
	<header class="container-fluid-1280" id="banner" role="banner">
		<div class="row">
			<div class="navbar-header" id="heading">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="64" src="${site_logo}" />
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>

				<#if is_setup_complete>
					<button aria-controls="navigation" aria-expanded="false" class="collapsed navbar-toggle" data-target="#navigationCollapse" data-toggle="collapse" type="button">
						<span class="icon-bar"></span>

						<span class="icon-bar"></span>

						<span class="icon-bar"></span>
					</button>

					<div class="pull-right user-personal-bar">
						<@liferay.user_personal_bar />
					</div>
				</#if>
			</div>

			<#include "${full_templates_path}/navigation.ftl" />
		</div>
	</header>

	<section class="container-fluid-1280" id="content">
		<h1 class="hide-accessible">${the_title}</h1>
>>>>>>> compatible

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

<<<<<<< HEAD
	<footer class="mt-3 navigation-bar-secondary" id="footer" role="contentinfo">
		<div class="container py-5">
			<div class="row">
				<div class="col-md-6 text-center text-md-left">
					<@liferay.language key="powered-by" />

					<a class="ml-1 text-white" href="http://www.liferay.com" rel="external">Liferay</a>
				</div>

				<div class="col-md-6 text-center text-md-right">
					2017
				</div>
			</div>
=======
	<footer class="container-fluid-1280" id="footer" role="contentinfo">
		<div class="row">
			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
>>>>>>> compatible
		</div>
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>