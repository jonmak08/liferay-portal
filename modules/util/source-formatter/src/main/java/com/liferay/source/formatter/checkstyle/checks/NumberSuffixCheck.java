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

<<<<<<< HEAD
=======
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
>>>>>>> compatible
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * @author Hugo Huijser
 */
<<<<<<< HEAD
public class NumberSuffixCheck extends BaseCheck {
=======
public class NumberSuffixCheck extends AbstractCheck {
>>>>>>> compatible

	@Override
	public int[] getDefaultTokens() {
		return new int[] {
			TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG
		};
	}

	@Override
<<<<<<< HEAD
	protected void doVisitToken(DetailAST detailAST) {
=======
	public void visitToken(DetailAST detailAST) {
>>>>>>> compatible
		if (detailAST.getType() == TokenTypes.NUM_DOUBLE) {
			_checkType(detailAST, "double", "d");
		}
		else if (detailAST.getType() == TokenTypes.NUM_FLOAT) {
			_checkType(detailAST, "float", "f");
		}
		else if (detailAST.getType() == TokenTypes.NUM_LONG) {
			_checkType(detailAST, "long", "l");
		}
	}

	private void _checkType(DetailAST detailAST, String type, String suffix) {
		String text = detailAST.getText();

		if (text.endsWith(suffix)) {
			log(
				detailAST.getLineNo(), _MSG_INCORRECT_SUFFIX,
				StringUtil.toUpperCase(suffix), type);
		}
	}

	private static final String _MSG_INCORRECT_SUFFIX = "suffix.incorrect";

}