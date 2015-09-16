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

package com.liferay.portal.search;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.test.IdempotentRetryAssert;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.ServiceContextTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.asset.service.persistence.AssetEntryQueryTestUtil;
import com.liferay.portlet.asset.util.AssetUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.util.DDMIndexerUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMStructureTestUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMTemplateTestUtil;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.ArrayUtils;

import org.junit.Assert;

/**
 * @author Preston Crary
 * @author André de Oliveira
 */
public abstract class TestOrderHelper {

	public void testOrderByDDMBooleanField() throws Exception {
		testOrderByDDMField(
			new String[] {"false", "true", "false", "true"},
			new String[] {"false", "false", "true", "true"},
			FieldConstants.BOOLEAN, "checkbox");
	}

	public void testOrderByDDMBooleanFieldRepeatable() throws Exception {
		testOrderByDDMFieldRepeatable(
			new String[] {
				"true|true", "false|false", "true|true", "false|false"
			},
			new String[] {
				"false|false", "false|false", "true|true", "true|true"
			},
			FieldConstants.BOOLEAN, "checkbox");
	}

	public void testOrderByDDMIntegerField() throws Exception {
		testOrderByDDMField(
			new String[] {"1", "10", "3", "2"},
			new String[] {"1", "2", "3", "10"}, FieldConstants.INTEGER,
			"ddm-integer");
	}

	public void testOrderByDDMIntegerFieldRepeatable() throws Exception {
		testOrderByDDMFieldRepeatable(
			new String[] {"50", "707|25", "1|99|42"},
			new String[] {"1|99|42", "707|25", "50"}, FieldConstants.INTEGER,
			"ddm-integer");
	}

	public void testOrderByDDMNumberField() throws Exception {
		testOrderByDDMField(
			new String[] {"3", "3.14", "12.34", "2.72", "1.41", "23.45", "20"},
			new String[] {"1.41", "2.72", "3", "3.14", "12.34", "20", "23.45"},
			FieldConstants.NUMBER, "ddm-number");
	}

	public void testOrderByDDMNumberFieldRepeatable() throws Exception {
		testOrderByDDMFieldRepeatable(
			new String[] {"20|12.34", "16.0", "3.14"},
			new String[] {"3.14", "20|12.34", "16.0"}, FieldConstants.NUMBER,
			"ddm-number");
	}

	public void testOrderByDDMTextField() throws Exception {
		testOrderByDDMTextField("text");
	}

	public void testOrderByDDMTextFieldKeyword() throws Exception {
		testOrderByDDMTextField("keyword");
	}

	public void testOrderByDDMTextFieldRepeatable() throws Exception {
		testOrderByDDMFieldRepeatable(
			new String[] {"B", "X|Y", "D|A|C|Z"},
			new String[] {"D|A|C|Z", "B", "X|Y"}, "string", "text");
	}

	protected TestOrderHelper(Group group) throws Exception {
		_group = group;

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			group.getGroupId());
	}

	protected DDMStructure addDDMStructure() throws Exception {
		String definition = DDMStructureTestUtil.getSampleStructureXSD(
			"name", _dataType, _indexType, _repeatable, _type,
			new Locale[] {LocaleUtil.US}, LocaleUtil.US);

		return DDMStructureTestUtil.addStructure(
			_serviceContext.getScopeGroupId(),
			getSearchableAssetEntryStructureClassName(), definition);
	}

	protected DDMTemplate addDDMTemplate(DDMStructure ddmStructure)
		throws Exception {

		return DDMTemplateTestUtil.addTemplate(
			_serviceContext.getScopeGroupId(), ddmStructure.getStructureId());
	}

	protected void addSearchableAssetEntries(
			DDMStructure ddmStructure, DDMTemplate ddmTemplate)
		throws Exception {

		BaseModel<?> parentBaseModel = getSearchableAssetEntryParentBaseModel(
			_group, _serviceContext);

		for (String value : _unsortedValues) {
			if (!_repeatable) {
				addSearchableAssetEntry(
					value, parentBaseModel, ddmStructure, ddmTemplate,
					_serviceContext);
			}
			else {
				addSearchableAssetEntryRepeatable(
					StringUtil.split(value, CharPool.PIPE), parentBaseModel,
					ddmStructure, ddmTemplate, _serviceContext);
			}
		}
	}

	protected abstract BaseModel<?> addSearchableAssetEntry(
			String fieldValue, BaseModel<?> parentBaseModel,
			DDMStructure ddmStructure, DDMTemplate ddmTemplate,
			ServiceContext serviceContext)
		throws Exception;

	protected abstract BaseModel<?> addSearchableAssetEntryRepeatable(
			String[] fieldValues, BaseModel<?> parentBaseModel,
			DDMStructure ddmStructure, DDMTemplate ddmTemplate,
			ServiceContext serviceContext)
		throws Exception;

	protected void assertSearch(AssetEntryQuery assetEntryQuery)
		throws Exception {

		Hits hits = search(assetEntryQuery);

		List<AssetEntry> assetEntries = AssetUtil.getAssetEntries(hits);

		Assert.assertEquals(
			ArrayUtils.toString(_sortedValues),
			ArrayUtils.toString(getValues(assetEntries)));
	}

	private AssetEntryQuery createAssetEntryQuery(DDMStructure ddmStructure)
		throws Exception {

		AssetEntryQuery assetEntryQuery =
			AssetEntryQueryTestUtil.createAssetEntryQuery(
				_group.getGroupId(),
				new String[] {getSearchableAssetEntryClassName()});

		String orderByCol1 = DDMIndexerUtil.encodeName(
			ddmStructure.getStructureId(), "name");

		assetEntryQuery.setOrderByCol1(orderByCol1);
		assetEntryQuery.setOrderByType1("asc");

		return assetEntryQuery;
	}

	protected AssetRendererFactory getAssetRendererFactory() {
		return AssetRendererFactoryRegistryUtil.
			getAssetRendererFactoryByClassName(
				getSearchableAssetEntryClassName());
	}

	protected abstract String getSearchableAssetEntryClassName();

	protected abstract String getValue(AssetRenderer assetRenderer)
		throws Exception;

	protected String[] getValues(List<AssetEntry> assetEntries)
		throws Exception {

		AssetRendererFactory assetRendererFactory = getAssetRendererFactory();

		String[] values = new String[assetEntries.size()];

		for (int i = 0; i < assetEntries.size(); i++) {
			AssetEntry assetEntry = assetEntries.get(i);

			AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(
				assetEntry.getClassPK());

			values[i] = getValue(assetRenderer);
		}

		return values;
	}

	protected abstract BaseModel<?> getSearchableAssetEntryParentBaseModel(
		Group group, ServiceContext serviceContext) throws Exception;

	protected abstract String getSearchableAssetEntryStructureClassName();

	protected Hits search(AssetEntryQuery assetEntryQuery) throws Exception {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(TestPropsValues.getCompanyId());
		searchContext.setGroupIds(new long[] {TestPropsValues.getGroupId()});
		searchContext.setUserId(TestPropsValues.getUserId());

		searchContext.setGroupIds(assetEntryQuery.getGroupIds());

		return AssetUtil.search(
			searchContext, assetEntryQuery, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	protected void testOrderByDDMField() throws Exception {
		DDMStructure ddmStructure = addDDMStructure();

		DDMTemplate ddmTemplate = addDDMTemplate(ddmStructure);

		addSearchableAssetEntries(ddmStructure, ddmTemplate);

		final AssetEntryQuery assetEntryQuery = createAssetEntryQuery(
			ddmStructure);

		IdempotentRetryAssert.retryAssert(
			3, TimeUnit.SECONDS,
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					assertSearch(assetEntryQuery);

					return null;
				}

			});
	}

	protected void testOrderByDDMField(
			String[] unsortedValues, String[] sortedValues, String dataType,
			String type)
		throws Exception {

		testOrderByDDMField(
			unsortedValues, sortedValues, dataType, "text", type);
	}

	protected void testOrderByDDMField(
			String[] unsortedValues, String[] sortedValues, String dataType,
			String indexType, String type)
		throws Exception {

		_unsortedValues = unsortedValues;
		_sortedValues = sortedValues;
		_dataType = dataType;
		_indexType = indexType;
		_type = type;
		_repeatable = false;

		testOrderByDDMField();
	}

	protected void testOrderByDDMFieldRepeatable(
			String[] unsortedValues, String[] sortedValues, String dataType,
			String type)
		throws Exception {

		testOrderByDDMFieldRepeatable(
			unsortedValues, sortedValues, dataType, "text", type);
	}

	protected void testOrderByDDMFieldRepeatable(
			String[] unsortedValues, String[] sortedValues, String dataType,
			String indexType, String type)
		throws Exception {

		_unsortedValues = unsortedValues;
		_sortedValues = sortedValues;
		_dataType = dataType;
		_indexType = indexType;
		_type = type;
		_repeatable = true;

		testOrderByDDMField();
	}

	protected void testOrderByDDMTextField(String indexType) throws Exception {
		testOrderByDDMField(
			new String[] {"a", "D", "c", "B"},
			new String[] {"a", "B", "c", "D"}, FieldConstants.STRING, indexType,
			"text");
	}

	private String _dataType;
	private final Group _group;
	private String _indexType;
	private boolean _repeatable;
	private final ServiceContext _serviceContext;
	private String[] _sortedValues;
	private String _type;
	private String[] _unsortedValues;

}