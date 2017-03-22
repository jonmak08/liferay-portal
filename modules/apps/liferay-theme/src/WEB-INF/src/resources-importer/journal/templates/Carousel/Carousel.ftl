<div class="carousel-container">
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<div class="carousel-inner" role="listbox">
			<#if CarouselImages.getSiblings()?has_content>
				<#list CarouselImages.getSiblings() as cur_CarouselImages>
					<#if cur_CarouselImages.getData()?? && cur_CarouselImages.getData() != "">
						<#if cur_CarouselImages?index == 0>
							<div class="active item">
						<#else>
							<div class="item">
						</#if>

							<div style="background:url(${cur_CarouselImages.getData()}) center center; background-size:cover;" class="slider-size">
								<div class="carousel-caption">${cur_CarouselImages.text.getData()}</div>
							</div>
						 </div>
					</#if>
				</#list>
			</#if>
		</div>

		<ol class="carousel-indicators">
			<#if CarouselImages.getSiblings()?has_content>
				<#list CarouselImages.getSiblings() as cur_CarouselImages>
					<#if cur_CarouselImages.getData()?? && cur_CarouselImages.getData() != "">
						<#if cur_CarouselImages?index == 0>
							<li class="active" data-target="#myCarousel" data-slide-to="${cur_CarouselImages?index}"></li>
							
							<#else>
							<li data-target="#myCarousel" data-slide-to="${cur_CarouselImages?index}"></li>
							
						</#if>
					</#if>
				</#list>
			</#if>
		</ol>
	</div>
</div>