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

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.checkstyle.util.DetailASTUtil;

<<<<<<< HEAD
=======
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
>>>>>>> compatible
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
<<<<<<< HEAD
import java.util.Set;
=======
>>>>>>> compatible

/**
 * @author Hugo Huijser
 */
<<<<<<< HEAD
public class ChainingCheck extends BaseCheck {
=======
public class ChainingCheck extends AbstractCheck {
>>>>>>> compatible

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF};
	}

	public void setAllowedClassNames(String allowedClassNames) {
		_allowedClassNames = StringUtil.split(allowedClassNames);
	}

	public void setAllowedMethodNames(String allowedMethodNames) {
		_allowedMethodNames = StringUtil.split(allowedMethodNames);
	}

	public void setAllowedVariableTypeNames(String allowedVariableTypeNames) {
		_allowedVariableTypeNames = StringUtil.split(allowedVariableTypeNames);
	}

	@Override
<<<<<<< HEAD
	protected void doVisitToken(DetailAST detailAST) {
=======
	public void visitToken(DetailAST detailAST) {
>>>>>>> compatible
		List<DetailAST> methodCallASTList = DetailASTUtil.getAllChildTokens(
			detailAST, true, TokenTypes.METHOD_CALL);

		for (DetailAST methodCallAST : methodCallASTList) {
			DetailAST dotAST = methodCallAST.findFirstToken(TokenTypes.DOT);

			if (dotAST != null) {
				List<DetailAST> childMethodCallASTList =
					DetailASTUtil.getAllChildTokens(
						dotAST, false, TokenTypes.METHOD_CALL);

				// Only check the method that is first in the chain

				if (!childMethodCallASTList.isEmpty()) {
					continue;
				}
			}

<<<<<<< HEAD
			if (_isInsideAnonymousClassVariableDefinition(methodCallAST)) {
				continue;
			}

=======
>>>>>>> compatible
			List<String> chainedMethodNames = _getChainedMethodNames(
				methodCallAST);

			if (chainedMethodNames.size() == 1) {
				continue;
			}

<<<<<<< HEAD
			_checkMethodName(
				chainedMethodNames, "getClass", methodCallAST, detailAST);

			if (chainedMethodNames.size() == 2) {
				continue;
			}

=======
>>>>>>> compatible
			if (_isAllowedChainingMethodCall(
					detailAST, methodCallAST, chainedMethodNames)) {

				_checkStyling(methodCallAST);

				continue;
			}

<<<<<<< HEAD
=======
			_checkMethodName(
				chainedMethodNames, "getClass", methodCallAST, detailAST);

			if (chainedMethodNames.size() == 2) {
				continue;
			}

>>>>>>> compatible
			int concatsCount = Collections.frequency(
				chainedMethodNames, "concat");

			if (concatsCount > 2) {
				log(methodCallAST.getLineNo(), _MSG_AVOID_TOO_MANY_CONCAT);

				continue;
			}

			if ((chainedMethodNames.size() == 3) && (concatsCount == 2)) {
				continue;
			}

			log(
				methodCallAST.getLineNo(), _MSG_AVOID_CHAINING_MULTIPLE,
				DetailASTUtil.getMethodName(methodCallAST));
		}
	}

	private void _checkMethodName(
		List<String> chainedMethodNames, String methodName,
		DetailAST methodCallAST, DetailAST detailAST) {

		String firstMethodName = chainedMethodNames.get(0);

		if (firstMethodName.equals(methodName) &&
<<<<<<< HEAD
			!_isInsideConstructorThisCall(methodCallAST, detailAST) &&
			!DetailASTUtil.hasParentWithTokenType(
				methodCallAST, TokenTypes.SUPER_CTOR_CALL)) {
=======
			!_isInsideConstructorThisCall(methodCallAST, detailAST)) {
>>>>>>> compatible

			log(methodCallAST.getLineNo(), _MSG_AVOID_CHAINING, methodName);
		}
	}

	private void _checkStyling(DetailAST methodCallAST) {
<<<<<<< HEAD
		for (int i = DetailASTUtil.getStartLine(methodCallAST) + 1;
			 i <= DetailASTUtil.getEndLine(methodCallAST); i++) {

			String line = StringUtil.trim(getLine(i - 1));
=======
		FileContents fileContents = getFileContents();

		for (int i = DetailASTUtil.getStartLine(methodCallAST) + 1;
			 i <= DetailASTUtil.getEndLine(methodCallAST); i++) {

			String line = StringUtil.trim(fileContents.getLine(i - 1));
>>>>>>> compatible

			if (line.startsWith(").")) {
				return;
			}
		}

		log(
			methodCallAST.getLineNo(), _MSG_INCORRECT_STYLING,
			DetailASTUtil.getMethodName(methodCallAST));
	}

	private List<String> _getChainedMethodNames(DetailAST methodCallAST) {
		List<String> chainedMethodNames = new ArrayList<>();

		chainedMethodNames.add(DetailASTUtil.getMethodName(methodCallAST));

		while (true) {
			DetailAST parentAST = methodCallAST.getParent();

			if (parentAST.getType() != TokenTypes.DOT) {
				return chainedMethodNames;
			}

			methodCallAST = parentAST.getParent();

			if (methodCallAST.getType() != TokenTypes.METHOD_CALL) {
				return chainedMethodNames;
			}

			chainedMethodNames.add(DetailASTUtil.getMethodName(methodCallAST));
		}
	}

<<<<<<< HEAD
	private DetailAST _getOuterMethodCallAST(DetailAST detailAST) {
		while (true) {
			if ((detailAST.getType() != TokenTypes.DOT) &&
				(detailAST.getType() != TokenTypes.METHOD_CALL)) {

				return null;
			}

			DetailAST parentAST = detailAST.getParent();

			if ((detailAST.getType() == TokenTypes.METHOD_CALL) &&
				(parentAST.getType() != TokenTypes.DOT)) {

				break;
			}

			detailAST = parentAST;
		}

		while (true) {
			DetailAST parentAST = detailAST.getParent();

			if (parentAST == null) {
				return null;
			}

			if (parentAST.getType() == TokenTypes.METHOD_CALL) {
				detailAST = parentAST;

				break;
			}

			detailAST = parentAST;
		}

		while (true) {
			DetailAST childAST = detailAST.getFirstChild();

			if ((detailAST.getType() != TokenTypes.DOT) &&
				(detailAST.getType() != TokenTypes.METHOD_CALL)) {

				return null;
			}

			if ((detailAST.getType() == TokenTypes.DOT) &&
				(childAST.getType() != TokenTypes.METHOD_CALL)) {

				return detailAST.getParent();
			}

			detailAST = childAST;
		}
=======
	private DetailAST _getClassAST(DetailAST detailAST) {
		DetailAST parentAST = detailAST.getParent();

		while (true) {
			if (parentAST.getParent() == null) {
				break;
			}

			return parentAST.getParent();
		}

		return null;
	}

	private String _getVariableType(DetailAST detailAST, String variableName) {
		List<DetailAST> definitionASTList = new ArrayList<>();

		if (variableName.matches("_[a-z].*")) {
			definitionASTList = DetailASTUtil.getAllChildTokens(
				_getClassAST(detailAST), true, TokenTypes.PARAMETER_DEF,
				TokenTypes.VARIABLE_DEF);
		}
		else if (variableName.matches("[a-z].*")) {
			definitionASTList = DetailASTUtil.getAllChildTokens(
				detailAST, true, TokenTypes.PARAMETER_DEF,
				TokenTypes.VARIABLE_DEF);
		}

		for (DetailAST definitionAST : definitionASTList) {
			DetailAST nameAST = definitionAST.findFirstToken(TokenTypes.IDENT);

			if (nameAST == null) {
				continue;
			}

			String name = nameAST.getText();

			if (name.equals(variableName)) {
				DetailAST typeAST = definitionAST.findFirstToken(
					TokenTypes.TYPE);

				nameAST = typeAST.findFirstToken(TokenTypes.IDENT);

				if (nameAST == null) {
					return null;
				}

				return nameAST.getText();
			}
		}

		return null;
>>>>>>> compatible
	}

	private boolean _isAllowedChainingMethodCall(
		DetailAST detailAST, DetailAST methodCallAST,
		List<String> chainedMethodNames) {

<<<<<<< HEAD
		if (_isInsideConstructorThisCall(methodCallAST, detailAST) ||
			DetailASTUtil.hasParentWithTokenType(
				methodCallAST, TokenTypes.SUPER_CTOR_CALL)) {

			return true;
		}

=======
>>>>>>> compatible
		for (String allowedMethodName : _allowedMethodNames) {
			if (chainedMethodNames.contains(allowedMethodName)) {
				return true;
			}
		}

		DetailAST dotAST = methodCallAST.findFirstToken(TokenTypes.DOT);

		if (dotAST == null) {
			FileContents fileContents = getFileContents();

			FileText fileText = fileContents.getText();

			String content = (String)fileText.getFullText();

			if (content.contains("extends PowerMockito")) {
				return true;
			}

			return false;
		}

<<<<<<< HEAD
		DetailAST nameAST = null;

		DetailAST firstChild = dotAST.getFirstChild();

		if (firstChild.getType() == TokenTypes.LITERAL_NEW) {
			nameAST = firstChild.findFirstToken(TokenTypes.IDENT);
		}
		else {
			nameAST = dotAST.findFirstToken(TokenTypes.IDENT);
		}
=======
		DetailAST nameAST = dotAST.findFirstToken(TokenTypes.IDENT);
>>>>>>> compatible

		String classOrVariableName = nameAST.getText();

		for (String allowedClassName : _allowedClassNames) {
			if (classOrVariableName.matches(allowedClassName)) {
				return true;
			}
		}

<<<<<<< HEAD
		Set<String> variableTypeNames = DetailASTUtil.getVariableTypeNames(
			detailAST, classOrVariableName);

		for (String variableTypeName : variableTypeNames) {
			for (String allowedVariableTypeName : _allowedVariableTypeNames) {
				if (variableTypeName.matches(allowedVariableTypeName)) {
					return true;
				}
			}
		}

		DetailAST outerMethodCallAST = _getOuterMethodCallAST(methodCallAST);

		if (outerMethodCallAST != null) {
			return _isAllowedChainingMethodCall(
				detailAST, outerMethodCallAST,
				_getChainedMethodNames(outerMethodCallAST));
		}

		return false;
	}

	private boolean _isInsideAnonymousClassVariableDefinition(
		DetailAST detailAST) {

		DetailAST parentAST = detailAST.getParent();

		while (parentAST != null) {
			if ((parentAST.getType() == TokenTypes.CTOR_DEF) ||
				(parentAST.getType() == TokenTypes.METHOD_DEF)) {

				return false;
			}

			if (parentAST.getType() == TokenTypes.VARIABLE_DEF) {
				parentAST = parentAST.getParent();

				if (parentAST.getType() == TokenTypes.OBJBLOCK) {
					return true;
				}

				return false;
			}

			parentAST = parentAST.getParent();
=======
		String variableType = _getVariableType(detailAST, classOrVariableName);

		if (variableType != null) {
			for (String allowedVariableTypeName : _allowedVariableTypeNames) {
				if (variableType.matches(allowedVariableTypeName)) {
					return true;
				}
			}
>>>>>>> compatible
		}

		return false;
	}

	private boolean _isInsideConstructorThisCall(
		DetailAST methodCallAST, DetailAST detailAST) {

		if (detailAST.getType() != TokenTypes.CTOR_DEF) {
			return false;
		}

		DetailAST parentAST = methodCallAST.getParent();

		while (parentAST != null) {
			String parentASTText = parentAST.getText();

			if ((parentAST.getType() == TokenTypes.CTOR_CALL) &&
				parentASTText.equals("this")) {

				return true;
			}

			parentAST = parentAST.getParent();
		}

		return false;
	}

	private static final String _MSG_AVOID_CHAINING = "chaining.avoid";

	private static final String _MSG_AVOID_CHAINING_MULTIPLE =
		"chaining.avoid.multiple";

	private static final String _MSG_AVOID_TOO_MANY_CONCAT =
		"concat.avoid.too.many";

	private static final String _MSG_INCORRECT_STYLING = "styling.incorrect";

	private String[] _allowedClassNames = new String[0];
	private String[] _allowedMethodNames = new String[0];
	private String[] _allowedVariableTypeNames = new String[0];

}