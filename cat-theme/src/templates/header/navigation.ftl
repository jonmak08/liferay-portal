<div class="navigation" id="navigation">
    <a class="nav__logo" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
        <img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" />

        <#if show_site_name>
            ${site_name}
        </#if>
    </a>
    <div class="menu--wrapper">
        <#if my_background_image_color>
            <div class="menu-icon">
                <span class="menu-icon--line menu-icon--line-left"></span>
                <span class="menu-icon--line"></span>
                <span class="menu-icon--line menu-icon--line-right"></span>
            </div>

            <span class="menu-title">Menu</span>
        <#else>
            <div class="menu-icon">
                <span class="menu-icon--line menu-icon--line-left" style="background-color: #0009"></span>
                <span class="menu-icon--line" style="background-color: #0009"></span>
                <span class="menu-icon--line menu-icon--line-right" style="background-color: #0009"></span>
            </div>

            <span class="menu-title" style="color: #0009">Menu</span>
        </#if>
        
        <div class="menu-liferay">
            <@liferay.user_personal_bar />
        </div>
    </div>
</div>

<nav class="cat--navi">
    <div class="col-12 col-md-6 cat--navi--content">
        <ul class="cat--navi--list">
            <#list nav_items as nav_item>
                <#assign
                    nav_item_attr_has_popup = ""
                    nav_item_css_class = "cat--navi--list-item"
                    nav_item_layout = nav_item.getLayout()
                    nav_item_caret = ""
                    nav_item_counter = nav_item?counter
                />

                <#if nav_item.isSelected()>
                    <#assign
                    nav_item_attr_has_popup = "aria-haspopup='true'"
                    nav_item_css_class = "${nav_item_css_class} selected"
                    />
                </#if>
                <#if (nav_item_counter < 9)>
                    <li id="layout_${nav_item.getLayoutId()}" role="presentation">
                        <a 
                            aria-labelledby="layout_${nav_item.getLayoutId()}" 
                            ${nav_item_attr_has_popup} 
                            class="${nav_item_css_class} ${nav_item_counter}"
                            href="${nav_item.getURL()}" 
                            ${nav_item.getTarget()} 
                            role="menuitem"
                            >
                            <span class="${nav_item_css_class} add-selected">
                                ${nav_item.getName()?left_pad(20)[0..*20]}
                            </span> 
                            ${nav_item_caret}
                        </a>
                    </li>
                </#if>
            </#list>
        </ul>
    </div>

    <div class="col-6 d-md-block d-none cat--navi--content cat--navi--content--right">
        <ul class="cat--navi--list" role="menu">
            <#list nav_items as nav_item>
                <#if nav_item.isSelected()>
                    <#assign
                    nav_item_attr_has_popup = "aria-haspopup='true'"
                    nav_item_css_class = "${nav_item_css_class} selected"
                    />

                    <#if nav_item.hasChildren()>
                        <#list nav_item.getChildren() as nav_child>
                            <#assign
                            nav_child_css_class = "cat--navi--list-item cat--navi--list-item--sub"
                            nav_child_counter = nav_child?counter
                            />

                            <#if nav_child.isSelected()>
                                <#assign
                                nav_child_css_class = "{nav_item_css_class} selected"
                                />
                            </#if>

                            <#if (nav_child_counter < 11)>
                                <li id="layout_${nav_child.getLayoutId()}" role="presentation">
                                    <a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">
                                        <span class="${nav_child_css_class}">
                                            ${nav_child.getName()?left_pad(20)[0..*20]}
                                        </span>
                                    </a>
                                </li>
                            </#if>
                        </#list>
                    </#if>
                </#if>
            </#list>
        </ul>
    </div>
</nav>