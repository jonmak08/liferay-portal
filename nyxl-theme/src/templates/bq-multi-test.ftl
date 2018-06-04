
<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone")>

<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
	<#if has_navigation && is_setup_complete>
		<nav class="${nav_css_class} site-navigation" id="navigation" role="navigation">
			<div class="navbar-form navbar-right" role="search">
				<@liferay.search default_preferences="${freeMarkerPortletPreferences}" />
			</div>

			<div class="navbar-right">
		        <@liferay.navigation_menu default_preferences="${freeMarkerPortletPreferences}" />

                    <ul class="nav navbar-nav nav-tabs">    
                            <#assign  count = 0 />
                            <#list nav_items as nav_item>
                                <#assign  count = count +1  />
                                <#assign  nav_item_class = "lfr-nav-item -" + count />
                        
                                <#if count == 1>
                                    <#assign  nav_item_class = nav_item_class + " first" />
                                </#if>

                                <#if nav_item.isSelected() >
                                    <#assign nav_item_class = nav_item_class + " selected active" />
                                </#if>
                                
                                <li class="${nav_item_class}">
                                    <a href="${nav_item.getURL()}" ${nav_item.getTarget()}>
                                        <span>${nav_item.getName()}</span>
                                        <#if nav_item.hasChildren()>
                                            <span class="lfr-nav-child-toggle"><i class="icon-caret-down"></i></span>
                                        </#if>
                                    </a>

                                    <#if nav_item.hasChildren()>
                                        <ul class="dropdown-menu child-menu" role="menu">
                                            <#list nav_item.getChildren() as nav_child>
                                                <#if nav_item.isSelected()>
                                                    <li class="selected sub">
                                                    </li>
                                                <#else>
                                                    <li class="sub">
                                                </#if>
                                                        <a href="${nav_child.getURL()}" ${nav_child.getTarget()}>${nav_child.getName()}</a>
                                                    </li>
                                            </#list>
                                        </ul>
                                    </#if>
                                </li>
                                
                            </#list>
                    </ul>

    			</div>
		</nav>
	</#if>
</div>

<#assign VOID = freeMarkerPortletPreferences.reset()>