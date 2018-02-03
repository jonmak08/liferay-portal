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

package com.liferay.source.formatter.checkstyle.checks;

<<<<<<< HEAD
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.checkstyle.util.DetailASTUtil;
import com.liferay.source.formatter.parser.JavaTerm;
import com.liferay.source.formatter.util.DebugUtil;
=======
import com.liferay.source.formatter.checkstyle.util.DetailASTUtil;
>>>>>>> compatible

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class ConstantNameCheck
	extends com.puppycrawl.tools.checkstyle.checks.naming.ConstantNameCheck {

<<<<<<< HEAD
	public void setCamelCaseTypeNames(String camelCaseTypeNames) {
		_camelCaseTypeNames = StringUtil.split(camelCaseTypeNames);
	}

	public void setImmutableFieldTypes(String immutableFieldTypes) {
		_immutableFieldTypes = StringUtil.split(immutableFieldTypes);
	}

	public void setShowDebugInformation(boolean showDebugInformation) {
		_showDebugInformation = showDebugInformation;
	}

	@Override
	public void visitToken(DetailAST detailAST) {
		if (!_showDebugInformation) {
			_checkConstantName(detailAST);

			return;
		}

		long startTime = System.currentTimeMillis();

		_checkConstantName(detailAST);

		long endTime = System.currentTimeMillis();

		Class<?> clazz = getClass();

		DebugUtil.increaseProcessingTime(
			clazz.getSimpleName(), endTime - startTime);
	}

	private void _checkConstantName(DetailAST detailAST) {
=======
	@Override
	public void visitToken(DetailAST detailAST) {
>>>>>>> compatible
		if (!mustCheckName(detailAST)) {
			return;
		}

<<<<<<< HEAD
		String regex = null;

		String typeName = DetailASTUtil.getTypeName(detailAST);

		if (ArrayUtil.contains(_camelCaseTypeNames, typeName) ||
			DetailASTUtil.isCollection(
				detailAST.findFirstToken(TokenTypes.TYPE))) {

			regex = _CAMEL_CASE_REGEX;
		}
		else if (_isImmutableFieldType(typeName)) {
			regex = _UPPER_CASE_REGEX;
		}
		else {
			regex = _CONSTANT_NAME_REGEX;
			typeName = null;
		}

		String accessLevel = null;

		DetailAST modifiersAST = detailAST.findFirstToken(TokenTypes.MODIFIERS);

		if (modifiersAST.branchContains(TokenTypes.LITERAL_PRIVATE)) {
			accessLevel = JavaTerm.ACCESS_MODIFIER_PRIVATE;

			regex = StringUtil.replaceFirst(regex, '^', "^_");
		}
		else if (modifiersAST.branchContains(TokenTypes.LITERAL_PROTECTED)) {
			accessLevel = JavaTerm.ACCESS_MODIFIER_PROTECTED;
		}
		else if (modifiersAST.branchContains(TokenTypes.LITERAL_PUBLIC)) {
			accessLevel = JavaTerm.ACCESS_MODIFIER_PUBLIC;
=======
		String message = null;
		String regex = null;

		DetailAST modifiersAST = detailAST.findFirstToken(TokenTypes.MODIFIERS);

		if (modifiersAST.branchContains(TokenTypes.LITERAL_PRIVATE)) {
			if (DetailASTUtil.isCollection(
					detailAST.findFirstToken(TokenTypes.TYPE))) {

				message = _MSG_PRIVATE_COLLECTION;
				regex = "^_[a-z0-9][_a-zA-Z0-9]*$";
			}
			else {
				message = _MSG_INVALID_PRIVATE_NAME;
				regex = "^_[_a-zA-Z0-9]*$";
			}
		}
		else if (modifiersAST.branchContains(TokenTypes.LITERAL_PROTECTED) ||
				 modifiersAST.branchContains(TokenTypes.LITERAL_PUBLIC)) {

			if (DetailASTUtil.isCollection(
					detailAST.findFirstToken(TokenTypes.TYPE))) {

				message = _MSG_PROTECTED_PUBLIC_COLLECTION;
				regex = "^[a-z0-9][_a-zA-Z0-9]*$";
			}
			else {
				message = _MSG_INVALID_PROTECTED_PUBLIC_NAME;
				regex = "^[a-zA-Z0-9][_a-zA-Z0-9]*$";
			}
>>>>>>> compatible
		}
		else {
			return;
		}

		DetailAST nameAST = detailAST.findFirstToken(TokenTypes.IDENT);

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(nameAST.getText());

		if (!matcher.find()) {
<<<<<<< HEAD
			if (typeName == null) {
				log(
					nameAST.getLineNo(), _MSG_INVALID_CONSTANT_NAME,
					accessLevel, nameAST.getText(), regex);
			}
			else {
				log(
					nameAST.getLineNo(), _MSG_INVALID_CONSTANT_TYPE_NAME,
					accessLevel, nameAST.getText(), typeName, regex);
			}
		}
	}

	private boolean _isImmutableFieldType(String typeName) {
		for (String immutableFieldType : _immutableFieldTypes) {
			if (typeName.equals(immutableFieldType) ||
				typeName.startsWith(immutableFieldType + "[]")) {

				return true;
			}
		}

		return false;
	}

	private static final String _CAMEL_CASE_REGEX = "^[a-z0-9][a-zA-Z0-9]*$";

	private static final String _CONSTANT_NAME_REGEX =
		"^[a-zA-Z0-9][_a-zA-Z0-9]*$";

	private static final String _MSG_INVALID_CONSTANT_NAME =
		"name.invalidConstantPattern";

	private static final String _MSG_INVALID_CONSTANT_TYPE_NAME =
		"name.invalidConstantTypePattern";

	private static final String _UPPER_CASE_REGEX = "^[A-Z0-9][_A-Z0-9]*$";

	private String[] _camelCaseTypeNames = new String[0];
	private String[] _immutableFieldTypes = new String[0];
	private boolean _showDebugInformation;
=======
			log(nameAST.getLineNo(), message, nameAST.getText(), regex);
		}
	}

	private static final String _MSG_INVALID_PRIVATE_NAME =
		"name.invalidPrivatePattern";

	private static final String _MSG_INVALID_PROTECTED_PUBLIC_NAME =
		"name.invalidProtectedPublicPattern";

	private static final String _MSG_PRIVATE_COLLECTION =
		"name.collectionPrivatePattern";

	private static final String _MSG_PROTECTED_PUBLIC_COLLECTION =
		"name.collectionProtectedPublicPattern";
>>>>>>> compatible

}