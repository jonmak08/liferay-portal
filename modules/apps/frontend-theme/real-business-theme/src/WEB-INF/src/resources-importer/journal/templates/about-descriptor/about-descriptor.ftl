<div class="about-wrapper">
	<div class="about-flex">
		<div class="about-us">

			<h2>${AboutUsTitle.getData()}</h2>

			<p>${AboutUsDescription1.getData()}

			<a class="arrow-link" href="${AboutUsLink.getFriendlyUrl()}">${Textd3b7.getData()} <span class="fa-long-arrow-alt-right fas"></span></a>

			</p>

		</div>

		<div class="about-us image-wrapper">

			<#if AboutUsImage.getData()?? && AboutUsImage.getData() != ""> <img alt="${AboutUsImage.getAttribute("alt")}" data-fileentryid="${AboutUsImage.getAttribute("fileEntryId")}" src="${AboutUsImage.getData()}" /> </#if>

			<div class="image-overlay-li"></div>
		</div>
	</div>
</div>