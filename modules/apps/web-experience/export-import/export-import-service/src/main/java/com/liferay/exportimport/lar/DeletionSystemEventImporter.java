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

package com.liferay.exportimport.lar;

import static com.liferay.exportimport.kernel.lar.StagedModelType.REFERRER_CLASS_NAME_ALL;
import static com.liferay.exportimport.kernel.lar.StagedModelType.REFERRER_CLASS_NAME_ANY;

<<<<<<< HEAD
import aQute.bnd.annotation.ProviderType;

=======
>>>>>>> compatible
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.xml.SecureXMLFactoryProviderUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.ElementHandler;
import com.liferay.portal.kernel.xml.ElementProcessor;

import java.io.StringReader;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * @author Zsolt Berentey
 */
<<<<<<< HEAD
@ProviderType
=======
>>>>>>> compatible
public class DeletionSystemEventImporter {

	public static DeletionSystemEventImporter getInstance() {
		return _instance;
	}

	public void importDeletionSystemEvents(
			final PortletDataContext portletDataContext)
		throws Exception {

		if (!MapUtil.getBoolean(
				portletDataContext.getParameterMap(),
				PortletDataHandlerKeys.DELETIONS)) {

			return;
		}

		String xml = portletDataContext.getZipEntryAsString(
			ExportImportPathUtil.getSourceRootPath(portletDataContext) +
				"/deletion-system-events.xml");

		if (xml == null) {
			return;
		}

		XMLReader xmlReader = SecureXMLFactoryProviderUtil.newXMLReader();

		ElementHandler elementHandler = new ElementHandler(
			new ElementProcessor() {

				@Override
				public void processElement(Element element) {
					doImportDeletionSystemEvents(portletDataContext, element);
				}

			},
			new String[] {"deletion-system-event"});

		xmlReader.setContentHandler(elementHandler);

		xmlReader.parse(new InputSource(new StringReader(xml)));
	}

	protected void doImportDeletionSystemEvents(
		PortletDataContext portletDataContext, Element element) {

		StagedModelType stagedModelType = new StagedModelType(
			element.attributeValue("class-name"),
			element.attributeValue("referrer-class-name"));

		if (!_shouldImportDeletionSystemEvent(
				portletDataContext, stagedModelType)) {

			return;
		}

		try {
			StagedModelDataHandlerUtil.deleteStagedModel(
				portletDataContext, element);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				StringBundler sb = new StringBundler(4);

				sb.append("Unable to process deletion for ");
				sb.append(stagedModelType);
				sb.append(" with UUID ");
				sb.append(element.attributeValue("uuid"));

				_log.warn(sb.toString());
			}
		}
	}

	private DeletionSystemEventImporter() {
	}

	private boolean _shouldImportDeletionSystemEvent(
		PortletDataContext portletDataContext,
		StagedModelType stagedModelType) {

		Set<StagedModelType> stagedModelTypes =
			portletDataContext.getDeletionSystemEventStagedModelTypes();

		if (stagedModelTypes.contains(stagedModelType)) {
			return true;
		}

		Stream<StagedModelType> stream = stagedModelTypes.stream();

		Predicate<StagedModelType> classNameIdPredicate =
			smt -> smt.getClassNameId() == stagedModelType.getClassNameId();

		Predicate<StagedModelType> allReferrerClassNamePredicate =
			smt -> REFERRER_CLASS_NAME_ALL.equals(smt.getReferrerClassName());

		Predicate<StagedModelType> anyReferrerClassNamePredicate =
			smt -> Validator.isNotNull(
				stagedModelType.getReferrerClassName()) &&
				   REFERRER_CLASS_NAME_ANY.equals(smt.getReferrerClassName());

		boolean hasSimilar = stream.anyMatch(
			classNameIdPredicate.and(
				allReferrerClassNamePredicate.or(
					anyReferrerClassNamePredicate)));

		if (hasSimilar) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DeletionSystemEventImporter.class);

	private static final DeletionSystemEventImporter _instance =
		new DeletionSystemEventImporter();

}