<div class="fine-print">
	<div class="powered-by">
		Powered By Liferay
	</div>
	<div class="copyright">
		<#if copyright.getData()?? && copyright.getData() != "">
			© ${copyright.getData()} 
		</#if>
		<#if company_name.getData()?? && company_name.getData() != "">
			${company_name.getData()}  All Rights Reserved.
		</#if>
	</div>
</div>
