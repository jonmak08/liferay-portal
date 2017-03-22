<div class="overall-team-wrapper">
    <div class="team-wrapper">
        <#if Member1.getData()?? && Member1.getData() != "">
        	<img alt="${Member1.getAttribute("alt")}" src="${Member1.getData()}" />
        	
        	<div class="layer-top">
                <div class="team-text"> ${Member1.Member1Text.getData()} </div>
            </div>
        </#if>
    </div>
    
    <div class="team-wrapper">
        <#if Member2.getData()?? && Member2.getData() != "">
        	<img alt="${Member2.getAttribute("alt")}" src="${Member2.getData()}" />
        	
        	<div class="layer-top">
                <div class="team-text"> ${Member2.Member2Text.getData()} </div>
            </div>
        </#if>
    </div>
    
    <div class="team-wrapper">
        <#if Member3.getData()?? && Member3.getData() != "">
        	<img alt="${Member3.getAttribute("alt")}" src="${Member3.getData()}" />
        	
        	<div class="layer-top">
                <div class="team-text"> ${Member3.Member3Text.getData()} </div>
            </div>
        </#if>
    </div>
    
    <div class="team-wrapper">
        <#if Member4.getData()?? && Member4.getData() != "">
        	<img alt="${Member4.getAttribute("alt")}" src="${Member4.getData()}" />
        	
        	<div class="layer-top">
                <div class="team-text"> ${Member4.Member4Text.getData()} </div>
            </div>
        </#if>
    </div>
    
    <div class="team-wrapper">
        <#if Member5.getData()?? && Member5.getData() != "">
        	<img alt="${Member5.getAttribute("alt")}" src="${Member5.getData()}" />
        	
        	<div class="layer-top">
                <div class="team-text"> ${Member4.Member4Text.getData()} </div>
            </div>
        </#if>
    </div>
</div>