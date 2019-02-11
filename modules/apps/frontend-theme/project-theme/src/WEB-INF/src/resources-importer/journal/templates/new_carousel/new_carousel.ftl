<div class="row cover-carousel">
		<div class="col-md-12">
     <div class="carousel slide" id="carousel-376754">
				<ol class="carousel-indicators">
                <#list CarouselImage.getSiblings() as cur_img>

                   <li class="${(cur_img?counter == 1)?then('active', '')}" data-slide-to="${(cur_img?counter == 1)?then(0, (cur_img?counter - 1))}" data-target='carousel-<@portlet.namespace />'></li>

                </#list>
				</ol>

				<div class="carousel-inner">

<#if CarouselImage.getSiblings()?has_content> 
    <#list CarouselImage.getSiblings() as cur_CarouselImage> 
        
				<div class="carousel-item ${(cur_CarouselImage?counter == 1)?then('active', '')}">
					<img alt="${cur_CarouselImage.getAttribute("alt")}" class="cover-img" data-fileentryid="${cur_CarouselImage.getAttribute("fileEntryId")}" src="${cur_CarouselImage.getData()}" /> 
		
					<div class="carousel-caption">

					<#if cur_CarouselImage.getData()?? && cur_CarouselImage.getData() != ""> 
							<div class="container cover-jumbo">
							<h4>
								${cur_CarouselImage.CarouselHeading.getData()}
							</h4>
							<p>
								${cur_CarouselImage.CarouselDescription.getData()}
							</p>
							<button type="button" class="btn"> ${cur_CarouselImage.CarouselButtonText.getData()} </button>
							</div>
					</div>
				</div>
        			</#if>
    </#list> 
</#if>
			<a class="carousel-control-prev" href="#carousel-376754" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carousel-376754" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
		</div>
	</div>
<div>