<footer id="footer" role="contentinfo">
    <div id="navbarContactWrapper" class="row mx-0 ${footer_background_theme}">
        <nav id="navbarFooter" class="col-12 col-md-12 pt-5 flex-row justify-content-center align-items-center ">
            <div id="socialMediaWrapper" class="col-12 col-md-4 text-center mx-auto mb-4 ">
                <h2 class="nav-heading ${footer_text_theme}">
                Let's Connect!
                </h2>

                <div id="socialMediaLinks">
                    <ul class="nav flex-row justify-content-center mx-auto">
                        <#if facebook_url?has_content>
                            <li class="mx-2">
                                <div id="facebook">
                                    <a class="${footer_text_theme}"
                                    href="${facebook_url}" 
                                    target="_blank"><span class="hide">Facebook</span>
                                    <@clay["icon"] symbol="social-facebook" />
                                    </a>
                                </div>
                            </li>
                        </#if>

                        <#if twitter_url?has_content>
                            <li class="mx-2">
                            <div id="twitter">
                            <a class="${footer_text_theme}" 
                            href="${twitter_url}" 
                            target="_blank"><span class="hide">Twitter</span>
                            <@clay["icon"] symbol="twitter" />
                            </a>
                            </div>
                            </li>
                        </#if>

                        <#if linkedin_url?has_content>
                            <li class="mx-2">
                                <div id="linked-in">
                                    <a class="${footer_text_theme}"
                                    href="{linkedin_url}" 
                                    target="_blank"><span class="hide">LinkedIn</span>
                                    <@clay["icon"] symbol="social-linkedin" />
                                    </a>
                                </div>
                            </li>
                        </#if>

                        <#if youtube_url?has_content>
                            <li class="mx-2">
                                <div id="youtube">
                                    <a class="${footer_text_theme}"
                                    href="${youtube_url}" 
                                    target="_blank"><span class="hide">YouTube</span>
                                    <@clay["icon"] symbol="video" />
                                    </a>
                                </div>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="text-center pb-2 col-12 col-md-4 mx-auto mb-4">
            <img alt="Company Logo" height="${site_logo_height}" class="mb-2" src="${site_logo}" />
        </div>
    </div>
</footer>