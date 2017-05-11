<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir='<@liferay.language key="lang.dir" />' lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<#if is_signed_in>
		<style>
			#jw-banner {
				margin-top: -30px !important;
			}

			#banner {
				height: 80px !important;
			}

			#jw-nav-bar {
				float: right;
				top: 65px !important;
			}

			.jumbotron-picture {
				top: -25px !important;
			}
		</style>
	</#if>

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="container-fluid" id="wrapper">
	<header id="banner" role="banner">
		<div class="row" id="jw-banner">
			<div class="col-md-2 col-sm-2 col-xs-2 jw-nav-bar jw-nav-left" id="company-branding">
				<div class="site-name">
					<a href="localhost:8080/" rel="home" title="Home">${site_name}</a>
				</div>
			</div>
			<nav id="login">
				<div class="float-right">
					<#if !is_signed_in>
						<a class="link-button" data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow"><div id="sign_in_text">${sign_in_text}</div></a>
					</#if>
					<a class="float-right jw-nav-bar-toggle">&#x2261;</a>
				</div>

				<!-- Dynamic navigation menu -->
				<div class="float-right" id="jw-nav-bar">

					<#if has_navigation && is_setup_complete>
						<a class="jw-nav-bar-toggle">&#x2261;</a>
						<#include "${full_templates_path}/navigation.ftl" />
					</#if>

				</div>
				<!-- /Dynamic navigation menu -->

			</nav>
		</div>
	</header>

	<section id="hero">
		<div class="row">
			<div class="jumbotron-picture">
				<div class="background-box">
					<nav id="breadcrumbs">
						<@liferay.breadcrumbs />
					</nav>
				</div>
			</div>
		</div>
	</section>

	<section id="content">
		<div class="theme-container">
			<h1 class="hide-accessible">${the_title}</h1>
			<div class="row">
				<div class="col-md-12 text-center">
					<h1>This is my <span style="color: #DAB485">Lapurd</span> theme</h1>
					<p>
					Donec consectetur mauris justo, vel scelerisque nunc dictum sed. Ut sodales arcu sed bibendum egestas. Proin lorem lacus, venenatis id enim at, sagittis eleifend odio. Ut porta blandit metus ac molestie. Ut lacinia nibh vitae felis porttitor, a maximus tortor sollicitudin. Aliquam sit amet sapien ac tellus efficitur vestibulum. Nulla elementum porta ante et facilisis.
					</p>
				</div>
			</div>
			<hr/>
			<div class="row">
				<div class="row">
					<div class="col-md-6 grid-left">
						<h3>Top left Title</h3>
						<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ac sapien auctor mi imperdiet faucibus quis eu risus. Nulla cursus ligula varius euismod pharetra. Aenean a vulputate enim, quis hendrerit metus. Sed ut sem non eros tincidunt congue viverra id diam.
						</p>
					</div>
					<div class="col-md-6 grid-right">
						<h3>Top right Title</h3>
						<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ac sapien auctor mi imperdiet faucibus quis eu risus. Nulla cursus ligula varius euismod pharetra. Aenean a vulputate enim, quis hendrerit metus. Sed ut sem non eros tincidunt congue viverra id diam. Aenean sed nibh nisi. Nulla vel ligula a ante vehicula ornare.
						</p>
					</div>
				</div>

				<div class="row row2">
					<div class="col-md-6 grid-left">
						<h3>Bottom left Title</h3>
						<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ac sapien auctor mi imperdiet faucibus quis eu risus. Nulla cursus ligula varius euismod pharetra. Aenean a vulputate enim, quis hendrerit metus. Sed ut sem non eros tincidunt congue viverra id diam. Aenean sed nibh nisi.
						</p>
					</div>
					<div class="col-md-6 grid-right">
						<h3>Bottom right Title</h3>
						<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ac sapien auctor mi imperdiet faucibus quis eu risus. Nulla cursus ligula varius euismod pharetra.
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="bg-dark" id="highlights">
		<div class="row">
			<div class="theme-container">
				<div class="row">
					<div class="content-desc">
						<a class="link-fade text-caps">This is a Title, some awesome title.</a>
						<p>This is some pretty cool sub text. should be in white.</p>
						<a class="link-button" href="">This is a link</a>
					</div>
					<div class="row">
						<div class="gallery">
							<ul>
								<li>
									<a alt="Gallery picture" href="">Zion National Park</a>
									<img alt="Zion National Park" class="gall-image" id="gall-img-1" src="../images/gallery1.jpg"/>
								</li>
								<li>
									<a alt="Gallery picture" href="">Badminton</a>
									<img alt="Badminton" class="gall-image" id="gall-img-2" src="../images/gallery2.jpg"/>
								</li>
								<li>
									<a alt="Gallery picture" href="">Snowboarding</a>
									<img alt="Snowboarding" class="gall-image" id="gall-img-3" src="../images/gallery3.jpg"/>
								</li>
								<li>
									<a alt="Gallery picture" href="">Rock Climbing</a>
									<img alt="Rock Climbing" class="gall-image" id="gall-img-4" src="../images/gallery4.jpg"/>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section id="portletSect">
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

	<!--======================================================== footer ========================================================-->
	<hr/>
	<footer id="footer" role="contentinfo">
		<p class="powered-by text-center">
			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
		</p>
	</footer>

</div>

<@liferay_util["include"] page=body_bottom_include />
<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->
	<script src="http://code.jquery.com/jquery-1.7.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#jw-nav-bar").css("right", "-250px");
			$("a.jw-nav-bar-toggle").click(function() {
				if (!$("#jw-nav-bar").hasClass("menuOpen")) {
					$("#jw-nav-bar").addClass("menuOpen");
					$("#jw-nav-bar").css("right", "0");
				} else {
					$("#jw-nav-bar").removeClass("menuOpen");
					$("#jw-nav-bar").css("right", "-250px");
					
				}
			});
			$("button.child-menu-toggle").click(function() {	
				if ($("ul.child-menu").hasClass("menuOpen")) {
					$("ul.child-menu").removeClass("menuOpen");
					$("ul.child-menu").removeClass("child-menu-open");
					$(this).removeClass("child-menu-toggle-open");
				} else {
					$("ul.child-menu").addClass("menuOpen");
					$("ul.child-menu").addClass("child-menu-open");
					$(this).addClass("child-menu-toggle-open");
				}

			});
		});
	</script>
</body>

</html>	