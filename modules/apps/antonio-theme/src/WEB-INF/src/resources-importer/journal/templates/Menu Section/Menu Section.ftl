<div class="col-md-10 col-md-offset-1 menu-section">
	<div class="menu-header">

		<#if menu-section-title.getSiblings()?has_content>
			<#list menu-section-title.getSiblings() as cur_menu-section-title>
				<#assign cur_menu-section-subtitle = cur_menu-section-title.menu-section-subtitle />

				<h2 class="menu-section-title">${cur_menu-section-title.getData()}</h2>
				<span class="menu-section-subtitle">${cur_menu-section-subtitle.getData()}</span>
			</#list>
		</#if>

	</div>

	<#if menu-item.getSiblings()?has_content>
		<#list menu-item.getSiblings() as cur_menu-item>
			<#assign
				cur_menu-item-price = cur_menu-item.menu-item-price
				cur_menu-item-description = cur_menu-item.menu-item-description
				cur_menu-item-image = cur_menu-item.menu-item-image
			/>

			<div class="col-md-12 menu-item">
				<div class="col-md-2 menu-item-image" style="background-image: url(${cur_menu-item-image.getData()}); background-position: center center; background-size: cover"></div>

				<div class="col-md-10">
					<h4 class="col-md-6 menu-item-name">${cur_menu-item.getData()}</h4>

					<h4 class="col-md-6 menu-item-price">${cur_menu-item-price.getData()}</h4>

					<div class="col-md-12 menu-item-description">${cur_menu-item-description.getData()}</div>
				</div>
			</div>

		</#list>
	</#if>

</div>
