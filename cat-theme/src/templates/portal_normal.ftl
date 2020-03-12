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
    <div id="navigation" class="navigation">
            <a class="nav__logo" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
                <img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" />

                <#if show_site_name>
                    ${site_name}
                </#if>
            </a>
        <div class="menu__wrapper">
        <div class="menu-icon">
            <span class="menu-icon__line menu-icon__line-left"></span>
            <span class="menu-icon__line"></span>
            <span class="menu-icon__line menu-icon__line-right"></span>
        </div>
            <span class="menu-title">Menu</span>
        </div>
        

    </div>

    <nav class="nav">
        <div class="nav__content">
            <ul class="nav__list">
                <li class="nav__list-item">Home</li>
                <li class="nav__list-item">About</li>
                <li class="nav__list-item">Projects</li>
                <li class="nav__list-item">Contact</li>
            </ul>
        </div>
    </nav>

    <header id="hero" class="hero">
        <div class="container-fluid">
            <div class="row">
                <div class="col-2 d-none d-md-block hero_carousal ">
                </div>
                <div class="offset-1 col-11 offset-md-0 col-md-8 hero__wrapper">
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