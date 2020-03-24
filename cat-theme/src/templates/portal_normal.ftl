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

        <div class="container-fluid" id="wrapper">

            <#include "${full_templates_path}/header/navigation.ftl" />

            <#include "${full_templates_path}/header/hero.ftl" />

            <main>
                <#include "${full_templates_path}/content/text_small_img.ftl" />

                <#include "${full_templates_path}/content/small_cards.ftl" />

                <#include "${full_templates_path}/content/large_img_text.ftl" />

                <#include "${full_templates_path}/content/large_cards.ftl" />

                <#include "${full_templates_path}/content/quarter_img_text.ftl" />

                <#include "${full_templates_path}/content/carousel_card.ftl" />

                <#include "${full_templates_path}/content/multiple_cards.ftl" />
            </main>

            <#include "${full_templates_path}/footer/footer.ftl" />
        </div>

        <@liferay_util["include"] page=body_bottom_include />

        <@liferay_util["include"] page=bottom_include />

        <!-- inject:js -->
        
        <!-- endinject -->
    </body>

</html>