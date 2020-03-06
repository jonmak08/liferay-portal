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
	<div class="row">
		<header id="navigation" role="navigation" class="col-2">
			<div class="container-fluid">
				<div class="row">
					<section id="main_logo" class="main_logo col-12">
						<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
							<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
						</a>
					</section>

				<#if !is_signed_in>
				<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
				</#if>

				<#if has_navigation && is_setup_complete>
				<#include "${full_templates_path}/navigation.ftl" />
				</#if>

					<section id="social" class="social col-12">
						<div class="social__wrapper">
						</div>
					<section/>
				</div>
			</div>
		</header>
	
	<#include "${full_templates_path}/content.ftl" />

	<!--***** CONTENT *****-->
	
	</div>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->

<!-- endinject -->

</body>

</html>