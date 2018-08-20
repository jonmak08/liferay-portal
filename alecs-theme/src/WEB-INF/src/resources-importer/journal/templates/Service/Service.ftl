<style>
  .col-md-8 {
    margin: auto;
    vertical-align: middle;
  }

  .service-row {
    background: ${BackgroundColor.getData()};
  }
</style>

<div class="row service-row" data-aos-delay="${FadeInDelay.getData()}" data-aos="fade-<#if ServiceImage.ImageColumn.getData() == 'right'>left<#else>right</#if>">
  <#if ServiceImage.ImageColumn.getData() !='right'>
    <div class="col-sm-12 col-md-4">
      <#if ServiceImage.getData()?? && ServiceImage.getData() !=""> <img alt="${Title.getData()} Image" src="${ServiceImage.getData()}" /> </#if>
    </div>
  </#if>
  <div class="col-sm-12 col-md-8">
    <h1 sytle="color: ${Title.TitleColor.getData()}">${Title.getData()}</h1>
    <hr style="border-color: #FFF; width: 75%" />
    <p>${ServiceDescription.getData()}</p>
  </div>
  <#if ServiceImage.ImageColumn.getData()=='right'>
    <div class="col-sm-12 col-md-4">
      <#if ServiceImage.getData()?? && ServiceImage.getData() !=""> <img alt="${Title.getData()} Image" src="${ServiceImage.getData()}" /> </#if>
    </div>
  </#if>
</div>