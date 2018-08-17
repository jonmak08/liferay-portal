<div class="card-row">
  <#if Name.getSiblings()?has_content>
    <#list Name.getSiblings() as cur_Name>
      <div class="col-sm-12 col-md-6 col-lg-4">
        <div class="card card-type-asset">
	        <div class="aspect-ratio bg-checkered card-item-first">
		        <img alt="Picture of ${cur_Name.getData()}"class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="<#if cur_Name.Picture.getData()?? && cur_Name.Picture.getData() != "">${cur_Name.Picture.getData()}<#else>https://www.qualiscare.com/wp-content/uploads/2017/08/default-user.png</#if>">
	        </div>
	        <div class="card-body">
		        <div class="card-row">
			        <div class="autofit-col autofit-col-expand">
				        <div class="card-title text-truncate" title="Job Title">${cur_Name.JobTitle.getData()}</div>
				        <div class="card-subtitle text-truncate" title="Name">${cur_Name.getData()}</div>
			        </div>
		        </div>
	        </div>
        </div>
      </div>
    </#list>
  </#if>
</div>
