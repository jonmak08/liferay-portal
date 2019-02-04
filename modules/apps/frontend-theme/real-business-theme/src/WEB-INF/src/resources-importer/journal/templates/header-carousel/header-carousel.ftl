<div class="row">
		<div class="col-md-12">
			<div class="carousel slide" id="carousel-502110">
				<ol class="carousel-indicators">
					<li data-slide-to="0" data-target="#carousel-502110">
					</li>
					<li data-slide-to="1" data-target="#carousel-502110">
					</li>
					<li data-slide-to="2" data-target="#carousel-502110" class="active">
					</li>
				</ol>
				<div class="carousel-inner">
					<div class="carousel-item active">
							<#if CarouselBG01.getData()?? && CarouselBG01.getData() != ""> <img alt="${CarouselBG01.getAttribute("alt")}" class="d-block img-cover" data-fileentryid="${CarouselBG01.getAttribute("fileEntryId")}" src="${CarouselBG01.getData()}" /> </#if> <div class="carousel-caption"> <#if Logo01.getData()?? && Logo01.getData() != ""> <img alt="${Logo01.getAttribute("alt")}" data-fileentryid="${Logo01.getAttribute("fileEntryId")}" src="${Logo01.getData()}" /> </#if> <h2>${Description01.getData()}</h2> </div> </div> <div class="carousel-item"> <#if CarouselBG021.getData()?? && CarouselBG021.getData() != ""> <img alt="${CarouselBG021.getAttribute("alt")}" class="d-block img-cover" data-fileentryid="${CarouselBG021.getAttribute("fileEntryId")}" src="${CarouselBG021.getData()}" /> </#if> <div class="carousel-caption"> <#if Logo02.getData()?? && Logo02.getData() != ""> <img alt="${Logo02.getAttribute("alt")}" data-fileentryid="${Logo02.getAttribute("fileEntryId")}" src="${Logo02.getData()}" /> </#if> <h2>${Description02.getData()}</h2> </div> </div> <div class="carousel-item"> <#if CarouselBG03.getData()?? && CarouselBG03.getData() != ""> <img alt="${CarouselBG03.getAttribute("alt")}" class="d-block img-cover" data-fileentryid="${CarouselBG03.getAttribute("fileEntryId")}" src="${CarouselBG03.getData()}" /> </#if> <div class="carousel-caption"> <#if Logo03.getData()?? && Logo03.getData() != ""> <img alt="${Logo03.getAttribute("alt")}" data-fileentryid="${Logo03.getAttribute("fileEntryId")}" src="${Logo03.getData()}" /> </#if> <h2>${Description03.getData()}</h2> </div> </div> </div> <a class="carousel-control-prev" href="#carousel-502110" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> <a class="carousel-control-next" href="#carousel-502110" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a> </div> </div> </div>