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

package com.liferay.fragment.internal.validator;

import com.liferay.fragment.exception.FragmentEntryConfigurationException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.util.FileImpl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Rubén Pulido
 */
public class FragmentEntryValidatorImplTest {

	@Before
	public void setUp() {
		new FileUtil().setFile(new FileImpl());

		_fragmentEntryValidatorImpl = new FragmentEntryValidatorImpl();
	}

	@Test
	public void testValidateConfigurationInvalidFieldCheckboxDefaultValueUnsupported()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0" +
				"/defaultValue: unsupported is not a valid enum value");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-invalid-field-checkbox-defaultValue-" +
					"unsupported.json"));
	}

	@Test
	public void testValidateConfigurationInvalidFieldCheckboxExtraProperties()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0: " +
				"extraneous key [extra] is not permitted");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-invalid-field-checkbox-extra-properties.json"));
	}

	@Test
	public void testValidateConfigurationInvalidFieldNameEmpty()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0" +
				"/name: expected minLength: 1, actual: 0");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-invalid-field-name-empty.json"));
	}

	@Test
	public void testValidateConfigurationInvalidFieldNameMissing()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0: " +
				"required key [name] not found");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-invalid-field-name-missing.json"));
	}

	@Test
	public void testValidateConfigurationInvalidFieldNameNonalphanumeric()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0" +
				"/name: string [a_b-c.d?e] does not match pattern");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_getFileContent(
				"configuration-invalid-field-name-non-alphanumeric.json"));
	}

	@Test
	public void testValidateConfigurationInvalidFieldNameWithSpace()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0" +
				"/name: string [a b] does not match pattern");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-invalid-field-name-with-space.json"));
	}

	@Test
	public void testValidateConfigurationInvalidFieldSetsExtraProperties()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: extraneous key [extra] is not permitted");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-invalid-field-sets-extra-properties.json"));
	}

	@Test
	public void testValidateConfigurationInvalidFieldSetsMissing()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: required key [fieldSets] not found");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-invalid-field-sets-missing.json"));
	}

	@Test
	public void testValidateConfigurationInvalidSelectFieldDataTypeUnsupported()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0" +
				"/dataType: unsupported is not a valid enum value");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-invalid-field-select-dataType-unsupported." +
					"json"));
	}

	@Test
	public void testValidateConfigurationInvalidSelectFieldDefaultValueMissing()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0: " +
				"required key [defaultValue] not found");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-invalid-field-select-defaultValue-missing." +
					"json"));
	}

	@Test
	public void testValidateConfigurationInvalidSelectFieldExtraProperties()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0: " +
				"extraneous key [extra] is not permitted");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-invalid-field-select-extra-properties.json"));
	}

	@Test
	public void testValidateConfigurationInvalidSelectFieldTypeOptionsExtraProperties()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0" +
				"/typeOptions: extraneous key [extra] is not permitted");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-invalid-field-select-typeOptions-extra-" +
					"properties.json"));
	}

	@Test
	public void testValidateConfigurationInvalidSelectFieldTypeOptionsMissing()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0: " +
				"required key [typeOptions] not found");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-invalid-field-select-typeOptions-missing.json"));
	}

	@Test
	public void testValidateConfigurationInvalidSelectFieldTypeOptionsValidValuesExtraProperties()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0" +
				"/typeOptions/validValues/0: extraneous key [extra] is not " +
					"permitted");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-invalid-field-select-typeOptions-validValues-" +
					"extra-properties.json"));
	}

	@Test
	public void testValidateConfigurationInvalidSelectFieldTypeOptionsValidValuesMissing()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0" +
				"/typeOptions: required key [validValues] not found");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-invalid-field-select-typeOptions-validValues-" +
					"missing.json"));
	}

	@Test
	public void testValidateConfigurationInvalidSelectFieldTypeOptionsValidValuesValueMissing()
		throws Exception {

		expectedException.expect(FragmentEntryConfigurationException.class);
		expectedException.expectMessage(
			"#: only 1 subschema matches out of 2\n#/fieldSets/0/fields/0" +
				"/typeOptions/validValues/0: required key [value] not found");

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-invalid-field-select-typeOptions-validValues-" +
					"value-missing.json"));
	}

	@Test
	public void testValidateConfigurationValidComplete() throws Exception {
		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-valid-complete.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldCheckboxComplete()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-valid-field-checkbox-complete.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldCheckboxDefaultValueBooleanFalse()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-valid-field-checkbox-defaultValue-boolean-" +
					"false.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldCheckboxDefaultValueBooleanTrue()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-valid-field-checkbox-defaultValue-boolean-" +
					"true.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldCheckboxDefaultValueStringFalse()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-valid-field-checkbox-defaultValue-string-" +
					"false.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldCheckboxDefaultValueStringTrue()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read(
				"configuration-valid-field-checkbox-defaultValue-string-" +
					"true.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldCheckboxRequired()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-valid-field-checkbox-required.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldSelectDoubleRequired()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-valid-field-select-double-required.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldSelectIntRequired()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-valid-field-select-int-required.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldSelectStringComplete()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-valid-field-select-string-complete.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldSelectStringRequired()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-valid-field-select-string-required.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldTextComplete()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-valid-field-text-complete.json"));
	}

	@Test
	public void testValidateConfigurationValidFieldTextRequired()
		throws Exception {

		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-valid-field-text-required.json"));
	}

	@Test
	public void testValidateConfigurationValidRequired() throws Exception {
		_fragmentEntryValidatorImpl.validateConfiguration(
			_read("configuration-valid-required.json"));
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private String _read(String fileName) throws Exception {
		return new String(
			FileUtil.getBytes(getClass(), "dependencies/" + fileName));
	}

	private FragmentEntryValidatorImpl _fragmentEntryValidatorImpl;

}