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

package com.liferay.portal.search.elasticsearch.internal.index;

import com.liferay.portal.kernel.test.util.RandomTestUtil;
<<<<<<< HEAD
import com.liferay.portal.search.elasticsearch.internal.connection.IndexName;

import java.util.HashMap;
=======
import com.liferay.portal.search.elasticsearch.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch.internal.connection.Index;
import com.liferay.portal.search.elasticsearch.internal.connection.IndexName;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
>>>>>>> compatible

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * @author André de Oliveira
 */
public class LiferayTypeMappingsPortugueseTest {

	@Before
	public void setUp() throws Exception {
		Class<?> clazz = getClass();

		_liferayIndexFixture = new LiferayIndexFixture(
			clazz.getSimpleName(), new IndexName(testName.getMethodName()));

		_liferayIndexFixture.setUp();
<<<<<<< HEAD
=======

		_index = _liferayIndexFixture.getIndex();

		_elasticsearchFixture = _liferayIndexFixture.getElasticsearchFixture();
>>>>>>> compatible
	}

	@After
	public void tearDown() throws Exception {
		_liferayIndexFixture.tearDown();
	}

	@Test
	public void testPortugueseDynamicTemplatesMatchAnalyzers()
		throws Exception {

<<<<<<< HEAD
=======
		Client client = _elasticsearchFixture.getClient();

		IndexRequestBuilder indexRequestBuilder = client.prepareIndex(
			_index.getName(),
			LiferayTypeMappingsConstants.LIFERAY_DOCUMENT_TYPE);

>>>>>>> compatible
		String field_pt = RandomTestUtil.randomString() + "_pt";
		String field_pt_BR = RandomTestUtil.randomString() + "_pt_BR";
		String field_pt_PT = RandomTestUtil.randomString() + "_pt_PT";

<<<<<<< HEAD
		_liferayIndexFixture.index(
			new HashMap<String, Object>() {
				{
					put(field_pt, RandomTestUtil.randomString());
					put(field_pt_BR, RandomTestUtil.randomString());
					put(field_pt_PT, RandomTestUtil.randomString());
				}
			});
=======
		indexRequestBuilder.setSource(
			field_pt, RandomTestUtil.randomString(), field_pt_BR,
			RandomTestUtil.randomString(), field_pt_PT,
			RandomTestUtil.randomString());

		indexRequestBuilder.get();
>>>>>>> compatible

		assertAnalyzer(field_pt, "portuguese");
		assertAnalyzer(field_pt_BR, "brazilian");
		assertAnalyzer(field_pt_PT, "portuguese");
	}

	@Rule
	public TestName testName = new TestName();

	protected void assertAnalyzer(String field, String analyzer)
		throws Exception {

<<<<<<< HEAD
		_liferayIndexFixture.assertAnalyzer(field, analyzer);
	}

=======
		FieldMappingAssert.assertAnalyzer(
			analyzer, field, LiferayTypeMappingsConstants.LIFERAY_DOCUMENT_TYPE,
			_index.getName(), _elasticsearchFixture.getIndicesAdminClient());
	}

	private ElasticsearchFixture _elasticsearchFixture;
	private Index _index;
>>>>>>> compatible
	private LiferayIndexFixture _liferayIndexFixture;

}