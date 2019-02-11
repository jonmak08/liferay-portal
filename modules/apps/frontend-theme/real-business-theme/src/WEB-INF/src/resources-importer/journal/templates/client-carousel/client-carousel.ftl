<div class="client-wrapper row">
	<div class="col-md-12">
		<div class="carousel slide" id="carousel-828042">
			<ol class="carousel-indicators-02">
				<#list Heading.getSiblings() as cur_img>
					<li class="${(cur_img?counter == 1)?then('active', '')}" data-slide-to="${(cur_img?counter == 1)?then(0, (cur_img?counter - 1))}" data-target='carousel-<@portlet.namespace />'></li>
				</#list>
			</ol>

			<div class="carousel-inner parallax">

			<#if Heading.getSiblings()?has_content>
				<#list Heading.getSiblings() as cur_Heading>
					<#if cur_Heading.getData()?? && cur_Heading.getData() != "">

						<div class="carousel-item client-carousel ${(cur_Heading?counter == 1)?then('active', '')}">
							<div class="carousel-caption">
								<h2>${cur_Heading.getData()}</h2>

								<#if cur_Heading.ClientImage.getData()?? && cur_Heading.ClientImage.getData() != "">
									<img alt="${cur_Heading.ClientImage.getAttribute("alt")}" data-fileentryid="${cur_Heading.ClientImage.getAttribute("fileEntryId")}" src="${cur_Heading.ClientImage.getData()}" />
								</#if>

								<h3 class="client-name">${cur_Heading.ClientName.getData()}</h3>

								<hr />

								<p class="client-quote">${cur_Heading.ClientQuote.getData()}</p>

								<ul class="carousel-ul">
									<li class="carousel-social">
										<a class="fa-facebook-f fab" href="#"></a>
									</li>
									<li class="carousel-social">
										<a class="fa-twitter fab" href="#"></a>
									</li>
									<li class="carousel-social">
										<a class="fa-pinterest-p fab" href="#"></a>
									</li>
								</ul>
							</div>
						</div>
					</#if>
				</#list>
			</#if>

			<a class="carousel-control-prev" href="#carousel-828042" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only"><@liferay.language key="previous" /></span></a> <a class="carousel-control-next" href="#carousel-828042" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only"><@liferay.language key="next" /></span></a>

		</div>
	</div>
</div>