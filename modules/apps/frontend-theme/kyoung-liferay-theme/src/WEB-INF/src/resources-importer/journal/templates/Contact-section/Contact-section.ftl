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
                        <p>${ContactSummary.getData()}</p>
                    </div>

                    <div class="contact-content">
                        <div class="row">
                            <div class="col-md-7">
                                <div class="contact-left">
                                    <div id="form-messages"></div>
                                    <form action="mailer.php" class="contact-form" id="ajax-contact" method="post">
                                        <div class="form-group">
                                            <input class="form-control" id="name" name="name" placeholder="Name" type="text" required>
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" id="email" name="email" placeholder="Enter Email" type="email" required>
                                        </div>
                                        <div class="form-group">
                                            <textarea class="form-control" id="message" name="message" placeholder="Message" required></textarea>
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
                                <div class="contact-right">
                                    <div class="contact-right-single">
                                        <div class="icon"><span class="fa fa-map-marker"></span></div>
                                        <p class="office-heading">                            
                                            <@liferay.language key="office-location" />
                                        </p>
                                        <p>${OfficeLocation.getData()}</p>
                                    </div>

                                    <div class="contact-right-single">
                                        <div class="icon"><span class="fa fa-phone"></span></div>
                                        <p class="office-heading">                            
                                            <@liferay.language key="phone-number" />
                                        </p>
                                        <#if PhoneNumber.getSiblings()?has_content>
                                            <#list PhoneNumber.getSiblings() as cur_PhoneNumber>
                                                <p>${cur_PhoneNumber.getData()}</p>
                                            </#list>
                                        </#if>
                                    </div>

                                    <div class="contact-right-single">
                                        <div class="icon"><span class="fa fa-envelope"></span></div>
                                        <p class="office-heading">
                                            <@liferay.language key="email-address" />
                                        </p>
                                        <#if EmailAddress.getSiblings()?has_content>
                                            <#list EmailAddress.getSiblings() as cur_EmailAddress>
                                                <p>${cur_EmailAddress.getData()}</p>
                                            </#list>
                                        </#if>
                                    </div>

                                    <div class="contact-right-single">
                                        <div class="social-media">
                                            <#if SocialMediaLogo.getSiblings()?has_content>
                                                <#list SocialMediaLogo.getSiblings() as cur_SocialMediaLogo> <a href="${cur_SocialMediaLogo.SocialMediaURL.getData()}"><span class="fa ${cur_SocialMediaLogo.getData()}"></span></a> </#list>
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