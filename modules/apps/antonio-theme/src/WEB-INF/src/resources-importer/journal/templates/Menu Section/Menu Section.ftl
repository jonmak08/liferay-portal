<div class="col-md-10 col-md-offset-1 menu-section">
    <div class="menu-header">

        <#if MenuSectionTitle.getSiblings()?has_content>
        	<#list MenuSectionTitle.getSiblings() as cur_MenuSectionTitle>
        	    <#assign cur_MenuSectionSubtitle = cur_MenuSectionTitle.MenuSectionSubtitle />

        		<h2 class="menu-section-title">${cur_MenuSectionTitle.getData()}</h2>
        		<span class="menu-section-subtitle">${cur_MenuSectionSubtitle.getData()}</span>

        	</#list>
        </#if>

    </div>

    <#if MenuItem.getSiblings()?has_content>
    	<#list MenuItem.getSiblings() as cur_MenuItem>
    	    <#assign
            cur_MenuItemPrice       = cur_MenuItem.MenuItemPrice
            cur_MenuItemDescription = cur_MenuItem.MenuItemDescription
            cur_MenuItemImage       = cur_MenuItem.MenuItemImage
    	    />

    	    <div class="col-md-12 menu-item">
        	    <div class="col-md-2 menu-item-image" style="background-image: url(${cur_MenuItemImage.getData()}); background-position: center center; background-size: cover"></div>
        	    <div class="col-md-10">
    	            <h4 class="col-md-6 menu-item-name">${cur_MenuItem.getData()}</h4>
        	        <h4 class="col-md-6 menu-item-price">${cur_MenuItemPrice.getData()}</h4>
                    <div class="col-md-12 menu-item-description">${cur_MenuItemDescription.getData()}</div>
                </div>
            </div>

    	</#list>
    </#if>

</div>
