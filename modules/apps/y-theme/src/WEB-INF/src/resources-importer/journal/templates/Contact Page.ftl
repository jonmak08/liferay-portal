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
    <div class="paragraph-div">
        <h4>Have you a question?</h4>
    </div>
    <div class="paragraph-div">
        Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus. Nam libero tempore
    </div>
    <div class="paragraph-div justified-multi-input-row">
        <div><input size="30" type="text" name="yourName" value="Your name"/></div>
        <div><input size="30" type="text" name="yourEmail" value="Your email"/></div>
        <div><input size="30" type="text" name="yourSubject" value="Your subject"/></div>
    </div>
    <div class="paragraph-div">
        <textarea rows="9" cols="100">Your message</textarea>
    </div>
    <div>
        <input class="disguised-submit-button" type="submit" value="Send Message"/>
    </div>
    </div>
</div>
