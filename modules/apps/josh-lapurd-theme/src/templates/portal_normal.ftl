<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir='<@liferay.language key="lang.dir" />' lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<#assign
		navBarAdjustments = ""
	/>

	<#if is_signed_in>

		<#assign
			navBarAdjustments = "nav-bar-adjustment"
		/>

	</#if>

	<@liferay_util["include"] page=top_head_include />

</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="container-fluid" id="wrapper">
	<header class="${navBarAdjustments} banner-unscrolled" id="banner" role="banner">
		<div class="row" id="themeBanner">
			<div class="col-md-2 col-sm-2 col-xs-2" id="companyBranding">
				<div class="site-name">
					<a href="localhost:8080/" rel="home" title="Home">${site_name}</a>
				</div>
			</div>
			
			<nav id="login">
				<div class="float-right sign-in">

					<#if !is_signed_in>

						<a class="link-button" data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="signIn" rel="nofollow"><div id="sign_in_text">${sign_in_text}</div></a>

					</#if>

					<a class="float-right theme-nav-bar-toggle" id="menu-dropdown-toggle">&#x2261;</a>
				</div>

				<div class="float-right" id="themeNavBar">

					<#if has_navigation && is_setup_complete>

						<a class="theme-nav-bar-toggle">&#x2261;</a>

						<#include "${full_templates_path}/navigation.ftl" />

					</#if>

				</div>
			</nav>
		</div>
	</header>

	<section id="background">
		<div class="row">
			<div class="jumbotron-picture">
				<div class="background-box">
					<nav id="breadcrumbs">

						<@liferay.breadcrumbs />

					</nav>
				</div>
			</div>
		</div>
	</section>

	<section id="portletSect">

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

	<hr />

	<footer id="footer" role="contentinfo">
		<p class="powered-by text-center">

			<@liferay.language key="powered-by" /> <a href="#" rel="external">${company_name}</a>

		</p>
	</footer>

	<a class="btn-back-to-top" href="#">Back to top</a>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

</body>

</html>