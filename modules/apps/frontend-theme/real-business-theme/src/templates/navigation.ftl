<#if has_navigation && is_setup_complete>
	<button aria-controls="navigationCollapse" aria-expanded="false" aria-label="Toggle navigation" class="fa fa-bars navbar-toggler navbar-toggler-right" data-target="#navigationCollapse" data-toggle="collapse" type="button"></button>

	<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
        <div class="drop" id="navigation" role="navigation">
            <ul class="nav nav-pills navbar-site">

                <#list nav_items as nav_item>

                    <#assign
                        nav_item_attr_has_popup = ""
                        nav_item_css_class = "lfr-nav-item nav-item dropdown main-nav-link"
                        nav_item_layout = nav_item.getLayout()
                    />

                    <#if nav_item.isSelected()>
                    <#assign
                        nav_item_attr_has_popup = "aria-haspopup='true'"
                        nav_item_css_class += " active"
                    />

                    </#if>
                    <li class="${nav_item_css_class}">
                        <a class="nav-link" href="${nav_item.getURL()}">${nav_item.getName()}</a>

                        <#if nav_item.hasChildren()>
						<span class="fa fa-angle-down ml-3"></span>

                            <ul class="child-menu child-toggle nav-closed" role="menu">

                                <#list nav_item.getChildren() as nav_child>

                                <li class="dropdown lfr-nav-item main-nav-link nav-item ">
                                    <a class="nav-link" href="${nav_child.getURL()}">${nav_child.getName()}</span></a>

                                        <#if nav_child.hasChildren()>

										<span class="fa fa-angle-down grandchild-arrow"></span>

                                            <ul class="child-menu grandchild-toggle nav-closed" role="menu">

                                                <#list nav_child.getChildren() as nav_child>
                                                    <li class="dropdown lfr-nav-item main-nav-link nav-item">
                                                        <a class="nav-link" href="${nav_child.getURL()}">${nav_child.getName()}</a>
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