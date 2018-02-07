 <ul>

	<#if facebook_link?? && facebook_link?contains("facebook.com")>
		<li>
			<div id="facebook">
				<a class="icon-facebook" href="${facebook_link}" target="_blank"></a>
			</div>
		</li>
	</#if>

	<#if twitter_link?? && twitter_link?contains("twitter.com")>
		<li>
			<div id="twitter">
				<a class="icon-twitter" href="${twitter_link}" target="_blank"></a>
			</div>
		</li>
	</#if>

	<#if linkedin_link?? && linkedin_link?contains("linkedin.com")>
		<li>
			<div id="linked-in">
				<a class="icon-linkedin" href="${linkedin_link}" target="_blank"></a>
			</div>
		</li>
	</#if>

	<#if youtube_link?? && youtube_link?contains("youtube.com")>
		<li>
			<div id="youtube">
				<a class="icon-youtube" href="${youtube_link}" target="_blank"></a>
			</div>
		</li>
	</#if>

	<#if google_plus_link?? && google_plus_link?contains("plus.google.com")>
		<li>
			<div id="google-plus">
				<a class="icon-google-plus" href="${google_plus_link}" target="_blank"></a>
			</div>
		</li>
	</#if>

</ul>