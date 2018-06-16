<#--
Application display templates can be used to modify the look of a
specific application.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->
<style>
    .carousel-control-next {
        color: #FFF;
        font-size: 35px;
        font-weight: bold;
        right: 5%;
        position: absolute;
        top: 48%;
    }

    .carousel-control-next:visited, .carousel-control-prev:visited {
        color: #FFF;
    }
    
    .carousel-control-prev {
        color: #FFF;
        font-size: 35px;
        font-weight: bold;
        position: absolute;
        left: 5%;
        top: 48%;
    }
    
    .carousel-indicators li {
        border: 2px solid #FFF;
        height: 12px;
        width: 12px;
    }
    
    .carousel-main-container {
        height: auto;
        width: 100%;
    }
    
    .link-to-image {
        display: block;
        height: 600px;
        margin: auto;
        width: 100%;
    }

    .slides {
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        min-height: 600px;
        margin: auto;
        width: 100%;
    }
</style>
<#if entries?has_content>
    <div class="carousel-main-container" id="<@portlet.namespace />carousel">
        <#assign imageMimeTypes = propsUtil.getArray("dl.file.entry.preview.image.mime.types") />
        <#list entries as entry>
            <#if imageMimeTypes?seq_contains(entry.getMimeType())>
                <div class="slides" style="background-image: url(${dlUtil.getPreviewURL(entry, entry.getFileVersion(), themeDisplay, "")})">
                    <a href="${dlUtil.getPreviewURL(entry, entry.getFileVersion(), themeDisplay, "")}">
                        <span class="link-to-image" aria-hidden="true">
                        </span>
                    </a>
                     <a class="carousel-control-prev" onclick="arrowClick(-1)" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"><</span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" onclick="arrowClick(1)" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true">></span>
            <span class="sr-only">Next</span>
        </a>
                </div>
            </#if>
        </#list>
        <ul class="carousel-indicators">
            <#list entries as entry>
                <li class="carousel-indicator" onclick="carousel2(${entry?index})">
                </li>
            </#list>
        </ul>
    </div>
</#if>

<script>
    var slideIndex = 0;
    var changeSlide = setInterval(carousel, 5000, 1);
    carousel(1);
    
    function carousel(p) {
        console.log("carousel", p);
        var x = document.getElementsByClassName("slides");
        var y = document.getElementsByClassName("carousel-indicator");
        for (let i = 0; i < x.length; i++) {
            x[i].style.display = "none";
            y[i].style.backgroundColor = "transparent";
            y[i].id = i;
        };
        if (p == -1) {
        slideIndex--;
        };
        if (p == 1) {
            slideIndex++;
        };
        if (slideIndex < 1) {
            slideIndex = x.length;
        };
        if (slideIndex > x.length) {
            slideIndex = 1;
        };
        x[slideIndex-1].style.display = "block";
        y[slideIndex-1].style.backgroundColor = "#FFF";
    };
    
    function carousel2(e) {
        console.log("carousel2");
        clearInterval(changeSlide);
        slideIndex = e;
        carousel(1);
    };
    
    function arrowClick(e) {
        clearInterval(changeSlide);
        carousel(e);
    };

Liferay.on('beforeNavigate', function(event) {
    clearInterval(changeSlide);
    console.log("interval cleared");
});
    
</script>