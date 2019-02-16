<section id="contact">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="contact-area">
                    <div class="title-area">
                        <h2 class="heading-title">
                            <@liferay.language key="get-in-touch" />
                        </h2>
                        <span class="title-dot"></span>

                        <div class="section-summary">
                            ${ContactSummary.getData()}
                        </div>
                    </div>

                    <div class="contact-content">
                        <div class="row">
                            <div class="col-md-7">
                                <div class="contact-form-area">
                                    <div id="formMessages">
                                    </div>
                                    <form action="#" class="contact-form" id="ajaxContact" method="post">
                                        <div class="form-group">
                                            <input class="form-control" id="name" name="name" placeholder='<@liferay.language key="name" />' type="text" required>
                                        </div>

                                        <div class="form-group">
                                            <input class="form-control" id="email" name="email" placeholder='<@liferay.language key="enter-email" />' type="email" required>
                                        </div>

                                        <div class="form-group">
                                            <textarea class="form-control" id="message" name="message" placeholder='<@liferay.language key="message" />' required></textarea>
                                        </div>

                                        <button class="send-msg-btn" type="submit">
                                            <span>
                                                <@liferay.language key="submit" />
                                            </span>
                                        </button>
                                    </form>
                                </div>
                            </div>

                            <div class="col-md-5">
                                <div class="contact-line">
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