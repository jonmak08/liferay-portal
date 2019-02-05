<#if has_navigation && is_setup_complete>
	<button aria-controls="navigationCollapse" aria-expanded="false" aria-label="Toggle navigation" class="fa fa-bars navbar-toggler navbar-toggler-right" data-target="#navigationCollapse" data-toggle="collapse" type="button"></button>

	<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
        <div id="navigation" class="navcollapse" role="navigation">
            <ul class="nav navbar-nav navbar-site">
            <#list nav_items as nav_item>
				<#assign
					nav_item_attr_has_popup = ""
					nav_item_css_class = "lfr-nav-item nav-item navbar-nav"
					nav_item_layout = nav_item.getLayout()
				/>

			<#if nav_item.isSelected()>
				<#assign
					nav_item_attr_has_popup = "aria-haspopup='true'"
					nav_item_css_class = " active"
				/>
			</#if>
                    <li class="${nav_item_css_class}">
                        <a href="${nav_item.getURL()}" class="nav-link">${nav_item.getName()}</a>
                        <#if nav_item.hasChildren()>
                            <ul>
                                <#list nav_item.getChildren() as nav_child>
                                <li>
                                    <a href="${nav_child.getURL()}" class="nav-child">${nav_child.getName()}</a>
                                        <#if nav_child.hasChildren()>
                                            <ul>
                                                <#list nav_child.getChildren() as nav_child>
                                                    <li>
                                                        <a href="${nav_child.getURL()}" class="nav-child">${nav_child.getName()}</a>
                                                    </li>
                                                </#list>
                                            </ul>
                                        </#if>
                                </li>
                                </#list>
                            </ul>
                        </#if>
                    </li>
                </#list>
            </ul>
        </div>
	</div>
</#if>

