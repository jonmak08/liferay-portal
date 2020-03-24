<div class="navigation" id="navigation">
    <a class="nav__logo" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
        <img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" />

        <#if show_site_name>
            ${site_name}
        </#if>
    </a>
    <div class="menu--wrapper">
        <div class="menu-icon">
            <span class="menu-icon--line menu-icon--line-left"></span>
            <span class="menu-icon--line"></span>
            <span class="menu-icon--line menu-icon--line-right"></span>
        </div>

        <span class="menu-title">Menu</span>
    </div>
</div>

<nav class="nav">
    <div class="col-12 col-md-6 nav--content">
        <ul class="nav--list">
            <li class="nav--list-item">Home</li>

            <li class="nav--list-item">About</li>

            <li class="nav--list-item">Projects</li>

            <li class="nav--list-item">Contact</li>
        </ul>
    </div>

    <div class="col-6 d-md-block d-none nav--content nav--content--right">
        <ul class="nav--list">
            <li class="nav--list-item">David</li>

            <li class="nav--list-item">Address</li>

            <li class="nav--list-item">Email</li>

            <li class="nav--list-item">Other</li>
        </ul>
    </div>
</nav>