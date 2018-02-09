 <ul>
    <#if facebook_link??>
        <#if facebook_link?starts_with("https://www.facebook.com")>
            <li>
                <div id="facebook">
                    <a class="icon-facebook" href="${facebook_link}" target="_blank"></a>
                </div>
            </li>
        </#if>
    </#if>
    <#if twitter_link??>
        <#if twitter_link?starts_with("https://twitter.com")>
            <li>
                <div id="twitter">
                    <a class="icon-twitter" href="${twitter_link}" target="_blank"></a>
                </div>
            </li>
        </#if>
    </#if>
    <#if linkedin_link??>
        <#if linkedin_link?starts_with("https://www.linkedin.com")>
            <li>
                <div id="linked-in">
                    <a class="icon-linkedin" href="${linkedin_link}" target="_blank"></a>
                </div>
            </li>
        </#if>
    </#if>
    <#if youtube_link??>
        <#if youtube_link?starts_with("https://www.youtube.com")>
            <li>
                <div id="youtube">
                    <a class="icon-youtube" href="${youtube_link}" target="_blank"></a>
                </div>
            </li>
        </#if>
    </#if>
    <#if google_plus_link??>
        <#if google_plus_link?starts_with("https://plus.google.com")>
            <li>
                <div id="google-plus">
                    <a class="icon-google-plus" href="${google_plus_link}" target="_blank"></a>
                </div>
            </li>
        </#if>
    </#if>
</ul>