<section id="contact">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="contact-area contact-form-logo-area">
                    <div class="contact-content-liferay">
                        <div class="row">
                            <div class="col-md-7">
                                <div class="contact-form-area">
                                    <div class="contact-line-single">
                                        <div class="icon">
                                            <span class="fa fa-map-marker"></span>
                                        </div>

                                        <div class="office-heading">
                                            <@liferay.language key="office-location" />
                                        </div>

                                        <div class="office-data">
                                            ${OfficeLocation.getData()}
                                        </div>
                                    </div>

                                    <div class="contact-line-single">
                                        <div class="icon">
                                            <span class="fa fa-phone"></span>
                                        </div>

                                        <div class="office-heading">
                                            <@liferay.language key="phone-number" />
                                        </div>

                                        <#if PhoneNumber.getSiblings()?has_content>
                                            <#list PhoneNumber.getSiblings() as cur_PhoneNumber>
                                                <div class="office-data">
                                                    ${cur_PhoneNumber.getData()}
                                                </div>
                                            </#list>
                                        </#if>
                                    </div>

                                    <div class="contact-line-single">
                                        <div class="icon">
                                            <span class="fa fa-envelope"></span>
                                        </div>

                                        <div class="office-heading">
                                            <@liferay.language key="email-address" />
                                        </div>

                                        <#if EmailAddress.getSiblings()?has_content>
                                            <#list EmailAddress.getSiblings() as cur_EmailAddress>
                                                <div class="office-data">
                                                    ${cur_EmailAddress.getData()}
                                                </div>
                                            </#list>
                                        </#if>
                                    </div>

                                    <div class="contact-line-single">
                                        <div class="social-media">
                                            <#if SocialMediaLogo.getSiblings()?has_content>
                                                <#list SocialMediaLogo.getSiblings() as cur_SocialMediaLogo>
                                                    <a href="${cur_SocialMediaLogo.SocialMediaURL.getData()}"><span class="fa ${cur_SocialMediaLogo.getData()}"></span></a>
                                                </#list>
                                            </#if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>