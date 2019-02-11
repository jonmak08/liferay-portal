<div class="row clients container-fluid">
	<div class="col-md-8 client-spacing">

<#if SponsorImage.getSiblings()?has_content> <#list SponsorImage.getSiblings() as cur_SponsorImage> <#if cur_SponsorImage.getData()?? && cur_SponsorImage.getData() != "">
	<div class="col-md-3">
		<img class="client-item-image" alt="buddypress" src="${cur_SponsorImage.getData()}">
	</div> </#if> </#list> </#if>

	</div>
</div>