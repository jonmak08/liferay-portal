<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key=" lang.dir" />" lang="${w3c_language_id}">

<head>
    <title>${the_title} - ${company_name}</title>

    <meta content="initial-scale=1.0, width=device-width" name="viewport" />

    <#--  pt animation js for header -->
    <script src="/o/kyoung-liferay-theme/js/pt.min.js"></script>

    <@liferay_util["include"] page=top_head_include />

</head>

<body class="${css_class}">

    <@liferay_ui["quick-access"] contentId="#main-content" />

    <@liferay_util["include"] page=body_top_include />

    <@liferay.control_menu />

    <#-- Start Header -->
    <#if show_header>
        <header role="banner">
            <div class="flex height-fix" id="home">
                <div class="canvas" id="pt" ></div>
				<#-- Start Logo -->
                <div class="flex">
                    <div class="mu-logo-area">
                        <a class="mu-logo" href="#" alt="logo"><img class="logo" alt="img logo ${logo_description}" src="${site_logo}"></a>
                    </div>
                    <#assign preferences=freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId":
                        "barebone" , "destination" : "/search" }) />
                </div>
                <#-- End Logo -->
            </div>
        </header>
    <#-- End Header Content -->

        <#-- Start Menu -->
        <button class="mu-menu-btn">
        </button>
        <div class="mu-menu-full-overlay">
            <div class="mu-menu-full-overlay-inner">
				<a class="mu-menu-close-btn"></a>
                <nav class="mu-menu" role="navigation">
                    <ul>
						<li>
							<#if show_site_name>
								<div class="company-name">Welcome To ${site_name}</div>
							</#if>
						</li>
						<li>
							<div class="user-personal-bar" id="center">
								<@liferay.user_personal_bar />
							</div>
						</li>
						<li>
							<div id="center">
								<#include "${full_templates_path}/navigation.ftl" />
							</div>
						</li>
						<#--  Search Bar  -->
						<#if show_header_search>
							<li>
								<div id="center">
									<div class="navbar-form" role="search">
										<@liferay.search_bar default_preferences="${preferences}" />
									</div>
								</div>
							</li>
						</#if>
					</ul>
				</nav>
            </div>
        </div>
        <#-- End Menu -->
        </header>
    </#if>

	<#--  Main Content  -->
    <div class="main-container">
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
    </div>

    <#-- Start footer -->
    <#if show_footer>
        <footer id="mu-footer" role="contentinfo">
            <div class="container">
                <div class="mu-footer-area">
                        <@liferay.language key="powered-by" />
                        <a href="http://www.liferay.com" rel="external">Liferay &copy;</a>
                </div>
            </div>
        </footer>
    </#if>
    </div>
    <#-- End footer -->

    <#-- Custom js -->
    <script src="/o/kyoung-liferay-theme/js/custom.js" type="text/javascript"></script>

    <#-- Header Canvas -->
    <script src="/o/kyoung-liferay-theme/js/canvas.js" type="text/javascript"></script>

    <@liferay_util["include"] page=body_bottom_include />

    <@liferay_util["include"] page=bottom_include />

</body>

</html>