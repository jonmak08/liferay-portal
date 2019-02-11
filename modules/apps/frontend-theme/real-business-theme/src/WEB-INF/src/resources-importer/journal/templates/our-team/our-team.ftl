<div class="parallax team-wrapper">
	<h2>${OurTeamHeader.getData()}</h2>

	<div class="team-flex">
		<div class="team-member">

			<#if TeamMemberImage01.getData()?? && TeamMemberImage01.getData() != ""> <img alt="${TeamMemberImage01.getAttribute("alt")}" data-fileentryid="${TeamMemberImage01.getAttribute("fileEntryId")}" src="${TeamMemberImage01.getData()}" /> </#if>

			<p>${Position01.getData()}</p>

			<h3>${Name01.getData()}</h3>

		</div>

		<div class="team-member">

			<#if TeamMemberImage02.getData()?? && TeamMemberImage02.getData() != ""> <img alt="${TeamMemberImage02.getAttribute("alt")}" data-fileentryid="${TeamMemberImage02.getAttribute("fileEntryId")}" src="${TeamMemberImage02.getData()}" /> </#if>

			<p>${Position02.getData()}</p>

			<h3>${Name02.getData()}</h3>

		</div>

		<div class="team-member">

			<#if TeamMemberImage03.getData()?? && TeamMemberImage03.getData() != ""> <img alt="${TeamMemberImage03.getAttribute("alt")}" data-fileentryid="${TeamMemberImage03.getAttribute("fileEntryId")}" src="${TeamMemberImage03.getData()}" /> </#if>

			<p>${Position03.getData()}</p>

			<h3>${Name03.getData()}</h3>

		</div>

		<div class="team-member">

			<#if TeamMemberImage04.getData()?? && TeamMemberImage04.getData() != ""> <img alt="${TeamMemberImage04.getAttribute("alt")}" data-fileentryid="${TeamMemberImage04.getAttribute("fileEntryId")}" src="${TeamMemberImage04.getData()}" /> </#if>

			<p>${Position04.getData()}</p>

			<h3>${Name04.getData()}</h3>

		</div>
	</div>
</div>