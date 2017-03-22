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
	<#if !is_signed_in>
		<a class="sign-in" data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
	</#if>

	<#if is_signed_in && has_navigation && is_setup_complete>
		<#include "${full_templates_path}/navigation.ftl" />
	</#if>


	<header class="header-banner" id="banner" role="banner">
		<div class="beginning" id="heading">
			<h1 class="site-title">
				<#if is_signed_in && show_site_name>
					<span class="site-title site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${the_title}
					</span>
				</#if>
			</h1>
		</div>
	</header>

	<section class="body-content" id="content">
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
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>

