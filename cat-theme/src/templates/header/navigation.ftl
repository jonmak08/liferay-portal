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

<navi class="navi">
    <div class="col-12 col-md-6 navi--content">
        <ul class="navi--list">
            <li class="navi--list-item">Home</li>

            <li class="navi--list-item">About</li>

            <li class="navi--list-item">Projects</li>

            <li class="navi--list-item">Contact</li>
        </ul>
    </div>

    <div class="col-6 d-md-block d-none navi--content navi--content--right">
        <ul class="navi--list">
            <li class="navi--list-item">David</li>

            <li class="navi--list-item">Address</li>

            <li class="navi--list-item">Email</li>

            <li class="navi--list-item">Other</li>
        </ul>
    </div>
</navi>