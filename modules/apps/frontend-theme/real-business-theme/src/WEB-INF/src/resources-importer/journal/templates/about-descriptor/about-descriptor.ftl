<div class="about-wrapper">
	<div class="about-flex">
		<div class="about-us">
			<h2 class="h2">${AboutUsTitle.getData()}</h2>

			<div class="about-description-text">${AboutUsDescription1.getData()}</div>
			<a class="arrow-link" href="${AboutUsLink.getFriendlyUrl()}">${Textd3b7.getData()} <span class="fa-long-arrow-alt-right fas"></span></a>
		</div>

		<div class="about-us image-wrapper">

			<#if AboutUsImage.getData()?? && AboutUsImage.getData() != "">
				<img alt="${AboutUsImage.getAttribute("alt")}" data-fileentryid="${AboutUsImage.getAttribute("fileEntryId")}" src="${AboutUsImage.getData()}" />
			</#if>

			<div class="image-overlay-li"></div>
		</div>
	</div>
</div>