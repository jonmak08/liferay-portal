/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portalweb.plugins.kaleo.workflow.workflowtask.resubmittaskwebcontentdetails;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class ResubmitTaskWebContentDetailsTest extends BaseTestCase {
	public void testResubmitTaskWebContentDetails() throws Exception {
		selenium.open("/web/guest/home/");

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (selenium.isElementPresent("link=Control Panel")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("link=Control Panel",
			RuntimeVariables.replace("Control Panel"));
		selenium.waitForPageToLoad("30000");
		selenium.clickAt("link=Workflow", RuntimeVariables.replace("Workflow"));
		selenium.waitForPageToLoad("30000");
		selenium.clickAt("link=Submissions",
			RuntimeVariables.replace("Submissions"));
		selenium.waitForPageToLoad("30000");
		selenium.clickAt("link=Pending", RuntimeVariables.replace("Pending"));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("Single Approver"),
			selenium.getText("//td[1]/a"));
		assertEquals(RuntimeVariables.replace("WC Web Content Title"),
			selenium.getText("//td[2]/a"));
		assertEquals(RuntimeVariables.replace("Web Content"),
			selenium.getText("//td[3]/a"));
		assertEquals(RuntimeVariables.replace("Update"),
			selenium.getText("//td[4]/a"));
		assertTrue(selenium.isVisible("//td[5]/a"));
		assertEquals(RuntimeVariables.replace("Never"),
			selenium.getText("//td[6]/a"));
		selenium.clickAt("//td[2]/a",
			RuntimeVariables.replace("WC Web Content Title"));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("Review"),
			selenium.getText("//span[.='Review']"));
		assertEquals(RuntimeVariables.replace("Never"),
			selenium.getText("//tr[3]/td[2]"));
		assertEquals(RuntimeVariables.replace("Yes"),
			selenium.getText("//td[contains(.,'Yes')]"));
		assertEquals(RuntimeVariables.replace("Update"),
			selenium.getText("//span[.='Update']"));
		assertEquals(RuntimeVariables.replace("Never"),
			selenium.getText("//tr[4]/td[2]"));
		assertEquals(RuntimeVariables.replace("No"),
			selenium.getText("//td[contains(.,'No')]"));
		assertEquals(RuntimeVariables.replace("Resubmit"),
			selenium.getText("//td[4]/span/a/span"));
		selenium.clickAt("//td[4]/span/a/span",
			RuntimeVariables.replace("Resubmit"));

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("//div[3]/span/span/button")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		assertEquals(RuntimeVariables.replace("OK"),
			selenium.getText("//div[3]/span/span/button"));
		selenium.clickAt("//div[3]/span/span/button",
			RuntimeVariables.replace("OK"));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace(
				"Your request completed successfully."),
			selenium.getText("//div[@class='portlet-msg-success']"));
		assertEquals(RuntimeVariables.replace("Review"),
			selenium.getText("xPath=(//span[.='Review'])[1]"));
		assertEquals(RuntimeVariables.replace("Never"),
			selenium.getText("//tr[3]/td[2]"));
		assertEquals(RuntimeVariables.replace("Yes"),
			selenium.getText("xPath=(//td[contains(.,'Yes')])[1]"));
		assertEquals(RuntimeVariables.replace("Update"),
			selenium.getText("xPath=(//span[.='Update'])[1]"));
		assertEquals(RuntimeVariables.replace("Never"),
			selenium.getText("//tr[4]/td[2]"));
		assertEquals(RuntimeVariables.replace("Yes"),
			selenium.getText("xPath=(//td[contains(.,'Yes')])[2]"));
		assertEquals(RuntimeVariables.replace("Review"),
			selenium.getText("xPath=(//span[.='Review'])[2]"));
		assertEquals(RuntimeVariables.replace("Never"),
			selenium.getText("//tr[5]/td[2]"));
		assertEquals(RuntimeVariables.replace("No"),
			selenium.getText("xPath=(//td[contains(.,'No')])[1]"));
		assertEquals(RuntimeVariables.replace(
				"Task initially assigned to the Site Owner role."),
			selenium.getText(
				"xPath=(//div[@class='task-activity task-type-1'])[4]/div[2]"));
		assertEquals(RuntimeVariables.replace("Assigned initial task."),
			selenium.getText(
				"xPath=(//div[@class='task-activity task-type-1'])[4]/div[3]"));
	}
}