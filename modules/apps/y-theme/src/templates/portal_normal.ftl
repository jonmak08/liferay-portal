<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
	<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<!--
<div class="container-fluid parallaximage">
<h1>hi</h1>
</div>
//-->

<div class="container-fluid" id="wrapper">
	<!-- Original for backup
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
	//-->

	<header id="banner" role="banner">
		<div class="parallaximagespacer">
			<!--
			<div style="background-image: url('${theme.getSetting("bannerimage")}'); background-size: cover; background-repeat: no-repeat; background-position: center center; width: 100%; height: 100%; opacity: 1; visibility: inherit; z-index: 0; position: absolute; left: 0; top: 0; border: 4px solid magenta;">
			//-->
			<!--
			<div style="background-image: url('${banner_img}'); background-size: cover; background-repeat: no-repeat; background-position: center center; width: 100%; height: 100%; opacity: 1; visibility: inherit; z-index: 0; position: absolute; left: 0; top: 0; border: 4px solid magenta;">
			//-->
			<div style="background-image: url('${theme_settings["bannerimage"]}'); background-size: cover; background-repeat: no-repeat; background-position: center center; width: 100%; height: 100%; opacity: 1; visibility: inherit; z-index: 0; position: absolute; left: 0; top: 0;">
<!--	
			<h1>theme.getSetting("bannerimage")=${theme.getSetting("bannerimage")}</h1>
			<h1>themeDisplay.getThemeSetting("bannerimage")=${themeDisplay.getThemeSetting("bannerimage")}</h1>
			<h1>theme_settings["bannerimage"]=${theme_settings["bannerimage"]}</h1>
			<h1>dummy, from database: ${theme.getSetting("dummy")}</h1>
			<h1>dummy, more current: ${themeDisplay.getThemeSetting("dummy")}</h1>
			<h1>associative array: ${theme_settings["dummy"]}</h1>
//-->
		<!--
		<h1>lorem ipsum or whatever</h1>
		//-->
				
			</div>
		</div>
	</header>
<!--
	<section id="oldheader">
		<div class="parallaximagespacer">
		</div>
		<div>
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
	</section>
//-->

	<section id="content">
<!--
		<div class="parallaximagespacer">
		</div>
//-->
		<div>
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


		<h1 class="hide-accessible">${the_title}</h1>
		<!--
		<nav id="breadcrumbs">
			<@liferay.breadcrumbs />
		</nav>
		//-->
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

<!-- inject:js -->
<!-- endinject -->

</body>

</html>
