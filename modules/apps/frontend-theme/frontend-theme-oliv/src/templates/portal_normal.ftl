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

<#assign
    preferences = freeMarkerPortletPreferences.getPreferences(
        {
            "portletSetupPortletDecoratorId": "barebone"
        }
    )
/>

<div class="pt-0" id="wrapper">
    <div class="page-container">
        <div class="wrap-container">

            <header class="site-header">
                <div class="top-bar">
                    <nav class="social-navigation">
                        <ul id="menu-social">

                            <#if facebook_handle?has_content>
                                <li title="@${facebook_handle}">
                                    <a href="http://facebook.com/${facebook_handle}" target="_blank"></a>
                                </li>
                            </#if>

                            <#if instagram_handle?has_content>
                                <li title="@${instagram_handle}">
                                    <a href="http://instagram.com/${instagram_handle}" target="_blank"></a>
                                </li>
                            </#if>

                            <#if twitter_handle?has_content>
                                <li title="@${twitter_handle}">
                                    <a href="http://twitter.com/${twitter_handle}" target="_blank"></a>
                                </li>
                            </#if>

                            <#if linkedin_handle?has_content>
                                <li title="@${linkedin_handle}">
                                    <a href="http://linkedin.com/in/${linkedin_handle}" target="_blank"></a>
                                </li>
                            </#if>

                            <#if pinterest_handle?has_content>
                                <li title="@${pinterest_handle}">
                                    <a href="http://pinterest.com/${pinterest_handle}" target="_blank"></a>
                                </li>
                            </#if>

                        </ul>
                    </nav>

                    <div class="menu-login">
                        <@liferay.user_personal_bar />
                    </div>
                </div>

                <div class="site-container">
                    <div class="site-branding">
                        <h1 class="site-title">
                            <#if show_site_logo>
                                <img alt="${logo_description}" src="${site_logo}" />
                            </#if>
                            ${title_text}
                        </h1>
                        <h2 class="site-description">
                            ${description_text}
                        </h2>
                    </div>
                </div>

                <div class="main-navigation" role="navigation">
                    <span class="toggle-navigation" id="toggle-navigation">&#9776;</span>
                    <#if has_navigation && is_setup_complete>
                        <div class="nested-navigation" id="nested-navigation">
                            <#include "${full_templates_path}/navigation.ftl" />
                        </div>
                    </#if>
                </div>
            </header>

            <section class="${portal_content_css_class}" id="content">
                <h1 class="sr-only">
                    ${the_title}
                </h1>

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

            <footer class="footer" id="footer" role="contentinfo">
                <div class="col-md-12 text-center">
                    <div class="powered-by">
                        <@liferay.language key="powered-by" />

                        <a href="http://www.liferay.com" rel="external" target="_blank">Liferay</a>
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