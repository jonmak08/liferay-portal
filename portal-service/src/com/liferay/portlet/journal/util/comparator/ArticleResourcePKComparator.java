/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portlet.journal.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.journal.model.JournalArticle;

/**
 * @author Eduardo Perez Garcia
 */
public class ArticleResourcePKComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"JournalArticle.resourcePrimKey ASC";

	public static final String ORDER_BY_DESC =
		"JournalArticle.resourcePrimKey DESC";

	public static final String[] ORDER_BY_FIELDS = {"resourcePrimKey"};

	public ArticleResourcePKComparator() {
		this(true);
	}

	public ArticleResourcePKComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		JournalArticle article1 = (JournalArticle)obj1;
		JournalArticle article2 = (JournalArticle)obj2;

		long resourcePK1 = article1.getResourcePrimKey();
		long resourcePK2 = article2.getResourcePrimKey();

		int value = 1;
		if (resourcePK1 <= resourcePK2) {
			value = -1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}