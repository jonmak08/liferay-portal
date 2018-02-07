<#assign imagesFolderPath = getterUtil.getString(request['theme-display']['path-theme-images']) />

<h1 class="header">
	${header.getData()}
	<hr class="line">
</h1>

<h4 class="subheader">
	${header.subheader.getData()}
</h4>

<div class="action-group side-by-side summary">
	${content.getData()}
</div>

<#if image.getData()?? && image.getData() != "">
	<img alt="${image.getAttribute("alt")}" class="action-group side-by-side" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" />
<#else>
	<img alt="Starman" class="action-group side-by-side" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${imagesFolderPath}/starman.jpg" />
</#if>