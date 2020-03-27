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

<header class="theme-header">
	<div class="container-fluid" id="banner" role="banner">
		<#if has_navigation>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
	</div>
</header>

<div class="container-fluid mt-0 pt-0 px-0" id="wrapper">
	<section id="content">
		<div class="initial-background">
			<#include "${full_templates_path}/welcome_page.ftl" />
		</div>

		<div class="default-background">
			<h2 class="hide-accessible" role="heading" aria-level="1">
				${the_title}
			</h2>
		
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

	</section>

	<#if show_map>
		<#include "${full_templates_path}/map.ftl" />
	</#if>

	<#if show_footer>
		<#include "${full_templates_path}/footer.ftl" />
	</#if>
</div>

<@liferay_util["include"] page=body_bottom_include />
<@liferay_util["include"] page=bottom_include />
</body>

</html>