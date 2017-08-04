<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta charset="utf-8">
	<meta content="IE=edge" http-equiv="X-UA-Compatible">
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet">

	<link href="https://unpkg.com/flickity@2/dist/flickity.min.css" rel="stylesheet" type="text/css">
	<script src="https://unpkg.com/flickity@2/dist/flickity.pkgd.js"></script>
	<script src="https://unpkg.com/scrollreveal/dist/scrollreveal.min.js"></script>

	<@liferay_util["include"] page=top_head_include />

	<style>

		<#if !showPortletBorders>
			.portlet-content.portlet-content-editable {
				border: 0;
			}
		</#if>

		body {
			background: ${backgroundColor1};
			background: -webkit-linear-gradient(to ${backgroundGradientDirection}, ${backgroundColor1}, ${backgroundColor2}) fixed;
			background: linear-gradient(to ${backgroundGradientDirection}, ${backgroundColor1}, ${backgroundColor2}) fixed;
		}
	</style>
</head>

<body class="${css_class}">

	<@liferay_ui["quick-access"] contentId="#main-content" />

	<@liferay_util["include"] page=body_top_include />

	<@liferay.control_menu />

	<div id="wrapper">
		<div class="photography-theme-container">
			<header id="banner" role="banner">

				<#if has_navigation && is_setup_complete>
					<#include "${full_templates_path}/navigation.ftl" />
				</#if>

			</header>
		</div>
	</div>

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

	</section>

	<footer class="photography-theme-container">

		<#if has_navigation>
			<#include "${full_templates_path}/footer.ftl" />
		</#if>

	</footer>

	<@liferay_util["include"] page=body_bottom_include />

	<@liferay_util["include"] page=bottom_include />

</body>
</html>
