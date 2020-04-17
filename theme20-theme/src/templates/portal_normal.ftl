<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body>
	<@liferay_ui["quick-access"] contentId="#main-content" />

	<@liferay_util["include"] page=body_top_include />

	<@liferay.control_menu />

	<header class="${header_css_class}" id="navigation">
		<div class="container-fluid mt-0 pt-0 px-0" id="wrapper" role="banner">
			<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
				<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" />	
				<#if show_site_name>
					${site_name}
				</#if>
			</a>

			<#if has_navigation>
				<button 
					aria-controls="navigation" 
					aria-expanded="false" 
					class="btn-monospaced ml-auto navbar-toggler" 
					data-target="#theme-navigation" 
					data-toggle="collapse" 
					type="button">
					<span class="navbar-toggler-icon"></span>
				</button>

 				<#include "${full_templates_path}/navigation.ftl" />
			</#if>
		</div>
	</header>
	
	<#include "${full_templates_path}/hero.ftl" />
	
	<div class="container-fluid mt-0 pt-0 px-0" id="wrapper">
		<section id="content">
			<h2 class="hide-accessible" role="heading" aria-level="1">${the_title}</h2>

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
	</div>

	<#if show_footer>
		<#include "${full_templates_path}/footer.ftl" />
	</#if>



<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

</body>

</html>