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
        <div class="nav__content col-md-6 col-12">
            <ul class="nav__list">
                <li class="nav__list-item">Home</li>
                <li class="nav__list-item">About</li>
                <li class="nav__list-item">Projects</li>
                <li class="nav__list-item">Contact</li>
            </ul>
        </div>
            <div class="nav__content nav__content--right col-6 d-none d-md-block">
            <ul class="nav__list">
                <li class="nav__list-item">David</li>
                <li class="nav__list-item">Address</li>
                <li class="nav__list-item">Email</li>
                <li class="nav__list-item">Other</li>
            </ul>
        </div>
    </nav>

    <header id="hero" class="hero">
        <div class="container-fluid">
            <div class="row">
                <div class="col-2 d-none d-md-block hero_carousal ">
                </div>
                <div class="col-11 col-md-8 offset-1 offset-md-0 hero__wrapper">
                    <div class="caption caption--header">
                        <hr class="caption__line caption__line--darkbg"/>
                        <span class="caption__title">Cats</span>
                    </div>
                    <h1  class="hero__title"><a>Cats In Space</a></h1>
                    </h1>
                    <p class="hero__description"> Sometimes, we need to check the time, wondering when our work or meeting will finish, without getting caught by others.</p>
                        <div class="btn__main">
                            <a>
                                <div class="btn__main__arrow">&#8658;
                                </div>
                                <span class="btn__main__text">View Case
                                </span>
                            </a>
                        </div>

                </div>
            </div>
        </div>
    </header>

    <main>
        <section  id="about" class="about">
            <div class="container">
                <div class="row">
                    <div class="about__bio col-lg-6 col-md-10 offset-lg-0 offset-md-1 ">
                        <div class="about__bio__wrapper">
                            <h2 class="about__bio__header">
                                Hello I am,
                                <br/>
                                Space Cat
                            </h2>
                            <p class="about__bio__desc">
                                Hello, my name is Picatsso. I am a cat. I enjoy the simple things in life such as fish and lasers. Hello, my name is Picatsso. I am a cat. I enjoy the simple things in life such as fish and lasers.Hello, my name is Picatsso. I am a cat. I enjoy the simple things in life such as fish and lasers. 
                            </p>
                            <div class="caption caption--signature">
                                <span class="caption__title caption__title--signature">Picatsso</span>
                                <hr class="caption__line"/>
                                <span class="caption__title caption__title--dot">•</span>
                            </div>
                            <div class="about__bio__signature">
                                Space Cat
                            </div>
                            <div class="about__bio__years">
                                <span class="about__bio__years__number">
                                    7
                                </span>
                                <span class="about__bio__years__text">
                                    Lives of 
                                    <br/>
                                    Space Experience
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="about__img">
                        <div class="about__img__container">
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="services" class="services">
            <div class="container">
                <div class="row">
                    <div class="services__wrapper col-lg-12 col-md-10 offset-lg-0 offset-md-1">
                        <div class="offset-1 caption">
                            <span class="caption__title caption__title--dot">•</span>
                            <hr class="caption__line"/>
                            <span class="caption__title">Mission Details</span>
                        </div>
                            <h2 class="services__header">Apawllo 18</h2>
                    </div>
                    <div class="services__subject col-12 col-md-4">
                        <div class="services__subject__container">
                            <div class="services__subject__icon">
                                <img src="o/cat-theme/images/cat_icon.png" alt="Cat Icon"/>
                            </div>
                            <div class="services__subject__header">
                            <h3>Looking for Purrfect Candidates</h3>
                            <p> Laser focused, Laser focused, Laser focused, Laser focused, Laser focused, Laser focused
                            </p>
                            </div>
                        </div>
                    </div> 
                    <div class="services__subject col-12 col-md-4">
                        <div class="services__subject__container">
                            <div class="services__subject__icon">
                                <img src="o/cat-theme/images/cat_icon.png" alt="Cat Icon"/>
                            </div>
                            <div class="services__subject__header">
                            <h3>Willing to travel to a galaxy fur fur away</h3>
                            <p> Could possibly be a one way flight. I repeat could possibly be a one way flight
                            </p>
                            </div>
                        </div>
                    </div> 
                    <div class="services__subject col-12 col-md-4">
                        <div class="services__subject__container">
                            <div class="services__subject__icon">
                                <img src="o/cat-theme/images/cat_icon.png" alt="Cat Icon"/>
                            </div>
                            <div class="services__subject__header">
                            <h3>Top Shelf Roccat fuel used</h3>
                            <p> Only the best for the best catstronauts
                            </p>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
        </section>

    </main>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

</body>

</html>