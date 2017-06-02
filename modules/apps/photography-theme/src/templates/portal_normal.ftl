<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">

	<link rel="stylesheet" type="text/css" href="https://unpkg.com/flickity@2/dist/flickity.min.css">
	<script src="https://unpkg.com/flickity@2/dist/flickity.pkgd.js"></script>

	<@liferay_util["include"] page=top_head_include />

</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

	<div id="wrapper">
		<div class="photography-theme-ctn">
			<header id="banner" role="banner">

				<#if has_navigation && is_setup_complete>
					<#include "${full_templates_path}/navigation.ftl" />
				</#if>

				<div class="carousel">
					<div id="carousel1" class="carousel-cell"></div>
					<div id="carousel2" class="carousel-cell"></div>
					<div id="carousel3" class="carousel-cell"></div>
					<div id="carousel4" class="carousel-cell"></div>
				</div>

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

	<footer class="photography-theme-ctn">

		<#if has_navigation>
			<#include "${full_templates_path}/footer_navigation.ftl" />
		</#if>
		
	</footer>	

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

<script type="text/javascript">

</script>


</body>

</html>