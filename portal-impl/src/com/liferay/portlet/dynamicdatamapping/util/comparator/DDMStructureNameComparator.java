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

package com.liferay.portlet.dynamicdatamapping.util.comparator;

import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;

import java.text.Collator;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Alberto Chaparro
 * @author Mariano Álvaro
 */
public class DDMStructureNameComparator implements Comparator<DDMStructure> {

	public DDMStructureNameComparator() {
		this(true, LocaleThreadLocal.getSiteDefaultLocale());
	}

	public DDMStructureNameComparator(Locale locale) {
		this(true, locale);
	}

	public DDMStructureNameComparator(boolean ascending, Locale locale) {
		_ascending = ascending;
		_locale = locale;
	}

	@Override
	public int compare(DDMStructure DDMStructure1, DDMStructure DDMStructure2) {
		if (_ascending) {
			return _COLLATOR.compare(
					DDMStructure1.getName(_locale),
					DDMStructure2.getName(_locale));
		}
		else {
			return - (_COLLATOR.compare(
					DDMStructure1.getName(_locale),
					DDMStructure2.getName(_locale)));
		}
	}

	private static final Collator _COLLATOR = Collator.getInstance();
	static {
		_COLLATOR.setStrength(Collator.PRIMARY);
	}

	private final boolean _ascending;
	private final Locale _locale;

}