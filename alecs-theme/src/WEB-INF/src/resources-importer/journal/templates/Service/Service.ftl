<style>
    .service-row {
        background: ${BackgroundColor.getData()};
        border-radius: 15px;
        color: #FFF;
        margin: 0px 15px;
        padding: 20px;
        text-align: center;
    }
    .col-md-8 {
        margin: auto;
        vertical-align: middle;
    }
</style>
<div class="row service-row" data-aos-delay="${FadeInDelay.getData()}" data-aos="fade-<#if Image24jl.ImageColumn.getData() == 'right'>left<#else>right</#if>">
  <#if Image24jl.ImageColumn.getData() != 'right'>
    <div class="col-sm-12 col-md-4">
      <#if Image24jl.getData()?? && Image24jl.getData() != "">
       <img alt="${Image24jl.getAttribute("alt")}" data-fileentryid="${Image24jl.getAttribute("fileEntryId")}" src="${Image24jl.getData()}" />
      </#if>
    </div>
  </#if>
<div class="col-sm-12 col-md-8">
  <h1 sytle="color: ${Title.TitleColor.getData()}">${Title.getData()}</h1>
  <hr style="border-color: #FFF; width: 75%"/>
  <p>${TextBoxfdx8.getData()}</p>
</div>
<#if Image24jl.ImageColumn.getData() == 'right'>
  <div class="col-sm-12 col-md-4">
    <#if Image24jl.getData()?? && Image24jl.getData() != "">
      <img alt="${Image24jl.getAttribute("alt")}" data-fileentryid="${Image24jl.getAttribute("fileEntryId")}" src="${Image24jl.getData()}" />
    </#if>
  </div>
</#if>
</div>
