
<nav class="navbar navbar-default" id="navigation" role="navigation">
	<div class="container-fluid" id="nav-ctn">
		<div class="navbar-header">
			<div class="navbar-brand logo" style="height:${logoHeight};">

        <#if showLogoImage>
          <a class="logo-link" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />" width="${logoWidth}">
            <img alt="${logo_description}" class="site-logo" height="${logoHeight}" src="${site_logo}" />
          </a>
        </#if>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>

			</div>

			<button aria-expanded="false" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-ctn" type="button">
				<span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
			</button>


      <#if showLanguagePicker>
        <#include "${full_templates_path}/language_picker.ftl" />
      </#if>

		</div>

		<div class="collapse navbar-collapse" id="navbar-ctn">

			<h1 class="hide-accessible"><@liferay.language key="navigation" /></h1>

			<ul aria-label="<@liferay.language key="site-pages" />" class="nav navbar-nav navbar-right" role="menubar">

				<#list nav_items as nav_item>

					<#assign
						nav_item_attr_has_popup = ""
						nav_item_attr_selected = ""
						nav_item_css_class = ""
						nav_item_layout = nav_item.getLayout()
					/>

					<#if nav_item.isSelected()>
						<#assign
							nav_item_attr_has_popup = "aria-haspopup='true'"
							nav_item_attr_selected = "aria-selected='true'"
							nav_item_css_class = "selected"
						/>

					<#else>
						<#assign
							nav_item_css_class = "dropdown"
						/>

					</#if>

					<li ${nav_item_attr_selected} class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">

						<#if nav_item.hasChildren()>

							<a aria-labelledby="layout_${nav_item.getLayoutId()}"class="dropdown-toggle" data-toggle="dropdown"  ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="button">
								<span>
									<@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}
								</span>
								<span class="icon-angle-down"></span>
							</a>

							<ul class="dropdown-menu" role="menu">

								<#list nav_item.getChildren() as nav_child>
									<#assign
										nav_child_attr_selected = ""
										nav_child_css_class = ""
									/>

									<#if nav_item.isSelected()>
										<#assign
											nav_child_attr_selected = "aria-selected='true'"
											nav_child_css_class = "selected"
										/>
									</#if>

									<li ${nav_child_attr_selected} class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
										<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
									</li>
								</#list>

							</ul>

						<#else>
							<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
								<span>
									<@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}
								</span>
							</a>
						</#if>

					</li>
				</#list>

			<#if !is_signed_in>
				<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
			</#if>

			</ul>
		</div>
	</div>
</nav>
