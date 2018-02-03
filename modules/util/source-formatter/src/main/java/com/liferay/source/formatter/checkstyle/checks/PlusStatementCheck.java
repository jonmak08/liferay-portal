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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.checkstyle.util.DetailASTUtil;

<<<<<<< HEAD
=======
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
>>>>>>> compatible
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtils;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.HashSet;
import java.util.List;
import java.util.Set;
>>>>>>> compatible

/**
 * @author Hugo Huijser
 */
<<<<<<< HEAD
public class PlusStatementCheck extends StringConcatenationCheck {
=======
public class PlusStatementCheck extends AbstractCheck {
>>>>>>> compatible

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.PLUS};
	}

<<<<<<< HEAD
	@Override
	protected void doVisitToken(DetailAST detailAST) {
		_checkPlusOperator(detailAST);
	}

	private void _checkPlusOperator(DetailAST detailAST) {
=======
	public void setMaxLineLength(int maxLineLength) {
		_maxLineLength = maxLineLength;
	}

	@Override
	public void visitToken(DetailAST detailAST) {
		_checkMultiLinesPlusStatement(detailAST);
>>>>>>> compatible
		_checkTabbing(detailAST);

		if (detailAST.getChildCount() != 2) {
			return;
		}

		DetailAST firstChild = detailAST.getFirstChild();

		String literalString1 = _getLiteralString(firstChild);

		if (literalString1 == null) {
			return;
		}

		DetailAST lastChild = detailAST.getLastChild();

		String literalString2 = _getLiteralString(lastChild);

		if (literalString2 == null) {
			return;
		}

		if (firstChild.getLineNo() == lastChild.getLineNo()) {
			log(
<<<<<<< HEAD
				firstChild.getLineNo(), MSG_COMBINE_LITERAL_STRINGS,
=======
				firstChild.getLineNo(), _MSG_COMBINE_LITERAL_STRINGS,
>>>>>>> compatible
				literalString1, literalString2);

			return;
		}

		if (_isRegexPattern(detailAST)) {
			return;
		}

<<<<<<< HEAD
		checkLiteralStringStartAndEndCharacter(
			literalString1, literalString2, detailAST.getLineNo());
=======
		if (literalString1.endsWith(StringPool.SLASH)) {
			log(
				detailAST.getLineNo(), _MSG_INVALID_END_CHARACTER,
				literalString1.charAt(literalString1.length() - 1));
		}

		if (literalString2.startsWith(StringPool.SPACE) ||
			(!literalString1.endsWith(StringPool.SPACE) &&
			 literalString2.matches("^[-:;.].*"))) {

			log(
				lastChild.getLineNo(), _MSG_INVALID_START_CHARACTER,
				literalString2.charAt(0));

			return;
		}
>>>>>>> compatible

		String line1 = getLine(lastChild.getLineNo() - 2);
		String line2 = getLine(lastChild.getLineNo() - 1);

		if (_getLeadingTabCount(line1) == _getLeadingTabCount(line2)) {
			return;
		}

		int lineLength1 = CommonUtils.lengthExpandedTabs(
			line1, line1.length(), getTabWidth());

		String trimmedLine2 = StringUtil.trim(line2);

<<<<<<< HEAD
		if ((lineLength1 + trimmedLine2.length() - 4) <= maxLineLength) {
			log(
				lastChild.getLineNo(), MSG_COMBINE_LITERAL_STRINGS,
=======
		if ((lineLength1 + trimmedLine2.length() - 4) <= _maxLineLength) {
			log(
				lastChild.getLineNo(), _MSG_COMBINE_LITERAL_STRINGS,
>>>>>>> compatible
				literalString1, literalString2);

			return;
		}

		DetailAST parentAST = detailAST.getParent();

		if ((parentAST.getType() == TokenTypes.PLUS) &&
<<<<<<< HEAD
			((lineLength1 + literalString2.length()) <= maxLineLength)) {

			log(
				detailAST.getLineNo(), MSG_COMBINE_LITERAL_STRINGS,
=======
			((lineLength1 + literalString2.length()) <= _maxLineLength)) {

			log(
				detailAST.getLineNo(), _MSG_COMBINE_LITERAL_STRINGS,
>>>>>>> compatible
				literalString1, literalString2);

			return;
		}

<<<<<<< HEAD
		int pos = getStringBreakPos(
			literalString1, literalString2, maxLineLength - lineLength1);

		if (pos != -1) {
			log(
				lastChild.getLineNo(), MSG_MOVE_LITERAL_STRING,
=======
		int pos = _getStringBreakPos(
			literalString1, literalString2, _maxLineLength - lineLength1);

		if (pos != -1) {
			log(
				lastChild.getLineNo(), _MSG_MOVE_LITERAL_STRING,
>>>>>>> compatible
				literalString2.substring(0, pos + 1));
		}
	}

<<<<<<< HEAD
=======
	private void _checkMultiLinesPlusStatement(DetailAST detailAST) {
		DetailAST firstChildAST = detailAST.getFirstChild();

		if (firstChildAST.getType() == TokenTypes.PLUS) {
			return;
		}

		if (DetailASTUtil.hasParentWithTokenType(
				detailAST, TokenTypes.ANNOTATION) ||
			!DetailASTUtil.hasParentWithTokenType(
				detailAST, TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF)) {

			return;
		}

		Set<Integer> lineNumbers = new HashSet<>();

		lineNumbers.add(detailAST.getLineNo());

		DetailAST parentAST = detailAST;

		while (true) {
			if (parentAST.getType() != TokenTypes.PLUS) {
				break;
			}

			DetailAST lastChildAST = parentAST.getLastChild();

			lineNumbers.add(lastChildAST.getLineNo());

			parentAST = parentAST.getParent();
		}

		if (lineNumbers.size() > 3) {
			log(detailAST.getLineNo(), _MSG_STATEMENT_TOO_LONG);
		}
	}

>>>>>>> compatible
	private void _checkTabbing(DetailAST detailAST) {
		DetailAST afterPlusAST = detailAST.getLastChild();

		if (afterPlusAST.getType() == TokenTypes.RPAREN) {
			while (afterPlusAST.getType() != TokenTypes.LPAREN) {
				afterPlusAST = afterPlusAST.getPreviousSibling();
			}
		}

		int afterPlusLineNo = DetailASTUtil.getStartLine(afterPlusAST);

		if (afterPlusLineNo == detailAST.getLineNo()) {
			return;
		}

		String line1 = getLine(detailAST.getLineNo() - 1);
		String line2 = getLine(afterPlusLineNo - 1);

		int tabCount = _getLeadingTabCount(line1);

		if ((tabCount + 1) != _getLeadingTabCount(line2)) {
			log(afterPlusLineNo, _MSG_INCORRECT_TABBING, tabCount + 1);
		}
	}

	private int _getLeadingTabCount(String line) {
		int leadingTabCount = 0;

		while (line.startsWith(StringPool.TAB)) {
			line = line.substring(1);

			leadingTabCount++;
		}

		return leadingTabCount;
	}

	private String _getLiteralString(DetailAST detailAST) {
		String literalString = null;

		if (detailAST.getType() == TokenTypes.STRING_LITERAL) {
			literalString = detailAST.getText();
		}
		else if ((detailAST.getType() == TokenTypes.PLUS) &&
				 (detailAST.getChildCount() == 2)) {

			DetailAST lastChild = detailAST.getLastChild();

			if (lastChild.getType() == TokenTypes.STRING_LITERAL) {
				literalString = lastChild.getText();
			}
		}

		if (literalString != null) {
			return literalString.substring(1, literalString.length() - 1);
		}

		return null;
	}

<<<<<<< HEAD
=======
	private int _getStringBreakPos(String s1, String s2, int i) {
		if (s2.startsWith(StringPool.SLASH)) {
			int pos = s2.lastIndexOf(StringPool.SLASH, i);

			if (pos > 0) {
				return pos - 1;
			}

			return -1;
		}

		if (s1.endsWith(StringPool.DASH)) {
			return Math.max(
				s2.lastIndexOf(StringPool.DASH, i - 1),
				s2.lastIndexOf(StringPool.SPACE, i - 1));
		}

		if (s1.endsWith(StringPool.PERIOD)) {
			return Math.max(
				s2.lastIndexOf(StringPool.PERIOD, i - 1),
				s2.lastIndexOf(StringPool.SPACE, i - 1));
		}

		if (s1.endsWith(StringPool.SPACE)) {
			return s2.lastIndexOf(StringPool.SPACE, i - 1);
		}

		return -1;
	}

>>>>>>> compatible
	private boolean _isRegexPattern(DetailAST detailAST) {
		DetailAST parentAST = detailAST.getParent();

		while (parentAST != null) {
			if (parentAST.getType() != TokenTypes.METHOD_CALL) {
				parentAST = parentAST.getParent();

				continue;
			}

			DetailAST firstChild = parentAST.getFirstChild();

			if (firstChild.getType() != TokenTypes.DOT) {
				return false;
			}

			List<DetailAST> nameASTList = DetailASTUtil.getAllChildTokens(
				firstChild, false, TokenTypes.IDENT);

			if (nameASTList.size() != 2) {
				return false;
			}

			DetailAST classNameAST = nameASTList.get(0);
			DetailAST methodNameAST = nameASTList.get(1);

			String methodCallClassName = classNameAST.getText();
			String methodCallMethodName = methodNameAST.getText();

<<<<<<< HEAD
			if (methodCallMethodName.equals("matches") ||
				(methodCallClassName.equals("Pattern") &&
				 methodCallMethodName.equals("compile"))) {
=======
			if (methodCallClassName.equals("Pattern") &&
				methodCallMethodName.equals("compile")) {
>>>>>>> compatible

				return true;
			}

			return false;
		}

		return false;
	}

<<<<<<< HEAD
	private static final String _MSG_INCORRECT_TABBING = "tabbing.incorrect";

=======
	private static final String _MSG_COMBINE_LITERAL_STRINGS =
		"literal.string.combine";

	private static final String _MSG_INCORRECT_TABBING = "tabbing.incorrect";

	private static final String _MSG_INVALID_END_CHARACTER =
		"end.character.invalid";

	private static final String _MSG_INVALID_START_CHARACTER =
		"start.character.invalid";

	private static final String _MSG_MOVE_LITERAL_STRING =
		"literal.string.move";

	private static final String _MSG_STATEMENT_TOO_LONG =
		"plus.statement.too.long";

	private int _maxLineLength = 80;

>>>>>>> compatible
}