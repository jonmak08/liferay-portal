<section class="media-tiles">
	<div class="tile-container">

		<figure>
			<div class="tile" style="background-image: url(${heading_1.image_1.getData()})">
				<figcaption>

				<#if heading_1 != ''>
					<h3 class="tile-header" style="color:${heading_1.text_color_1.getData()};">${heading_1.getData()}</h3>
				</#if>

				<#if heading_1.caption_1 != ''>
					<h5 style="color:${heading_1.text_color_1.getData()};">${heading_1.caption_1.getData()}</h5>
				</#if>
				</figcaption>
			</div>
		</figure>

		<figure>
			<div class="tile" style="background-image: url(${heading_2.image_2.getData()})">
				<figcaption>

				<#if heading_2 != ''>
					<h3 class="tile-header" style="color:${heading_2.text_color_2.getData()};">${heading_2.getData()}</h3>
				</#if>

				<#if heading_2.caption_2 != ''>
					<h5 style="color:${heading_2.text_color_2.getData()};">${heading_2.caption_2.getData()}</h5>
				</#if>

				</figcaption>
			</div>
		</figure>

	</div>

	<div class="tile-container">

		<figure>
			<div class="tile" style="background-image: url(${heading_3.image_3.getData()})">
				<figcaption>

				<#if heading_3 != ''>
					<h3 class="tile-header" style="color:${heading_3.text_color_3.getData()};">${heading_3.getData()}</h3>
				</#if>

				<#if heading_3.caption_3 != ''>
					<h5 style="color:${heading_3.text_color_3.getData
					()};">${heading_3.caption_3.getData()}</h5>
				</#if>

				</figcaption>
			</div>
		</figure>

		<figure>
			<div class="tile" style="background-image: url(${heading_4.image_4.getData()})">
				<figcaption>

				<#if heading_4 != ''>
					<h3 class="tile-header" style="color:${heading_4.text_color_4.getData()};">${heading_4.getData()}</h3>
				</#if>

				<#if heading_4.caption_4 != ''>
					<h5 style="color:${heading_4.text_color_4.getData()};">${heading_4.caption_4.getData()}</h5>
				</#if>

				</figcaption>
			</div>
		</figure>

	</div>
</section>
