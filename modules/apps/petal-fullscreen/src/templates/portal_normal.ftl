<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />

	<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Inconsolata" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Playfair+Display+SC" rel="stylesheet">

	<script src="${font_awesome_url}"></script>

	<style>
		.header-title-color {
			color: ${header_title_color};
		}

		.header-subtitle-color {
			color: ${header_subtitle_color};
		}

		.parallax-image {
			background-image: url('${theme_settings["banner-image"]}');
		}
	</style>
</head>

<body class="${css_class}">

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="container-fluid unpadded" id="wrapper">
	<header id="banner" role="banner">
		<div class="absolute flexbox horizontal-nav without-offset">
			<div class="top-menu-text">${site_name}</div>
		</div>

		<#assign
			parallax_image_spacer_size = 300
			parallax_image_header_display = "none"
		/>
		<#if layout.isFirstParent() >
			<#assign parallax_image_spacer_size = 500>
			<#assign parallax_image_header_display = "block">
		</#if>

		<div class="parallax-image-spacer" style="height: ${parallax_image_spacer_size}px;">
			<div class="parallax-image">
				<div class="header-container" style="display: ${parallax_image_header_display};">
					<div class="vertical-spacer"></div>

					<div class="header-title header-title-color">${header_title}</div>

					<div class="header-subtitle header-subtitle-color">${header_subtitle}</div>

					<div class="full-width-container">
						<a class="mouse-over-darken-pic" href="#myContent">
							<div class="colored-button large-button">
								<@liferay.language key="continue-button" />
							</div>
						</a>
					</div>
				</div>
			</div>
		</div>
	</header>

	<section id="content">
		<@liferay_ui["quick-access"] contentId="#main-content" />
		<a name="myContent"></a>

		<#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if has_navigation && is_setup_complete>
			<div class="nav-text">
				<#include "${full_templates_path}/navigation.ftl" />
			</div>
		</#if>

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
		<p>
			<#if theme_settings["social-media-url-0"] != "" && theme_settings["social-media-icon-0"] != "">
					<a href="${theme_settings['social-media-url-0']}" target="_blank"><span class="fa ${theme_settings['social-media-icon-0']} spaced"></span></a>
			</#if>

			<#if theme_settings["social-media-url-1"] != "" && theme_settings["social-media-icon-1"] != "">
					&nbsp;

					<a href="${theme_settings['social-media-url-1']}" target="_blank"><span class="fa ${theme_settings['social-media-icon-1']} spaced"></span></a>
			</#if>

			<#if theme_settings["social-media-url-2"] != "" && theme_settings["social-media-icon-2"] != "">
					&nbsp;

					<a href="${theme_settings['social-media-url-2']}" target="_blank"><span class="fa ${theme_settings['social-media-icon-2']} spaced"></span></a>
			</#if>

			<#if theme_settings["social-media-url-3"] != "" && theme_settings["social-media-icon-3"] != "">
					&nbsp;

					<a href="${theme_settings['social-media-url-3']}" target="_blank"><span class="fa ${theme_settings['social-media-icon-3']} spaced"></span></a>
			</#if>

			<#if theme_settings["social-media-url-4"] != "" && theme_settings["social-media-icon-4"] != "">
					&nbsp;

					<a href="${theme_settings['social-media-url-4']}" target="_blank"><span class="fa ${theme_settings['social-media-icon-4']} spaced"></span></a>
			</#if>
		</p>

		<p>${copyright} • ${physical_address} • ${telephone}</p>

		<#if show_powered_by >
			<p class="powered-by">
				<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
			</p>
		</#if>
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

</body>

</html>
