<div class="row">
	<div class="col-md-12">
		<div class="carousel slide" id="carousel-502110">

			<ol class="carousel-indicators">
				<#list BG.getSiblings() as cur_img>

                   <li class="${(cur_img?counter == 1)?then('active', '')}" data-slide-to="${(cur_img?counter == 1)?then(0, (cur_img?counter - 1))}" data-target='carousel-<@portlet.namespace />'></li>

                </#list>
			</ol>

			<div class="carousel-inner">

			<#if BG.getSiblings()?has_content>
<#list BG.getSiblings() as cur_BG> <#if cur_BG.getData()?? && cur_BG.getData() != "">

				<div class="carousel-item ${(cur_BG?counter == 1)?then('active', '')}">

						<img alt="${cur_BG.getAttribute("alt")}" class="d-block img-cover" data-fileentryid="${cur_BG.getAttribute("fileEntryId")}" src="${cur_BG.getData()}">

						<div class="carousel-caption">

							<#if cur_BG.Logo.getData()?? && cur_BG.Logo.getData() != ""> <img alt="${cur_BG.Logo.getAttribute("alt")}" data-fileentryid="${cur_BG.Logo.getAttribute("fileEntryId")}" src="${cur_BG.Logo.getData()}" /> </#if>
							<h2>${cur_BG.TagLine.getData()}</h2>
					</div>
				</div>

</#if> </#list> </#if>

			</div> <a class="carousel-control-prev" href="#carousel-502110" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> <a class="carousel-control-next" href="#carousel-502110" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
		</div>
	</div>
</div>