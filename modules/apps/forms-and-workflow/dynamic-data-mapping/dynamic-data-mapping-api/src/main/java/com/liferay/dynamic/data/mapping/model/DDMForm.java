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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.util.HashUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pablo Carvalho
 */
public class DDMForm implements Serializable {

	public DDMForm() {
	}

	public DDMForm(DDMForm ddmForm) {
		_availableLocales = new LinkedHashSet<>(ddmForm._availableLocales);
		_defaultLocale = ddmForm._defaultLocale;

		for (DDMFormField ddmFormField : ddmForm._ddmFormFields) {
			addDDMFormField(new DDMFormField(ddmFormField));
		}
<<<<<<< HEAD

		for (DDMFormRule ddmFormRule : ddmForm._ddmFormRules) {
			addDDMFormRule(new DDMFormRule(ddmFormRule));
		}

		_ddmFormSuccessPageSettings = new DDMFormSuccessPageSettings(
			ddmForm.getDDMFormSuccessPageSettings());
=======
>>>>>>> compatible
	}

	public void addAvailableLocale(Locale locale) {
		_availableLocales.add(locale);
	}

	public void addDDMFormField(DDMFormField ddmFormField) {
		ddmFormField.setDDMForm(this);

		_ddmFormFields.add(ddmFormField);
	}

<<<<<<< HEAD
	public void addDDMFormRule(DDMFormRule ddmFormRule) {
		_ddmFormRules.add(ddmFormRule);
	}

=======
>>>>>>> compatible
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMForm)) {
			return false;
		}

		DDMForm ddmForm = (DDMForm)obj;

		if (Objects.equals(_availableLocales, ddmForm._availableLocales) &&
			Objects.equals(_defaultLocale, ddmForm._defaultLocale) &&
<<<<<<< HEAD
			Objects.equals(_ddmFormFields, ddmForm._ddmFormFields) &&
			Objects.equals(_ddmFormRules, ddmForm._ddmFormRules) &&
			Objects.equals(
				_ddmFormSuccessPageSettings,
				ddmForm._ddmFormSuccessPageSettings)) {
=======
			Objects.equals(_ddmFormFields, ddmForm._ddmFormFields)) {
>>>>>>> compatible

			return true;
		}

		return false;
	}

	public Set<Locale> getAvailableLocales() {
		return _availableLocales;
	}

	public List<DDMFormField> getDDMFormFields() {
		return _ddmFormFields;
	}

	public Map<String, DDMFormField> getDDMFormFieldsMap(
		boolean includeNestedDDMFormFields) {

		Map<String, DDMFormField> ddmFormFieldsMap = new LinkedHashMap<>();

		for (DDMFormField ddmFormField : _ddmFormFields) {
			ddmFormFieldsMap.put(ddmFormField.getName(), ddmFormField);

			if (includeNestedDDMFormFields) {
				ddmFormFieldsMap.putAll(
					ddmFormField.getNestedDDMFormFieldsMap());
			}
		}

		return ddmFormFieldsMap;
	}

<<<<<<< HEAD
	public List<DDMFormRule> getDDMFormRules() {
		return _ddmFormRules;
	}

	public DDMFormSuccessPageSettings getDDMFormSuccessPageSettings() {
		return _ddmFormSuccessPageSettings;
	}

=======
>>>>>>> compatible
	public Locale getDefaultLocale() {
		return _defaultLocale;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _availableLocales);

		hash = HashUtil.hash(hash, _defaultLocale);

<<<<<<< HEAD
		hash = HashUtil.hash(hash, _ddmFormFields);

		hash = HashUtil.hash(hash, _ddmFormRules);

		return HashUtil.hash(hash, _ddmFormSuccessPageSettings);
=======
		return HashUtil.hash(hash, _ddmFormFields);
>>>>>>> compatible
	}

	public void setAvailableLocales(Set<Locale> availableLocales) {
		_availableLocales = availableLocales;
	}

	public void setDDMFormFields(List<DDMFormField> ddmFormFields) {
		for (DDMFormField ddmFormField : ddmFormFields) {
			ddmFormField.setDDMForm(this);
		}

		_ddmFormFields = ddmFormFields;
	}

<<<<<<< HEAD
	public void setDDMFormRules(List<DDMFormRule> ddmFormRules) {
		_ddmFormRules = ddmFormRules;
	}

	public void setDDMFormSuccessPageSettings(
		DDMFormSuccessPageSettings ddmFormSuccessPageSettings) {

		_ddmFormSuccessPageSettings = new DDMFormSuccessPageSettings(
			ddmFormSuccessPageSettings);
	}

=======
>>>>>>> compatible
	public void setDefaultLocale(Locale defaultLocale) {
		_defaultLocale = defaultLocale;
	}

	private Set<Locale> _availableLocales = new LinkedHashSet<>();
	private List<DDMFormField> _ddmFormFields = new ArrayList<>();
<<<<<<< HEAD
	private List<DDMFormRule> _ddmFormRules = new ArrayList<>();
	private DDMFormSuccessPageSettings _ddmFormSuccessPageSettings =
		new DDMFormSuccessPageSettings();
=======
>>>>>>> compatible
	private Locale _defaultLocale;

}