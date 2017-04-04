<#assign
	id_count = 0
	slide_count = 0
	theme_path = getterUtil.getString(request['theme-display']['path-theme-images'])
/>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12">
			<div class="carousel slide" id="imageGalleryCarousel">
				<div class="carousel-inner">

					<#if ImageURL.getSiblings()?has_content>
						<#list ImageURL.getSiblings() as cur_ImageURL>
							<#if cur_ImageURL?is_first>
								<#assign carousel_item_css = "active item" />
							<#else>
								<#assign carousel_item_css = "item" />
							</#if>

							<div class="${carousel_item_css}" data-slide-number="${slide_count}">
								<img src="${theme_path}/${cur_ImageURL.getData()}">
							</div>

							<#assign slide_count = slide_count+1 />

						</#list>
					</#if>
				</div>
				<a class="carousel-control left "data-slide="prev" href="#imageGalleryCarousel" role="button" ><span class="glyphicon glyphicon-chevron-left"></span>
				</a>
				<a class="carousel-control right" data-slide="next" href="#imageGalleryCarousel" role="button" ><span class="glyphicon glyphicon-chevron-right"></span>
				</a>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12" id="slider-thumbs">
			<ul class="thumbnail-list">

				<#if ImageURL.getSiblings()?has_content>
					<#list ImageURL.getSiblings() as cur_ImageURL>

						<li class="col-md-2 col-sm-3">
							<a class="carousel-selector thumbnail" data-id="${id_count}"><img alt="${cur_ImageURL.Alt.getData()}" src="${theme_path}/${cur_ImageURL.getData()}" /></a>
						</li>

					<#assign id_count = id_count+1 />

					</#list>
				</#if>

			</ul>
		</div>
	</div>
</div>