<div class="about-wrapper">
		<div class="about-flex">
			<div class="about-us">
				<h2>${AboutUsTitle.getData()}</h2>
				<p>${AboutUsDescription1.getData()}<br>
				<a class="arrow-link" href="${AboutUsLink.getFriendlyUrl()}">Learn More <span class="fas fa-long-arrow-alt-right"></span></a>
				</p>
				
			</div>
			<div class="about-us imageWrapper">
			<#if AboutUsImage.getData()?? && AboutUsImage.getData() != ""> <img alt="${AboutUsImage.getAttribute("alt")}" data-fileentryid="${AboutUsImage.getAttribute("fileEntryId")}" src="${AboutUsImage.getData()}" /> </#if> <div class="imageOverlayLi"></div> </div> </div> </div>