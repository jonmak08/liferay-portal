<section id="highlights">
    <div class="bg-${bgColor.getData()}">
        <div class="theme-container">
        	<div class="content-desc">
        	    <div class="row">
        			<a class="link-fade text-caps">${galleryHeader.getData()}</a>
        			<p>${galleryDescText.getData()}</p><br/>
        			<a class="link-button" href="#">${textLinkToImages.getData()}</a>
        		</div>
        		<div class="row">
        			<div class="gallery">
        				<ul>
        					<li>
                                <#if imgGallery1.getData()?? && imgGallery1.getData() != "">
                                    <a>${imgGallery1.getAttribute("alt")}</a>
                                    <img alt="${imgGallery1.getAttribute("alt")}" src="${imgGallery1.getData()}"/>
                                </#if>
        					</li>
        					<li>
                                <#if imgGallery2.getData()?? && imgGallery2.getData() != "">
                                    <a>${imgGallery2.getAttribute("alt")}</a>
                                    <img alt="${imgGallery2.getAttribute("alt")}" src="${imgGallery2.getData()}" />
                                </#if>
        					</li>
        					<li>
                                <#if imgGallery3.getData()?? && imgGallery3.getData() != "">
                                	<a>${imgGallery3.getAttribute("alt")}</a>
                                	<img alt="${imgGallery3.getAttribute("alt")}" src="${imgGallery3.getData()}" />
                                </#if>
        					</li>
        					<li>
                                <#if imgGallery4.getData()?? && imgGallery4.getData() != "">
                                	<a>${imgGallery4.getAttribute("alt")}</a>
                                	<img alt="${imgGallery4.getAttribute("alt")}" src="${imgGallery4.getData()}" />
                                </#if>
        					</li>
        				</ul>
        			</div>
        		</div>
        	</div>
        </div>
    </div>
</section>