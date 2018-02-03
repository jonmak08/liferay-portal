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

package com.liferay.portal.search.test.util.query;

import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.generic.StringQuery;
import com.liferay.portal.search.test.util.DocumentsAssert;
import com.liferay.portal.search.test.util.IdempotentRetryAssert;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author Tibor Lipusz
 * @author André de Oliveira
 */
public abstract class BaseStringQueryTestCase extends BaseIndexingTestCase {

	@Test
<<<<<<< HEAD
	public void testBooleanOperatorAnd() throws Exception {
		addDocuments("java eclipse", "java liferay", "java liferay eclipse");

		assertSearch(
			"java AND eclipse",
			Arrays.asList("java eclipse", "java liferay eclipse"));
		assertSearch(
			"eclipse AND liferay", Arrays.asList("java liferay eclipse"));
	}

	@Test
	public void testBooleanOperatorAndWithNot() throws Exception {
		addDocuments("alpha bravo", "alpha charlie", "charlie delta");

		assertSearch("alpha AND NOT bravo", Arrays.asList("alpha charlie"));
	}

	@Test
	public void testBooleanOperatorNot() throws Exception {
		addDocuments("alpha bravo", "alpha charlie", "charlie delta");

		assertSearch("NOT alpha", Arrays.asList("charlie delta"));
		assertSearch(
			"NOT bravo", Arrays.asList("alpha charlie", "charlie delta"));
	}

	@Test
	public void testBooleanOperatorNotDeep() throws Exception {
		addDocuments("alpha bravo", "alpha charlie", "charlie delta");

		assertSearch(
			"+(*:* NOT alpha) +charlie", Arrays.asList("charlie delta"));
	}

	@Test
	public void testBooleanOperatorOr() throws Exception {
		addDocuments("alpha bravo", "alpha charlie", "charlie delta");

		assertSearch(
			"alpha OR charlie",
			Arrays.asList("alpha charlie", "alpha bravo", "charlie delta"));
		assertSearch(
			"alpha OR delta",
			Arrays.asList("charlie delta", "alpha bravo", "alpha charlie"));
		assertSearch(
			"bravo OR delta", Arrays.asList("alpha bravo", "charlie delta"));
	}

	@Test
	public void testBooleanOperatorOrWithNot() throws Exception {
		addDocuments("alpha bravo", "alpha charlie", "charlie delta");

		assertSearch("alpha OR NOT bravo", Arrays.asList("alpha charlie"));
=======
	public void testAnd() throws Exception {
		addDocument("description", "java eclipse");
		addDocument("description", "java liferay");
		addDocument("description", "java liferay eclipse");

		assertSearch(
			"java AND eclipse", "description",
			Arrays.asList("java eclipse", "java liferay eclipse"));

		assertSearch(
			"eclipse AND liferay", "description",
			Arrays.asList("java liferay eclipse"));
>>>>>>> compatible
	}

	@Test
	public void testField() throws Exception {
<<<<<<< HEAD
		addDocuments("java", "eclipse", "liferay");

		assertSearch(
			"title:(java OR eclipse)", Arrays.asList("java", "eclipse"));
		assertSearch("description:(java OR eclipse)", Collections.emptyList());
	}

	@Test
	public void testPrefixOperatorMust() throws Exception {
		addDocuments("alpha bravo", "alpha charlie", "charlie delta");

		assertSearch("+alpha", Arrays.asList("alpha bravo", "alpha charlie"));
		assertSearch(
			"+alpha bravo", Arrays.asList("alpha bravo", "alpha charlie"));
		assertSearch("+alpha +bravo", Arrays.asList("alpha bravo"));
		assertSearch(
			"+alpha delta", Arrays.asList("alpha bravo", "alpha charlie"));
		assertSearch("+alpha +delta", Arrays.asList());
	}

	@Test
	public void testPrefixOperatorMustNot() throws Exception {
		addDocuments("alpha bravo", "alpha charlie", "charlie delta");

		assertSearch("-alpha", Arrays.asList("charlie delta"));
		assertSearch("-alpha bravo", Arrays.asList());
		assertSearch("-alpha -bravo", Arrays.asList("charlie delta"));
		assertSearch("-alpha delta", Arrays.asList("charlie delta"));
		assertSearch("-alpha -delta", Arrays.asList());
	}

	@Test
	public void testPrefixOperatorMustNotWithBooleanOperatorOr()
		throws Exception {

		addDocuments("alpha bravo", "alpha charlie", "charlie delta");

		assertSearch("(-bravo OR alpha)", Arrays.asList("alpha charlie"));
		assertSearch("-(bravo OR alpha)", Arrays.asList("charlie delta"));
		assertSearch("-(bravo) OR (alpha)", Arrays.asList("alpha charlie"));
		assertSearch("-(bravo) OR alpha", Arrays.asList("alpha charlie"));
		assertSearch("-bravo OR (alpha)", Arrays.asList("alpha charlie"));
		assertSearch("-bravo OR alpha", Arrays.asList("alpha charlie"));
	}

	protected void addDocuments(String... values) throws Exception {
		addDocuments(
			value -> DocumentCreationHelpers.singleText(_FIELD_NAME, value),
			Arrays.asList(values));
	}

	protected void assertSearch(String query, List<String> expectedValues)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			5, TimeUnit.SECONDS, () -> doAssertSearch(query, expectedValues));
	}

	protected Void doAssertSearch(String query, List<String> expectedValues)
=======
		addDocument("title", "java");
		addDocument("title", "eclipse");
		addDocument("title", "liferay");

		assertSearch(
			"title:(java OR eclipse)", "title",
			Arrays.asList("java", "eclipse"));

		assertSearch(
			"description:(java OR eclipse)", "title", Collections.emptyList());
	}

	protected void addDocument(String fieldName, String... fieldValues)
		throws Exception {

		addDocument(DocumentCreationHelpers.singleText(fieldName, fieldValues));
	}

	protected void assertSearch(
			String query, String fieldName, List<String> expectedValues)
>>>>>>> compatible
		throws Exception {

		SearchContext searchContext = createSearchContext();

		StringQuery stringQuery = _createStringQuery(query, searchContext);

<<<<<<< HEAD
		Hits hits = search(searchContext, stringQuery);

		DocumentsAssert.assertValues(
			query, hits.getDocs(), _FIELD_NAME, expectedValues);

		return null;
=======
		IdempotentRetryAssert.retryAssert(
			5, TimeUnit.SECONDS,
			() -> {
				Hits hits = search(searchContext, stringQuery);

				DocumentsAssert.assertValues(
					query, hits.getDocs(), fieldName, expectedValues);

				return null;
			});
>>>>>>> compatible
	}

	private StringQuery _createStringQuery(
		String query, SearchContext searchContext) {

		StringQuery stringQuery = new StringQuery(query);

		stringQuery.setQueryConfig(searchContext.getQueryConfig());

		return stringQuery;
	}

<<<<<<< HEAD
	private static final String _FIELD_NAME = "title";

=======
>>>>>>> compatible
}