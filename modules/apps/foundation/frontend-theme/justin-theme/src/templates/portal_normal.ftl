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
	<header class="banner higher-position nav-down" id="banner" role="banner">
			<div class="row">

				<div clas="col-xs-2 pull-right">

					<#if !is_signed_in>
						<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
					</#if>

				</div>

			</div>
			<div class="row">
				<div class="navbar-header" id="heading">

					<#if show_site_name>
						<a href="${site_default_url}" class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
								${site_name}
						</a>
						
						<p class="hidden-sm hidden-xs" title="<@liferay.language_format arguments="${sub_heading}" key="go-to-x" />">
							${sub_heading}
						</p>
					</#if>

					<button  aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" class="align-bottom collapsed navbar-toggle"  data-target="#navbarSupportedContent" data-toggle="collapse" id="navbarButton" type="button">
						<span id="btnChild">
							<i class="icon-th-list"></i>
						</span>
					</button>
					
				</div>

				<#include "${full_templates_path}/navigation.ftl" />

			</div>
	</header>

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
			<div class="row">
				<p class="col-xs-6 powered-by">
					<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
				</p>
				<div class="col-xs-6 social-media-wrapper">
					<#include "${full_templates_path}/social.ftl" />
				</div>
			</div>
		</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />
<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

</body>

</html>