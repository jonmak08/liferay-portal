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
	
	<#if font_awesome_url!="">
	<script src="${font_awesome_url}"></script>
	</#if>

	<meta name="viewport" content="width=device-width, initial-scale=1,0"/>


	<script type="text/javascript">
	$(function() {
	    var winW = $(window).width();
	    console.log("*** width: "+winW);
	    var nav = $('.horizontal-nav');
	    var $w = $(window);
	    if (winW > 875) {
		    // Stick the .horizontal-nav to the top of the window
		    var navHomeY = nav.offset().top;
		    console.log("navHomeY starts at " + navHomeY);
		    var isFixed = false;
		    //var $w = $(window);
		    $w.scroll(function() {
	        	var scrollTop = $w.scrollTop();
			console.log("scrollTop = " + scrollTop);
	        	var shouldBeFixed = scrollTop > 0; //navHomeY;
	        	if (shouldBeFixed && !isFixed) {
		            nav.css({
	        	        position: 'fixed',
				'background-color': 'white',
		                left: nav.offset().left,
        		        width: nav.width()
	        	    });
			    if ($('.control-menu').length) {
				nav.css({top: 65});
				console.log("*** ,controlmenu.length, so horizontal-nav top = 65");
			    }
			    else {
				nav.css({top: 0});
				console.log("*** ,!controlmenu.length, so horizontal-nav top = 0");
			    }
        		    isFixed = true;
		        }
        		else if (!shouldBeFixed && isFixed)
	        	{
	        	    nav.css({
		                position: 'absolute',
				'background-color': 'transparent',
				top: 0
        		    });
		            isFixed = false;
	        	}
		    });
	    }
	});

	function openNav() {
		console.log("nope");		
	}

	function closeNav() {
		console.log("nope");	
	}

	</script>


</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<!--
<div class="container-fluid parallaximage">
<h1>hi</h1>
</div>
//-->

<div class="container-fluid" id="wrapper" style="padding-left: 0; padding-right: 0;">
	<!-- Original for backup
	<header id="banner" role="banner">
		<div id="heading">
			<h1 class="site-title">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>
			</h1>
		</div>

		<#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if has_navigation && is_setup_complete>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
	</header>
	//-->

	<header id="banner" role="banner">
		<div class="horizontal-nav flexbox">
			<div class="top-menu-text">${site_name}</div>
			<div style="float: right; padding: 20px; font-size: 20px;"><a href="javascript:openNav()"><i class="fa fa-bars" aria-hidden="true" style="color: black;"></i></a></div>
		</div>

		<div class="parallaximagespacer" align="center" width="100%">
			<div style="background-image: url('${theme_settings["bannerimage"]}');" class="parallaximage">
				<div style="display: block; width: 100%; height: 100%; left: 0; top: 0; justify-content: center; align-items: center;">
					<div style="height: 10%;"></div>
					<div class="header-title">${header_title}</div>
					<div class="header-subtitle">${header_subtitle}</div>
					<div><a href="#myContent" class="mouseOverDarkenPic"><div class="bluebutton large-button">Continue</div></a></div>
				</div>
			</div>
		</div>
	</header>
<!--
	<section id="oldheader">
		<div class="parallaximagespacer">
		</div>
		<div>
			<h1 class="site-title">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>
			</h1>
		</div>

		<#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if has_navigation && is_setup_complete>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
	</section>
//-->

	<section id="content">
<!-- Take out to match the original site
		<div>
			<h1 class="site-title">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>
			</h1>
		</div>
//-->
		<a name="myContent">&nbsp;</a>
		<#if !is_signed_in>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if has_navigation && is_setup_complete>
			<div class="nav-text" align="center">
				<#include "${full_templates_path}/navigation.ftl" />
			</div>
		</#if>


		<h1 class="hide-accessible">${the_title}</h1>
		<!--
		<nav id="breadcrumbs">
			<@liferay.breadcrumbs />
		</nav>
		//-->
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
		<p>${social_media}</p>
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
