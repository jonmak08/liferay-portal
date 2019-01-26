<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
    <title>${the_title} - ${company_name}</title>
    <meta content="initial-scale=1.0, width=device-width" name="viewport" />
    <link href="https://fonts.googleapis.com/css?family=Lato|Open+Sans|Oswald:400,700" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<#assign preferences = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "barebone"}) />

<div class="pt-0" id="wrapper">

    <div id="page-container">
        <div id="container-wrap">

            <header class="site-header">
                <div class="top-bar">
                    <nav class="social-navigation">
                        <ul id="menu-social">
                            <li>
                                <a href="${facebook}" target="_blank"></a>
                            </li>
                            <li>
                                <a href="${instagram}" target="_blank"></a>
                            </li>
                            <li>
                                <a href="${twitter}" target="_blank"></a>
                            </li>
                            <li>
                                <a href="${linkedin}" target="_blank"></a>
                            </li>
                            <li>
                                <a href="${pinterest}" target="_blank"></a>
                            </li>
                        </ul>
                    </nav>
                    <div class="menu-login">
                        <@liferay.user_personal_bar />
                    </div>
                </div>
                <div class="container">
                    <div class="site-branding">
                        <h1 class="site-title">${title_text}</h1>
                        <h2 class="site-description">${description_text}</h2>
                    </div>
                </div>
                <div class="main-navigation" role="navigation">
                    <div class="nested-navigation">
                        <@liferay.navigation_menu default_preferences="${preferences}" />
                    </div>
                </div>
            </header>

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

            <footer id="footer" role="contentinfo">
                <div class="container">
                    <div class="row">
                        <div class="powered-by">
                            <@liferay.language key="powered-by" />

                            <a href="http://www.liferay.com" rel="external" target="_blank">Liferay</a>
                        </div>
                    </div>
                </div>
            </footer>

        </div>
    </div>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>