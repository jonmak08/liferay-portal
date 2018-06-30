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

<div id="wrapper">
	<header class="container-fluid-1280" id="banner" role="banner">
		<div class="row">
			<div class="navbar-header" id="heading">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" src="${site_logo}"/>
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>

				<#if is_setup_complete>
					<button aria-controls="navigation" aria-expanded="false" class="collapsed navbar-toggle" data-target="#navigationCollapse" data-toggle="collapse" type="button">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>

					<div class="pull-right user-personal-bar">
						<@liferay.user_personal_bar />
					</div>
				</#if>
			</div>

			<#--  <#include "${full_templates_path}/navigation.ftl" />  -->
			<#include "${full_templates_path}/bq-multi-test.ftl" />
		</div>
	</header>

	<section class="container-fluid-1280" id="content">
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

	<footer class="container-fluid-1280" id="footer" role="contentinfo">
		<div class="row">
			<div class="footer-container">
				<div class="footer-content">
					<ul>
						<a href="https://facebook.com/nyxl">
							<li>
								<svg aria-hidden="true" class="facebook-svg" role="img" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" viewBox="-204.5 1 216 216" xml:space="preserve">
									<path d="M-63.7,45.8v20.8H-76c-4.5,0-7.6,0.9-9.1,2.8s-2.4,4.7-2.4,8.5v14.9h23l-3.1,23.3h-20v59.7h-24.1v-59.7h-20.1V92.8h20.1
	V75.6c0-9.8,2.7-17.3,8.2-22.7s12.7-8.1,21.8-8.1C-73.9,44.9-67.9,45.2-63.7,45.8L-63.7,45.8z">
									</path>
								</svg>
							</li>
						</a>
					</ul>
				</div>

				<div class="footer-content">
					<ul>
						<a href="https://instagram.com/nyxl">
							<li>
								<svg aria-hidden="true" class="instagram-svg" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 64 64" width="100%" height="100%">
									<path id="camera" d="M13,32c0-2.6.1-5.1.1-7.7A12.4,12.4,0,0,1,14.8,18a9.5,9.5,0,0,1,6.7-4.6,33.8,33.8,0,0,1,5.2-.4H38a18.3,18.3,0,0,1,5.9.7,9.5,9.5,0,0,1,6.7,7.7,30.9,30.9,0,0,1,.4,5.3c.1,3.8,0,7.5,0,11.3a18.5,18.5,0,0,1-.7,5.9,9.5,9.5,0,0,1-7.7,6.7,30.4,30.4,0,0,1-5.3.4H26a18.3,18.3,0,0,1-5.9-.7,9.5,9.5,0,0,1-6.7-7.7,35.9,35.9,0,0,1-.4-5.3c-.1-1.8,0-3.5,0-5.3Zm34.7-.3h-.1c0-1.5,0-3,0-4.5a41.6,41.6,0,0,0-.3-4.8,6.2,6.2,0,0,0-4.5-5.3,11.2,11.2,0,0,0-2.9-.5H29.4c-2.2,0-4.4.1-6.5.3a6.3,6.3,0,0,0-6,5.4,31.6,31.6,0,0,0-.4,5.2c-.1,3.1,0,6.3,0,9.4,0,1.5.1,3,.2,4.4a6.4,6.4,0,0,0,5.4,6,30.1,30.1,0,0,0,5.1.4h9.6l4.3-.2a7,7,0,0,0,3.7-1.4,7,7,0,0,0,2.6-5.5C47.6,37.5,47.6,34.6,47.7,31.7Z"></path>
									<path id="lens" d="M32,41.8a9.8,9.8,0,1,1,9.8-9.7A9.7,9.7,0,0,1,32,41.8Zm0-16.1A6.3,6.3,0,1,0,38.3,32,6.4,6.4,0,0,0,32,25.7Z"></path>
									<path id="viewfinder" d="M44.4,21.9a2.3,2.3,0,0,1-2.3,2.3,2.3,2.3,0,0,1-2.3-2.3,2.3,2.3,0,0,1,2.3-2.3A2.2,2.2,0,0,1,44.4,21.9Z"></path>
								</svg>
							</li>
						</a>
					</ul>
				</div>

				<div class="footer-content">
					<ul>
						<a href="https://twitter.com/nyxl">
							<li>
								<svg aria-hidden="true" class="twitter-svg" role="img" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" viewBox="-196.5 1 216 216" xml:space="preserve">
									<path d="M-10.8,60.8c-4.4,6.5-9.8,12-16,16.5c0.1,0.9,0.1,2.3,0.1,4.2c0,8.6-1.3,17.1-3.8,25.7c-2.5,8.5-6.3,16.7-11.4,24.6
									s-11.2,14.8-18.2,20.8c-7.1,6-15.6,10.8-25.5,14.4c-9.9,3.6-20.6,5.4-31.9,5.4c-17.9,0-34.2-4.8-49-14.3c2.3,0.3,4.9,0.4,7.7,0.4
									c14.8,0,28-4.5,39.6-13.6c-6.9-0.1-13.1-2.3-18.6-6.4c-5.5-4.1-9.2-9.4-11.3-15.8c2.2,0.3,4.2,0.5,6,0.5c2.8,0,5.6-0.4,8.4-1.1
									c-7.4-1.5-13.5-5.2-18.3-11s-7.3-12.6-7.3-20.3v-0.4c4.5,2.5,9.3,3.9,14.4,4.1c-4.4-2.9-7.8-6.7-10.4-11.4s-3.9-9.8-3.9-15.2
									c0-5.8,1.4-11.2,4.3-16.1c8,9.8,17.7,17.7,29.1,23.6c11.4,5.9,23.7,9.2,36.7,9.8c-0.5-2.5-0.8-4.9-0.8-7.3c0-8.8,3.1-16.4,9.3-22.6
									s13.8-9.3,22.6-9.3c9.2,0,17,3.4,23.3,10.1c7.2-1.4,13.9-4,20.3-7.7c-2.4,7.6-7.1,13.4-14,17.6C-23.1,65-16.9,63.4-10.8,60.8
									L-10.8,60.8z"></path>
								</svg>
							</li>
						</a>
					</ul>
				</div>

				<div class="footer-content">
					<ul>
						<a href="https://youtube.com/c/nyxl">
							<li>
								<svg aria-hidden="true" class="youtube-svg" role="img" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" viewBox="0 0 64 64" width="100%" height="100%">
									<path d="M55.9,32a54.4,54.4,0,0,0-.5-8.3c-.6-4.3-1.9-5.9-2.7-6.8a5.9,5.9,0,0,0-2.9-1.2s-5.5-.4-16-.4H30.1c-10.5,0-16,.4-16,.4a5.9,5.9,0,0,0-2.9,1.2c-.8.9-2.1,2.5-2.7,6.8A54.5,54.5,0,0,0,8.1,32h0a54.5,54.5,0,0,0,.5,8.3c.6,4.3,1.9,5.9,2.7,6.8a5.9,5.9,0,0,0,2.9,1.2s5.5.4,16,.4h3.7c10.5,0,16-.4,16-.4a5.9,5.9,0,0,0,2.9-1.2c.8-.9,2.1-2.5,2.7-6.8a54.4,54.4,0,0,0,.5-8.3ZM27.1,40.9V23.2L40.8,32Z"></path>
								</svg>
							</li>
						</a>
					</ul>
				</div>
			</div>
		</div>
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>