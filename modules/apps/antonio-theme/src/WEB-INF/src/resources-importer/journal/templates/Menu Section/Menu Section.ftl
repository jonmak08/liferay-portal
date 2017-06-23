<div class="col-md-10 col-md-offset-1 menu-section">
	<div class="menu-header">

		<#if menuSectionTitle.getSiblings()?has_content>
			<#list menuSectionTitle.getSiblings() as cur_menuSectionTitle>
				<#assign cur_menuSectionSubtitle = cur_menuSectionTitle.menuSectionSubtitle />

				<h2 class="menuSectionTitle">${cur_menuSectionTitle.getData()}</h2>
				<span class="menuSectionSubtitle">${cur_menuSectionSubtitle.getData()}</span>
			</#list>
		</#if>

	</div>

	<#if menuItem.getSiblings()?has_content>
		<#list menuItem.getSiblings() as cur_menuItem>
			<#assign
				cur_menuItemPrice = cur_menuItem.menuItemPrice
				cur_menuItemDescription = cur_menuItem.menuItemDescription
				cur_menuItemImage = cur_menuItem.menuItemImage
			/>

			<div class="col-md-12 menuItem">
				<div class="col-md-2 menuItemImage" style="background-image: url(${cur_menuItemImage.getData()}); background-position: center center; background-size: cover"></div>

				<div class="col-md-10">
					<h4 class="col-md-6 menuItemName">${cur_menuItem.getData()}</h4>

					<h4 class="col-md-6 menuItemPrice">${cur_menuItemPrice.getData()}</h4>

					<div class="col-md-12 menuItemDescription">${cur_menuItemDescription.getData()}</div>
				</div>
			</div>

		</#list>
	</#if>

</div>
