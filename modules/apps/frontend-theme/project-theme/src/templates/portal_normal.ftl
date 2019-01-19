<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

<<<<<<< HEAD
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
	
=======
>>>>>>> c11802cafe84... add project theme feature 1
	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<<<<<<< HEAD
<#--  WRAPPER  -->
	<div id="wrapper">
		<#if show_header>
			<header id="banner">

				<div class="navbar navbar-classic navbar-top py-4">

						<div class="contact">
								<#if show_phone>
									<li class="contact-info"> <i class="fas fa-phone-square"></i> </a>${phone_url}</li>
								</#if>
								<#if show_email>
									<li class="contact-info"> <i class="fas fa-envelope-square"></i> </a>${email_url}</li>
								</#if>	
						</div>

						<div class="social">
							<ul>
								<#if show_instagram>
									<li class="social-icons"> <a class="fab fa-instagram" href ="https://${instagram_url}"> </a></li>
								</#if>
								<#if show_instagram>
									<li class="social-icons"> <a class="fab fa-facebook" href ="https://${facebook_url}"> </a></li>
								</#if>	
								<#if show_instagram>
									<li class="social-icons"> <a class="fab fa-twitter" href ="https://${twitter_url}"> </a></li>
								</#if>	
								<#if show_instagram>
									<li class="social-icons"> <a class="fab fa-google-plus-g" href ="https://${googleplus_url}"> </a></li>
								</#if>	
								<#if show_instagram>
									<li class="social-icons"> <a class="fab fa-linkedin-in" href ="https://${linkedin_url}"> </a></li>
								</#if>	
								<#if show_instagram>
									<li class="social-icons"> <a class="fab fa-youtube" href ="https://${youtube_url}"> </a></li>
								</#if>	
								<#if show_instagram>
									<li class="social-icons"> <a class="fab fa-dribbble" href ="https://${dribbble_url}"> </a></li>
								</#if>	
								<#if show_instagram>
									<li class="social-icons"> <a class="fab fa-github" href ="https://${github_url}"> </a></li>
								</#if>		
							<ul>
						</div>

					<div class="container user-personal-bar">
						<div class="align-items-center autofit-row">

							<#assign 
								preferences = freeMarkerPortletPreferences.getPreferences(
									{"portletSetupPortletDecoratorId": "barebone", 
									"destination": "/search"}
									)
							/>

						</div>
					</div>
				</div>

				<#--  SITE LOGO AND TITLE  -->
				<div class="navlogo-wrapper">

					<a class="${logo_css_class} align-items-center d-md-inline-flex d-sm-none d-none logo-md" href="${site_default_url}" title="<@liferay.language_format arguments="" key="go-to-x" />">
						<img alt="${logo_description}" class="mr-2" height="" src="${site_logo}" />
=======
<div class="pt-0" id="wrapper">
	<#if show_header>
		<header id="banner">
			<div class="navbar navbar-classic navbar-top py-3">
				<div class="container user-personal-bar">
					<div class="align-items-center autofit-row">
						<a class="${logo_css_class} align-items-center d-md-inline-flex d-sm-none d-none logo-md" href="${site_default_url}" title="<@liferay.language_format arguments="" key="go-to-x" />">
							<img alt="${logo_description}" class="mr-2" height="56" src="${site_logo}" />

							<#if show_site_name>
								<h1 class="font-weight-bold h2 mb-0 text-dark">${site_name}</h1>
							</#if>
						</a>

						<#assign preferences = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "barebone", "destination": "/search"}) />

						<div class="autofit-col autofit-col-expand">
							<#if show_header_search>
								<div class="justify-content-md-end mr-4 navbar-form" role="search">
									<@liferay.search_bar default_preferences="${preferences}" />
								</div>
							</#if>
						</div>

						<div class="autofit-col">
							<@liferay.user_personal_bar />
						</div>
					</div>
				</div>
			</div>

			<div class="mb-4 navbar navbar-classic navbar-expand-md navbar-light pb-3">
				<div class="container">
					<a class="${logo_css_class} align-items-center d-inline-flex d-md-none logo-xs" href="${site_default_url}" rel="nofollow">
						<img alt="${logo_description}" class="mr-2" height="56" src="${site_logo}" />
>>>>>>> c11802cafe84... add project theme feature 1

						<#if show_site_name>
							<h1 class="font-weight-bold h2 mb-0 text-dark">${site_name}</h1>
						</#if>
					</a>

<<<<<<< HEAD
					<#--  CLOSE SITE LOGO AND TITLE  -->

					<#--  NAVIGATION  -->
					<div id="portlet-body" class="navbar navbar-expand-lg navbar-light">
						<div class="container">

							<a class="${logo_css_class} d-inline-flex d-md-none logo-xs" href="${site_default_url}" rel="nofollow">
								<img alt="${logo_description}" class="mr-2" height="" src="${site_logo}" />

								<#if show_site_name>
									<h1 class="font-weight-bold h2 mb-0 text-dark">${site_name}</h1>
								</#if>
							</a>

							<#include "${full_templates_path}/navigation.ftl" />
								
								<#--  SEARCH  -->
								<#if show_header_search>

									<div>

										<i class="search">
												<svg aria-hidden="true" class="lexicon-icon lexicon-icon-add-column lexicon-icon-search">
													<use xlink:href="${themeDisplay.getPathThemeImages()}/clay/icons.svg#search" />
												</svg>
										</i> 

										<div class="search-box">
												<@liferay.search_bar default_preferences="${preferences}" />
										</div>

									</div>

								</#if>
											
								<#--  CLOSE SEARCH  -->

							<@liferay.user_personal_bar />

						</div>
					</div>

				</div>
				<#--  CLOSE NAVIGATION -->

			</header>
		</#if>
=======
					<#include "${full_templates_path}/navigation.ftl" />
				</div>
			</div>
		</header>
	</#if>
>>>>>>> c11802cafe84... add project theme feature 1

	<section class="${portal_content_css_class}" id="content">
		<h1 class="sr-only">${the_title}</h1>

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

<<<<<<< HEAD
<#--  FOOTER  -->
=======
>>>>>>> c11802cafe84... add project theme feature 1
	<#if show_footer>
		<footer id="footer" role="contentinfo">
			<div class="container">
				<div class="row">
<<<<<<< HEAD

					<div class="col-xs-12 col-md-6 col-sm-6 ">
						<p>Copyright &copy; David Lui 2019.</p>
					</div>

					<div class="col-xs-12 col-md-6 col-sm-6 fr footer-links">
=======
					<div class="col-md-12 text-center text-md-left">
>>>>>>> c11802cafe84... add project theme feature 1
						<@liferay.language key="powered-by" />

						<a class="text-white" href="http://www.liferay.com" rel="external">Liferay</a>
					</div>
<<<<<<< HEAD

=======
>>>>>>> c11802cafe84... add project theme feature 1
				</div>
			</div>
		</footer>
	</#if>
<<<<<<< HEAD
	<#--  CLOSE FOOTER  -->

</div>
<#--  CLOSE WRAPPER  -->

=======
</div>
>>>>>>> c11802cafe84... add project theme feature 1

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

<<<<<<< HEAD
</html> 
=======
</html>
>>>>>>> c11802cafe84... add project theme feature 1
