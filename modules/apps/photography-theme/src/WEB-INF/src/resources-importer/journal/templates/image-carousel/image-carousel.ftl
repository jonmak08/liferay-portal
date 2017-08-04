<#if image.getSiblings()?has_content>
	<div class="carousel" style="height:${height.getData()}${height_units.getData()};">
		<#list image.getSiblings() as cur_image>

			<#if cur_image.getData()?? && cur_image.getData() != "">
				<div class="carousel-cell" style="background: url(${cur_image.getData()})" >
					<div class="heading-container">

					<#if cur_image.heading_1 != ''>
						<h1 style="color:${cur_image.text_colors.getData()};">${cur_image.heading_1.getData()}</h1>
					</#if>

					<#if cur_image.heading_2 != ''>
						<h2 style="color:${cur_image.text_colors.getData()};">${cur_image.heading_2.getData()}</h2>
					</#if>

					</div>
				</div>
			</#if>

		</#list>
	</div>
</#if>
