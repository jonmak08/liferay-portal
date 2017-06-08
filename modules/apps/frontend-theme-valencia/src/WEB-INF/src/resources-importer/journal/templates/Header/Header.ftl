<#assign themePath = getterUtil.getString(request['theme-display']['path-theme-images']) />
<#if (Selectj0q7.getData()) == "home">
	<#assign selectedImage = "home.jpg" />
<#elseif (Selectj0q7.getData()) == "about">
	<#assign selectedImage = "about.jpg" />
<#elseif (Selectj0q7.getData()) == "blogs">
	<#assign selectedImage = "blogs.jpg" />
<#elseif (Selectj0q7.getData()) == "images">
	<#assign selectedImage = "images.jpg" />
<#elseif (Selectj0q7.getData()) == "videos">
	<#assign selectedImage = "videos.jpg" />
<#elseif (Selectj0q7.getData()) == "contact">
	<#assign selectedImage = "contact.jpg" />
<#else>
    <#assign selectedImage = "home.jpg" />
</#if>
<div class="header-filter header-image" style="background-image: url('${themePath}/${selectedImage}');">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center">

                <#if (PageHeadline.getData())??>
                    <h1 class="page-headline">${PageHeadline.getData()}</h1>
                </#if>

                <#if (PageTagline.getData())??>
                    <h2 class="page-tagline">${PageTagline.getData()}</h2>
                </#if>

            </div>
        </div>
    </div>
</div>