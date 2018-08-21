<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
	<head>
		<title>${the_title} | ${company_name}</title>
		<meta content="initial-scale=1.0, width=device-width" name="viewport" />

		<@liferay_util["include"] page=top_head_include />
	</head>

	<body class="${css_class} <#if header_fixed>fixed</#if>" >
		<@liferay_ui["quick-access"] contentId="#main-content" />

		<@liferay_util["include"] page=body_top_include />

		<@liferay.control_menu />

		<div id="wrapper">
			<header class="navbar navbar-inverse navbar-alec <#if is_signed_in>buffer</#if>">
				<div class="container-fluid" id="banner" role="banner">
					<div class="navbar-header" id="heading">
						<#if has_navigation>
							<button aria-controls="navigation" aria-expanded="false" class="collapsed navbar-toggle" data-target="#navigationCollapse" data-toggle="collapse" type="button">
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</#if>

						<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
							<img alt="${logo_description}" height="56" src="${site_logo}" />
							<#if show_site_name>
								${site_name}
							</#if>
						</a>
					</div>

					<#if has_navigation>
						<#include "${full_templates_path}/navigation.ftl" />
					</#if>
				</div>
			</header>

			<div class="jumbotron jumbotron-fluid alecs-jumbotron">
				<#if show_page_title>
					<h1>${the_title}</h1>
				</#if>
			</div>

			<main id="content" role="main" data-aos="fade-down">
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
			</main>

			<#include "${full_templates_path}/footer.ftl" />
		</div>

		<@liferay_util["include"] page=body_bottom_include />

		<@liferay_util["include"] page=bottom_include />
	</body>
	<script src="https://cdn.rawgit.com/michalsnik/aos/2.0.4/dist/aos.js"></script>
	<script>
		AOS.init();
		window.addEventListener('DOMContentLoaded', AOS.refresh)
	</script>
</html>
