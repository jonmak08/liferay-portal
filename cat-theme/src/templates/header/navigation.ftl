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
    <div class="col-12 col-md-6 nav__content ">
        <ul class="nav__list">
            <li class="nav__list-item">Home</li>
            <li class="nav__list-item">About</li>
            <li class="nav__list-item">Projects</li>
            <li class="nav__list-item">Contact</li>
        </ul>
    </div>

    <div class="col-6 d-md-block d-none nav__content nav__content--right">
        <ul class="nav__list">
            <li class="nav__list-item">David</li>
            <li class="nav__list-item">Address</li>
            <li class="nav__list-item">Email</li>
            <li class="nav__list-item">Other</li>
        </ul>
    </div>
</nav>