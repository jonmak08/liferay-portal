<#if Name.getSiblings()?has_content>
  <div class="card-row">
    <#list Name.getSiblings() as cur_Name>
      <div class="col-sm-12 col-md-6 col-lg-4">
        <div class="card card-type-asset">
	        <div class="aspect-ratio bg-checkered card-item-first">
		        <img alt="Picture of ${cur_Name.getData()}"class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="<#if cur_Name.Picture.getData()?? && cur_Name.Picture.getData() != "">${cur_Name.Picture.getData()}<#else>/o/alecs-theme/images/default.png</#if>">
	        </div>
	        <div class="card-body">
		        <div class="card-row">
			        <div class="autofit-col autofit-col-expand">
				        <div class="card-title text-truncate" title="${Name.JobTitle.getData()}">${cur_Name.JobTitle.getData()}</div>
				        <div class="card-subtitle text-truncate" title="${cur_Name.getData()}">${cur_Name.getData()}</div>
			        </div>
		        </div>
	        </div>
        </div>
      </div>
    </#list>
  </div>
</#if>