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

package com.liferay.portal.search.test.util.facet;

<<<<<<< HEAD
import com.liferay.portal.kernel.json.JSONObject;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.SimpleFacet;
<<<<<<< HEAD
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.test.util.indexing.QueryContributor;
=======
import com.liferay.portal.kernel.test.util.RandomTestUtil;
>>>>>>> compatible
import com.liferay.portal.search.test.util.indexing.QueryContributors;

import java.util.ArrayList;
import java.util.Arrays;
<<<<<<< HEAD
import java.util.List;

import org.mockito.Mockito;
=======
>>>>>>> compatible

/**
 * @author Bryan Engler
 * @author André de Oliveira
 */
public abstract class BaseSimpleFacetTestCase extends BaseFacetTestCase {

<<<<<<< HEAD
	protected void assertFacet(
			JSONObject jsonObject, List<String> expectedTerms)
		throws Exception {

		assertFacet(QueryContributors.dummy(), jsonObject, expectedTerms);
	}

	protected void assertFacet(
			QueryContributor queryContributor, JSONObject jsonObject,
			List<String> expectedTerms)
		throws Exception {

		assertFacet(
			searchContext -> createFacet(searchContext, jsonObject),
			queryContributor, expectedTerms);
	}

	protected Facet createFacet(
		SearchContext searchContext, JSONObject jsonObject) {

		Facet facet = new SimpleFacet(searchContext);

		facet.setFieldName(Field.STATUS);

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		facetConfiguration.setDataJSONObject(jsonObject);
=======
	@Override
	protected Facet createFacet(SearchContext searchContext) {
		Facet facet = new SimpleFacet(searchContext);

		facet.setFieldName(Field.TITLE);
>>>>>>> compatible

		return facet;
	}

	@Override
	protected String getField() {
<<<<<<< HEAD
		return Field.STATUS;
	}

	protected void testFrequencyThreshold() throws Exception {
		addDocuments(6, "one");
		addDocuments(5, "two");
		addDocuments(4, "three");
		addDocuments(3, "four");
		addDocuments(2, "five");
		addDocuments(1, "six");
=======
		return Field.TITLE;
	}

	protected void testFrequencyThreshold() throws Exception {
		addDocument("One Two Three Four Five Six");
		addDocument("ONE TWO THREE FOUR FIVE");
		addDocument("one two three four");
		addDocument("OnE tWo ThReE");
		addDocument("oNE tWO");
		addDocument("oNe");
>>>>>>> compatible

		assertFacet(
			setUpFrequencyThreshold(4, setUpMaxTerms(5)),
			new ArrayList<String>() {
				{
					add("one=6");
					add("two=5");
					add("three=4");
				}
			});

		assertFacet(
			setUpFrequencyThreshold(4, setUpMaxTerms(2)),
			new ArrayList<String>() {
				{
					add("one=6");
					add("two=5");
				}
			});
	}

	protected void testMaxTerms() throws Exception {
<<<<<<< HEAD
		addDocuments(6, "One");
		addDocuments(5, "TWO");
		addDocuments(4, "ThReE");
		addDocuments(3, "four");
		addDocuments(2, "fivE");

		assertFacet(setUpMaxTerms(1), Arrays.asList("One=6"));
=======
		addDocument("One Two Three Four Five Six");
		addDocument("ONE TWO THREE FOUR FIVE");
		addDocument("one two three four");
		addDocument("OnE tWo ThReE");
		addDocument("oNE tWO");
		addDocument("oNe");

		assertFacet(setUpMaxTerms(1), Arrays.asList("one=6"));
>>>>>>> compatible

		assertFacet(
			setUpMaxTerms(5),
			new ArrayList<String>() {
				{
<<<<<<< HEAD
					add("One=6");
					add("TWO=5");
					add("ThReE=4");
					add("four=3");
					add("fivE=2");
=======
					add("one=6");
					add("two=5");
					add("three=4");
					add("four=3");
					add("five=2");
>>>>>>> compatible
				}
			});
	}

	protected void testMaxTermsNegative() throws Exception {
		addDocument("One");

<<<<<<< HEAD
		assertFacet(setUpMaxTerms(-25), Arrays.asList("One=1"));
=======
		assertFacet(setUpMaxTerms(-25), Arrays.asList("one=1"));
>>>>>>> compatible
	}

	protected void testMaxTermsZero() throws Exception {
		addDocument("One");

<<<<<<< HEAD
		assertFacet(setUpMaxTerms(0), Arrays.asList("One=1"));
=======
		assertFacet(setUpMaxTerms(0), Arrays.asList("one=1"));
>>>>>>> compatible
	}

	protected void testUnmatchedAreIgnored() throws Exception {
		String presentButUnmatched = RandomTestUtil.randomString();

		addDocument("One");
		addDocument(presentButUnmatched);

		assertFacet(
<<<<<<< HEAD
			QueryContributors.mustNotTerm(getField(), presentButUnmatched),
			Mockito.mock(JSONObject.class), Arrays.asList("One=1"));
=======
			QueryContributors.mustNotMatch(getField(), presentButUnmatched),
			Arrays.asList("one=1"));
>>>>>>> compatible
	}

}