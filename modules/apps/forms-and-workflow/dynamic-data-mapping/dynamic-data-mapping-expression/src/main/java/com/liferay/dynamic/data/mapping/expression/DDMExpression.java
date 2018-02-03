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

package com.liferay.dynamic.data.mapping.expression;

<<<<<<< HEAD
import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.expression.model.Expression;

=======
>>>>>>> compatible
import java.math.MathContext;

import java.util.Map;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
<<<<<<< HEAD
@ProviderType
=======
>>>>>>> compatible
public interface DDMExpression<T> {

	public T evaluate() throws DDMExpressionException;

<<<<<<< HEAD
	public Expression getModel();

=======
>>>>>>> compatible
	public Map<String, VariableDependencies> getVariableDependenciesMap()
		throws DDMExpressionException;

	public void setBooleanVariableValue(
		String variableName, Boolean variableValue);

<<<<<<< HEAD
	public void setDDMExpressionFunction(
		String functionName, DDMExpressionFunction ddmExpressionFunction);

=======
>>>>>>> compatible
	public void setDoubleVariableValue(
		String variableName, Double variableValue);

	public void setExpressionStringVariableValue(
		String variableName, String variableValue);

	public void setFloatVariableValue(String variableName, Float variableValue);

	public void setIntegerVariableValue(
		String variableName, Integer variableValue);

	public void setLongVariableValue(String variableName, Long variableValue);

<<<<<<< HEAD
	/**
	 * @deprecated As of 2.1.0, with no direct replacement
	 */
	@Deprecated
	public void setMathContext(MathContext mathContext);

	public void setNumberVariableValue(
		String variableName, Number variableValue);

	public void setObjectVariableValue(
		String variableName, Object variableValue);

=======
	public void setMathContext(MathContext mathContext);

>>>>>>> compatible
	public void setStringVariableValue(
			String variableName, String variableValue)
		throws DDMExpressionException;

}