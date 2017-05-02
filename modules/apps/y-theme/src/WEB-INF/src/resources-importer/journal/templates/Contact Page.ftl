<#--
Web content templates are used to lay out the fields defined in a web
content structure.
<p>${addressline1.getData()}<br/>${addressline2.getData()}<br/>${email.getData()}<br/>${phone.getData()}</p>
Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->
<div class="contact-flexbox-container">
    <div class="contact-page-left multi-column-container-left">
        <div class="paragraph-div">
            <h4>Office Location</h4>
        </div>
        <div>
            <h5>Address</h5>
        </div>
        <div class="paragraph-div">
            ${addressline1.getData()}<br/>
            ${addressline2.getData()}
        </div>
        <div>
            <h5>Email</h5>
        </div>
        <div class="paragraph-div">
            ${email.getData()}
        </div>
        <div>
            <h5>Phone</h5>
        </div>
        <div class="paragraph-div">
            Ph:&nbsp; ${phone.getData()}<br/>
            <#if fax.getData()?? && fax.getData() != "">
            Fax: ${fax.getData()}
            </#if>
        </div>
    </div>
    <div class="contact-page-right multi-column-container-right">
        <#if contactsidetitle.getData()?? && contactsidetitle.getData() != "">
            <div class="paragraph-div">
                <h4>Have you a question?</h4>
            </div>
        </#if>
        <#if contactsideparagraph.getData()?? && contactsideparagraph.getData() != "">
            <div class="paragraph-div">
                Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus. Nam libero tempore
            </div>
        </#if>
    <form action="mailto:${email.getData()}">
    <div class="paragraph-div justified-multi-input-row">
        <div size="30" class="input-in-line-container"><input class="input-in-line" type="text" name="yourName" value="Your name"/></div>
        <div size="30" class="input-in-line-container"><input class="input-in-line" type="text" name="yourEmail" value="Your email"/></div>
        <div size="30" class="input-in-line-container"><input class="input-in-line" type="text" name="yourSubject" value="Your subject"/></div>
    </div>
    <div class="paragraph-div full-width-container">
        <textarea class="contact-text" rows="9">Your message</textarea>
    </div>
    <div>
        <input class="disguised-submit-button" type="submit" value="Send Message"/>
    </div>
    </form>
    </div>
</div>
