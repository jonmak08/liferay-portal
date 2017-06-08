<section id="highlights">
	<div class="bg-${bg_color.getData()}">
		<div class="theme-container">
			<div class="content-desc">
				<div class="row">

					<#if gallery_header.getData()?? && gallery_header.getData() != "">

						<a class="link-fade text-caps" href="#">${gallery_header.getData()}</a>

					</#if>
					
					<#if description.getData()?? && description.getData() != "">

						<p>${description.getData()}</p>

					</#if>
					
					<#if link.getData()?? && link.getData() != "">

						<a class="link-button" href="#">${link.getData()}</a>

					</#if>

				</div>

				<div class="row">
					<div class="gallery">
						<ul>

							<#if img_gallery_1.getData()?? && img_gallery_1.getData() != "">
								
								<li>
									<a href="#">${img_gallery_1.getAttribute("alt")}</a>
									
									<img alt="${img_gallery_1.getAttribute("alt")}" src="${img_gallery_1.getData()}" />
								</li>

							</#if>
							
							<#if img_gallery_2.getData()?? && img_gallery_2.getData() != "">
								
								<li>
									<a href="#">${img_gallery_2.getAttribute("alt")}</a>
									
									<img alt="${img_gallery_2.getAttribute("alt")}" src="${img_gallery_2.getData()}" />
								</li>

							</#if>
							
							<#if img_gallery_3.getData()?? && img_gallery_3.getData() != "">
								
								<li>
									<a href="#">${img_gallery_3.getAttribute("alt")}</a>
									
									<img alt="${img_gallery_3.getAttribute("alt")}" src="${img_gallery_3.getData()}" />
								</li>

							</#if>
							
							<#if img_gallery_4.getData()?? && img_gallery_4.getData() != "">
								
								<li>
									<a href="#">${img_gallery_4.getAttribute("alt")}</a>
									
									<img alt="${img_gallery_4.getAttribute("alt")}" src="${img_gallery_4.getData()}" />
								</li>

							</#if>

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>