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

<div class="container-fluid" id="wrapper">
	<header id="banner" role="banner">

		<#if has_navigation && is_setup_complete>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>

		<div class="carousel slide" id="carousel-header" data-ride="carousel">
			<div class="carousel-inner" role="listbox">
				<div class="active item">

					<#if !show_custom_header_image>
						<div class="page-header header-filter" style="background-image: url('${default_header_image}');">
					</#if>

					<#if show_custom_header_image>
						<div class="page-header header-filter" style="background-image: url('${custom_header_image}');">
					</#if>
							<div class="container">
								<div class="row">
									<div class="col-md-8 col-md-offset-2 text-center">
										<h1 class="site-title">

											<#if show_page_headline>
												${page_headline}
											</#if>

											<#if !show_page_headline>
												${the_title}
											</#if>

										</h1>
										<h2>

											<#if show_page_tagline>
												${page_tagline}
											</#if>

										</h2>
									</div>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="content-wrapper">
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

		<footer id="footer" role="contentinfo">
			<p class="powered-by">
				<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
			</p>
		</footer>
	</div>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>