<#assign elements = currentTestCaseElement.elements()>

<#list elements as element>
	<#assign lineNumber = element.attributeValue("line-number")>

	<li id="${testCaseNameStack.peek()?uncap_first}TestCase${lineNumber}">
		<#if element.getName() == "execute">
			<#if element.attributeValue("action")??>
				<#assign displayElement = element>

				<#include "element_whole_html.ftl">
			<#elseif element.attributeValue("macro")??>
				<#assign macroElement = element>

				<#include "macro_element_html.ftl">
			<#elseif element.attributeValue("test-case")??>
				<#assign currentTestCaseElement = element>

				<#include "extended_test_case_element_html.ftl">
			</#if>
		<#elseif element.getName() == "var">
			<#assign displayElement = element>

			<#include "element_whole_html.ftl">
		</#if>
	</li>
</#list>