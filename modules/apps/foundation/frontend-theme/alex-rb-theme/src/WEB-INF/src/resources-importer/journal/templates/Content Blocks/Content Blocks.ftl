<#assign imagesFolderPath = getterUtil.getString(request['theme-display']['path-theme-images']) />

<#if image.getSiblings()?has_content>
	<#if header.getData()?? && header.getData() != "">
		<h1 class="header">
			${header.getData()}
			<hr class="line">
		</h1>
	</#if>

	<#list image.getSiblings() as cur_image>
		<div class="action-group content-block">
			<#if cur_image.getData()?? && cur_image.getData() != "">
				<#if cur_image.getData()?starts_with("/documents/") || cur_image.getData()?starts_with("/image/") >
					<#assign imagePath = cur_image.getData()>
				<#else>
					<#assign imagePath = "${imagesFolderPath}/${cur_image.getData()}">
				</#if>

				<img alt="${cur_image.getAttribute("alt")}" class="picture" data-fileentryid="${cur_image.getAttribute("fileEntryId")}" src="${imagePath}" />
			</#if>

			<#if cur_image.subheader.getData()?? && cur_image.subheader.getData() != "">
				<h4 class="subheader">
					${cur_image.subheader.getData()}
					<hr class="line">
				</h4>
			</#if>

			<#if cur_image.subtitle.getData()?? && cur_image.subtitle.getData() != "">
				<div class="subtitle">
					${cur_image.subtitle.getData()}
				</div>
			</#if>

			<#if cur_image.summary.getData()?? && cur_image.summary.getData() != "">
				<div class="summary">
					${cur_image.summary.getData()}
				</div>
			</#if>
		</div>
	</#list>
</#if>