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
    <nav id="navigation" class="navigation">
            <a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
                <img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" />

                <#if show_site_name>
                    ${site_name}
                </#if>
            </a>

        <div id="hamburger" class="hamburger">
            <button id="hamburger__btn" class="hamburger_btn">
                <span class="hamburger__span"></span>
                <span class="hamburger__span"></span>
                <span class="hamburger__span"></span>
            </button>
        </div>
    </nav>
    
    
    <header id="hero" class="hero">
        <div class="container-fluid">
            <div class="row">
                <div class="col-2 hero_carousal">
                </div>
                <div class="col-8 hero__wrapper">
                    <div class="hero__category">
                        <hr/>
                        <span>Cats</span>
                    </div>
                    <h1  class="hero__title"><a>Cats In Space</a></h1>
                    </h1>
                    <p class="hero__description"> Sometimes, we need to check the time, wondering when our work or meeting will finish, without getting caught by others.</p>
                        <div class="btn__main">
                            <a>
                                <div class="btn__main__arrow">>
                                </div>
                                <span class="btn__main__text">View Case
                                </span>
                            </a>
                        </div>
<div> test</div>
                </div>
            </div>
        </div>
    </header>
    <section  id="about">
    </section>
    <section id="other_content">
    </section>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

</body>

</html>