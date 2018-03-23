<div class="image">
	<#if image.getData()?? && image.getData() != "">
		<img alt="${image.getAttribute('alt')}" src="${image.getData()}" />
	</#if>
</div>