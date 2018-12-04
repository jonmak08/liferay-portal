<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />

	<link href="https://fonts.googleapis.com/css?family=Libre+Franklin" rel="stylesheet">

	<link href="https://fonts.googleapis.com/css?family=Libre+Franklin:400,800" rel="stylesheet">
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="container-fluid" id="wrapper">
	<header id="banner" role="banner">
		<div class="custom-header">
			<div class="header-media">
				<img alt="Header Media Picture" class="simplicity-media" height="1200" scale="0" src="${themeDisplay.getPathThemeImages()}/simplicity.jpg" width="2000" />
			</div>

			<div class="site-branding" style="margin-bottom: 72px;">
				<div class="wrap">
					<h1 class="site-title">
						<#if show_site_name>
							<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
								${site_name}
							</span>
						</#if>
					</h1>
					<p class="site-description">Simplicity Theme 2018</p>
				</div>
			</div>
		</div>

		<#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if has_navigation && is_setup_complete>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
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
		<div class="footer-wrap">
			<nav class="social-media-navigation">
				<ul class="social-media-links">
					<li id="menu-item-facebook" class="menu-item menu-item-facebook">
						<a href="http://www.facebook.com" class="social-facebook">
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-add-column lexicon-icon-facebook" >
								<use xlink:href="${themeDisplay.getPathThemeImages()}/clay/icons.svg#social-facebook" />
							</svg>
						</a>
					</li>
					<li id="menu-item-twiiter" class="menu-item menu-item-twiiter">
						<a href="http://www.twitter.com" class="social-twitter">
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-add-column lexicon-icon-twitter">
								<use xlink:href="${themeDisplay.getPathThemeImages()}/clay/icons.svg#twitter" />
							</svg>
						</a>
					</li>
					<li id="menu-item-linkedin" class="menu-item menu-item-linkedin">
						<a href="http://www.linkedin.com" class="social-linkedin">
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-add-column lexicon-icon-linkedin">
								<use xlink:href="${themeDisplay.getPathThemeImages()}/clay/icons.svg#social-linkedin" />
							</svg>
						</a>
					</li>
				</ul>
			</nav>
			<div class="site-info">
				<a href="http://www.liferay.com">Blog at Liferay</a>
			</div>
		</div>
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>