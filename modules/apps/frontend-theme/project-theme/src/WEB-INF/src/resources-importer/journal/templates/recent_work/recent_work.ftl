<div class="recent-work row" id="myTooltipDelegate">
	<div class="col-md-12 container-fluid">
		<h1 class="recent-title"> OUR RECENT WORK </h1>
		<div class="recent-work row">

			<div class="col-md-3 recent-work-text">
				<#if ProjectImage1.getData()?? && ProjectImage1.getData() != ""> <img alt="${ProjectImage1.getAttribute("alt")}" class="recent-img" data-fileentryid="${ProjectImage1.getAttribute("fileEntryId")}" src="${ProjectImage1.getData()}" title="Project 1 Paragraph" /> </#if>
				<h5 class="recent-work-title"><a title="Recent Work 1" href="">${TitleOfProject11.getData()}</a><h5>
				<p class="recent-work-text">${Project1Description.getData()}</p>
			</div>

			<div class="col-md-3 recent-work-text">
				<#if ProjectImage2.getData()?? && ProjectImage2.getData() != ""> <img alt="${ProjectImage2.getAttribute("alt")}" class="reent-img" data-fileentryid="${ProjectImage2.getAttribute("fileEntryId")}" src="${ProjectImage2.getData()}" title="Project 2 Paragraph" /> </#if>
				<h5 class="recent-work-title"><a title="Recent Work 2" href="">${TitleOfProject2.getData()}</a><h5>
				<p class="recent-work-text">${Project2Description.getData()}</p>
			</div>

			<div class="col-md-3 recent-work-text">
				<#if ProjectImage3.getData()?? && ProjectImage3.getData() != ""> <img alt="${ProjectImage3.getAttribute("alt")}" class="recent-img" data-fileentryid="${ProjectImage3.getAttribute("fileEntryId")}" src="${ProjectImage3.getData()}" title="Project 3 Paragraph" /> </#if>
				<h5 class="recent-work-title"><a title="Recent Work 3" href="">${TitleOfProject3.getData()}</a><h5>
				<p class="recent-work-text">${Project3Description.getData()}</p>
			</div>

			<div class="col-md-3 recent-work-text">
				<#if ProjectImage4.getData()?? && ProjectImage4.getData() != ""> <img alt="${ProjectImage4.getAttribute("alt")}" class="recent-img" data-fileentryid="${ProjectImage4.getAttribute("fileEntryId")}" src="${ProjectImage4.getData()}" title="Project 4 Paragraph" /> </#if>
				<h5 class="recent-work-title"><a title="Recent Work 4" href="">${TitleOfProject4.getData()}</a><h5>
				<p class="recent-work-text">${Project4Description.getData()}</p>
			</div>

		</div>
	</div>
</div>