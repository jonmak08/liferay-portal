<div class="blog">
	<div class="blog-image">
		<#if image.getData()?? && image.getData() != "">
			<img alt="${image.getAttribute('alt')}" src="${image.getData()}" />
		</#if>
	</div>

	<div class="blog-title">
		${title.getData()}
	</div>

	<h5 class="blog-subtitle">
		${subtitle.getData()}
	</h5>

	<div class="blog-date">
		<#assign date_Data = getterUtil.getString(date.getData())>

		<#if validator.isNotNull(date_Data)>
			<#assign date_DateObj = dateUtil.parseDate("yyyy-MM-dd", date_Data, locale)>

			${dateUtil.getDate(date_DateObj, "dd MMM yyyy", locale)}
		</#if>
	</div>

	${content.getData()}
	<br />
	<hr />
</div>