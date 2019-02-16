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
            <div class="flex height-fix home">
                <div class="canvas" id="pt" ></div>
				<#-- Start Logo -->
                <div class="flex">
                    <div class="logo-wrapper">
                        <a class="logo-container" alt="logo"><img class="logo" alt="${logo_description}" src="${site_logo}" /></a>
                    </div>

                    <#assign
                        preferences = freeMarkerPortletPreferences.getPreferences(
                            {
                                "portletSetupPortletDecoratorId": "barebone",
                                "destination" : "/search"
                            }
                        )
                    />

                </div>
                <#-- End Logo -->
            </div>
        </header>
    <#-- End Header Content -->

        <#-- Start Menu -->
        <button class="menu-btn"></button>
        <div class="menu-full-overlay">
            <div class="menu-full-overlay-inner">
				<a class="menu-close-btn"></a>
                <nav class="menu-nav" role="navigation">
                    <ul>
                        <#if show_site_name>
                            <li>
                                <div class="company-name">
                                    <@liferay.language key="welcome-to" />
                                    ${site_name}</div>
                            </li>
                        </#if>

						<li>
							<div class="user-personal-bar">
								<@liferay.user_personal_bar />
							</div>
						</li>
						<li>
							<div class='navigation-section'>
								<#include "${full_templates_path}/navigation.ftl" />
							</div>
						</li>
						<#--  Search Bar  -->
						<#if show_header_search>
							<li>
								<div class="search-container">
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
        <footer id="footer" role="contentinfo">
            <div class="container">
                <div class="footer-area" >
                        <@liferay.language key="powered-by" />

                        <a href="http://www.liferay.com" id="kyTooptip" rel="external" title="Check out our site">Liferay &copy;</a>
                </div>
            </div>
        </footer>
    </#if>
    </div>
    <#-- End footer -->

    <#-- Header Canvas -->
    <script src="/o/kyoung-liferay-theme/js/canvas.js" type="text/javascript"></script>

    <@liferay_util["include"] page=body_bottom_include />

    <@liferay_util["include"] page=bottom_include />

</body>

</html>