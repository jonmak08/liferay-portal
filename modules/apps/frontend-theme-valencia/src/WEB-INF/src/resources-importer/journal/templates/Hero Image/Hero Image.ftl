<#assign themePath = getterUtil.getString(request['theme-display']['path-theme-images']) />
<#if (Select10rv.getData()) == "about">
	<#assign selectedImage = "about-hero.jpg" />
<#elseif (Select10rv.getData()) == "contact">
	<#assign selectedImage = "contact-hero.jpg" />
</#if>
<#if Select10rv.getData?? && Select10rv.getData() != "">
	<img alt="${Alt.getData()}" class="img-responsive" src="${themePath}/${selectedImage}" />
</#if>