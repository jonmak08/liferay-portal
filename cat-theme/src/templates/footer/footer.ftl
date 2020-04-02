<footer class="footer" id="footer">
    <div class="footer--top">
        <#if show_number>
            <a class="footer--phone">${my_phone_text}</a>
        </#if>
        <#if show_email>
            <a href="${my_email_text}"class="footer--email">${my_email_text}</a>
        </#if>
    </div>

    <div class="footer--bottom">
        <#if show_credits>
            <div class="copyright">${my_credits_text}</div>
        </#if>
        <#if show_social>
            <ul>
                <li>
                <#if my_facebook_url?has_content>
                    <a href=${my_facebook_url} target="_blank">Facebook</a>
                </#if>

                <#if my_instagram_url?has_content>
                    <a href=${my_instagram_url} target="_blank">Instagram</a>
                </#if>
                <#if my_linkedin_url?has_content>
                    <a href=${my_linkedin_url} target="_blank">LinkedIn</a>
                </#if>
                <#if my_twitter_url?has_content>
                    <a href=${my_twitter_url} target="_blank">Twitter</a>
                </#if>
                </li>
            </ul>
        </#if>
    </div>
</footer>