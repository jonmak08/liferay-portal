<div class="row">
	<#if img_hero.getData()?? && img_hero.getData() != "">
		<div class="hero">
			<img alt="${img_hero.getAttribute("alt")}" src="${img_hero.getData()}" />
		</div>
	</#if>
</div>