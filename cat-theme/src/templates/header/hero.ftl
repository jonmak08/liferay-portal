<#if change_hero_img?has_content>
    <#if my_background_image_color>
        <header class="dark--background hero" id="hero">
            <div class="hero--img parallax" style="background-image: url(${change_hero_img})">
            </div>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-2 d-md-block d-none hero-carousal"></div>

                    <div class="col-11 col-md-8 hero--wrapper offset-1 offset-md-0">
                        
                        <#if my_title?has_content>
                            <h1 class="hero--title">
                                ${my_title}
                            </h1>
                        </#if>

                        <#if my_caption?has_content>
                            <div class="paragraph hero--description">${my_caption?left_pad(0)[0..*500]}
                            </div>
                        </#if>
                        
                        <#if main_button>
                            <div class="btn--main">
                                <a href=${main_button_link}>
                                    <div class="btn--main--arrow">&#8658;</div>
                                    <span class="btn--main--text">View Case</span>
                                </a>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </header>
    <#else>
        <header class="dark--background hero" id="hero">
            <div class="hero--img parallax" style="background-image: url(${change_hero_img})">
            </div>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-2 d-md-block d-none hero-carousal"></div>

                    <div class="col-11 col-md-8 hero--wrapper offset-1 offset-md-0" style="color: #0009">
                        
                        <#if my_title?has_content>
                            <h1 class="hero--title">
                                ${my_title}
                            </h1>
                        </#if>

                        <#if my_caption?has_content>
                            <div class="paragraph hero--description">${my_caption?left_pad(0)[0..*500]}
                            </div>
                        </#if>
                        
                        <#if main_button>
                            <div class="btn--main" style="border: 0.5px solid #0009">
                                <a href=${main_button_link} style="color: #0009">
                                    <div class="btn--main--arrow" style="background-color: #0009; color: #fff">&#8658;</div>
                                    <span class="btn--main--text">View Case</span>
                                </a>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </header>
    </#if>
<#else>
    <header class="dark--background hero" id="hero">
        <div class="hero--img parallax"></div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-2 d-md-block d-none hero-carousal"></div>

                <div class="col-11 col-md-8 hero--wrapper offset-1 offset-md-0">
                    
                    <#if my_title?has_content>
                        <h1 class="hero--title">
                            ${my_title}
                        </h1>
                    </#if>

                    <#if my_caption?has_content>
                        <div class="paragraph hero--description">${my_caption?left_pad(0)[0..*500]}
                        </div>
                    </#if>
                    
                    <#if main_button>
                        <div class="btn--main">
                            <a href=${main_button_link}>
                                <div class="btn--main--arrow">&#8658;</div>
                                <span class="btn--main--text">View Case</span>
                            </a>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </header>
</#if>