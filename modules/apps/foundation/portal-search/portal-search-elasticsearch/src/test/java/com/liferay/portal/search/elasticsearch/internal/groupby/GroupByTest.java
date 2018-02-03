/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.search.elasticsearch.internal.groupby;

import com.liferay.portal.search.elasticsearch.internal.ElasticsearchIndexingFixture;
import com.liferay.portal.search.elasticsearch.internal.connection.ElasticsearchFixture;
<<<<<<< HEAD
import com.liferay.portal.search.elasticsearch.internal.connection.LiferayIndexCreator;
import com.liferay.portal.search.elasticsearch.internal.count.ElasticsearchCountTest;
=======
>>>>>>> compatible
import com.liferay.portal.search.test.util.groupby.BaseGroupByTestCase;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;

import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class GroupByTest extends BaseGroupByTestCase {

	@Override
	@Test
	public void testGroupBy() throws Exception {
		super.testGroupBy();
	}

	@Override
	@Test
	public void testStartAndEnd() throws Exception {
		super.testStartAndEnd();
	}

	@Override
	@Test
	public void testStartAndSize() throws Exception {
		super.testStartAndSize();
	}

	@Override
	protected IndexingFixture createIndexingFixture() {
<<<<<<< HEAD
		ElasticsearchFixture elasticsearchFixture = new ElasticsearchFixture(
			ElasticsearchCountTest.class.getSimpleName());

		return new ElasticsearchIndexingFixture(
			elasticsearchFixture, BaseIndexingTestCase.COMPANY_ID,
			new LiferayIndexCreator(elasticsearchFixture));
=======
		return new ElasticsearchIndexingFixture(
			new ElasticsearchFixture(GroupByTest.class.getSimpleName()),
			BaseIndexingTestCase.COMPANY_ID);
>>>>>>> compatible
	}

}