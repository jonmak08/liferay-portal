<section id="highlights">
	<div class="bg-${bg_Color.getData()}">
		<div class="theme-container">
			<div class="content-desc">
				<div class="row">

					<#if Gallery_Header.getData()?? Gallery_Header.getData() !== "">

						<a class="link-fade text-caps" href="#">${Gallery_Header.getData()}</a>

					</#if>
					
					<#if Description.getData()?? Description.getData() !== "">

						<p>${Description.getData()}</p>

					</#if>
					
					<#if Link.getData()?? Link.getData() !=="">

						<a class="link-button" href="#">${Link.getData()}</a>

					</#if>

				</div>

				<div class="row">
					<div class="gallery">
						<ul>

							<#if img_Gallery_1.getData()?? && img_Gallery_1.getData() != "">
								
								<li>
									<a href="#">${img_Gallery_1.getAttribute("alt")}</a>
									
									<img alt="${img_Gallery_1.getAttribute("alt")}" src="${img_Gallery_1.getData()}" />
								</li>

							</#if>
							
							<#if img_Gallery_2.getData()?? && img_Gallery_2.getData() != "">
								
								<li>
									<a href="#">${img_Gallery_2.getAttribute("alt")}</a>
									
									<img alt="${img_Gallery_2.getAttribute("alt")}" src="${img_Gallery_2.getData()}" />
								</li>

							</#if>
							
							<#if img_Gallery_3.getData()?? && img_Gallery_3.getData() != "">
								
								<li>
									<a href="#">${img_Gallery_3.getAttribute("alt")}</a>
									
									<img alt="${img_Gallery_3.getAttribute("alt")}" src="${img_Gallery_3.getData()}" />
								</li>

							</#if>
							
							<#if img_Gallery_4.getData()?? && img_Gallery_4.getData() != "">
								
								<li>
									<a href="#">${img_Gallery_4.getAttribute("alt")}</a>
									
									<img alt="${img_Gallery_4.getAttribute("alt")}" src="${img_Gallery_4.getData()}" />
								</li>

							</#if>

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>