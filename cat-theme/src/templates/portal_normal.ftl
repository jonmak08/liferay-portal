<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
    <head>
        <title>${the_title} - ${company_name}</title>

        <meta content="initial-scale=1.0, width=device-width" name="viewport" />

        <@liferay_util["include"] page=top_head_include />
    </head>

    <body>
        <@liferay_ui["quick-access"] contentId="#main-content" />

        <@liferay_util["include"] page=body_top_include />

        <@liferay.control_menu />

        <div class="container-fluid" id="wrapper">
            <#if has_navigation && is_setup_complete>
                <#include "${full_templates_path}/header/navigation.ftl" />
            </#if>

            <#include "${full_templates_path}/header/hero.ftl" />

            <main>
                <section id="content">
                    <h2 class="hide-accessible" role="heading" aria-level="1">${the_title}</h2>

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
            </main>

            <#if show_footer>
                <#include "${full_templates_path}/footer/footer.ftl" />
            </#if>
        </div>

        <@liferay_util["include"] page=body_bottom_include />

        <@liferay_util["include"] page=bottom_include />

        <!-- inject:js -->
        
        <!-- endinject -->
    </body>
</html>