<div class="about-wrapper">
  <div class="about-flex">
  
    <div class="about-us">
      <h2 class=".h2">${Textdya3.getData()}</h2>
      <p>${TextBoxcp7x.getData()}</p>
    </div>
  
    <div class="about-us"><#if Image58ad.getData()?? && Image58ad.getData() != ""> <img alt="${Image58ad.getAttribute("alt")}" data-fileentryid="${Image58ad.getAttribute("fileEntryId")}" src="${Image58ad.getData()}" /> </#if>
    </div>
  
  </div>
</div>