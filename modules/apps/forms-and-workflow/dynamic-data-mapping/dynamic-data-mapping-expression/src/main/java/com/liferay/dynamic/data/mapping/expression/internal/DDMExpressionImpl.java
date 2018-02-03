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

package com.liferay.dynamic.data.mapping.expression.internal;

import com.liferay.dynamic.data.mapping.expression.DDMExpression;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionException;
<<<<<<< HEAD
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;
import com.liferay.dynamic.data.mapping.expression.VariableDependencies;
import com.liferay.dynamic.data.mapping.expression.internal.parser.DDMExpressionLexer;
import com.liferay.dynamic.data.mapping.expression.internal.parser.DDMExpressionParser;
import com.liferay.dynamic.data.mapping.expression.internal.parser.DDMExpressionParser.ExpressionContext;
import com.liferay.dynamic.data.mapping.expression.model.Expression;
import com.liferay.portal.kernel.util.ListUtil;

import java.math.MathContext;

import java.util.HashMap;
import java.util.HashSet;
=======
import com.liferay.dynamic.data.mapping.expression.VariableDependencies;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import java.util.HashMap;
>>>>>>> compatible
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

<<<<<<< HEAD
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * @author Miguel Angelo Caldas Gallindo
 * @author Marcellus Tavares
=======
/**
 * @author Miguel Angelo Caldas Gallindo
>>>>>>> compatible
 */
public class DDMExpressionImpl<T> implements DDMExpression<T> {

	public DDMExpressionImpl(String expressionString, Class<T> expressionClass)
		throws DDMExpressionException {

<<<<<<< HEAD
		if ((expressionString == null) || expressionString.isEmpty()) {
			throw new IllegalArgumentException();
		}

		_expressionString = expressionString;
		_expressionClass = expressionClass;

		_expressionContext = createExpressionContext();

		registerExpressionFunctionsAndVariables();
		registerExpressionModel();
	}

	@Override
	public T evaluate() throws DDMExpressionException {
		Set<String> undefinedFunctionNames = getUndefinedFunctionNames();

		if (!undefinedFunctionNames.isEmpty()) {
			throw new DDMExpressionException.FunctionNotDefined(
				undefinedFunctionNames);
		}

		try {
			DDMExpressionEvaluatorVisitor ddmExpressionEvaluatorVisitor =
				createDDMExpressionEvaluatorVisitor();

			Object result = _expressionContext.accept(
				ddmExpressionEvaluatorVisitor);

			return (T)toRetunType(result);
		}
		catch (DDMExpressionException ddmee) {
			throw ddmee;
		}
=======
		TokenExtractor tokenExtractor = new TokenExtractor(expressionString);

		Map<String, String> variableMap = tokenExtractor.getVariableMap();

		for (Map.Entry<String, String> entry : variableMap.entrySet()) {
			String variableName = entry.getKey();

			Variable variable = new Variable(variableName);

			_variables.put(variableName, variable);

			String token = entry.getValue();

			if (token != null) {
				setStringVariableValue(variableName, token);
			}
		}

		_expressionString = tokenExtractor.getExpression();

		_expressionClass = expressionClass;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T evaluate() throws DDMExpressionException {
		try {
			com.udojava.evalex.Expression expression =
				new com.udojava.evalex.Expression(_expressionString);

			for (Map.Entry<String, Variable> entry : _variables.entrySet()) {
				BigDecimal variableValue = getVariableValue(entry.getValue());

				expression.setVariable(entry.getKey(), variableValue);
			}

			BigDecimal result = evaluate(expression);

			return (T)toRetunType(result);
		}
>>>>>>> compatible
		catch (Exception e) {
			throw new DDMExpressionException(e);
		}
	}

	@Override
<<<<<<< HEAD
	public Expression getModel() {
		return _expressionModel;
	}

	@Override
=======
>>>>>>> compatible
	public Map<String, VariableDependencies> getVariableDependenciesMap()
		throws DDMExpressionException {

		Map<String, VariableDependencies> variableDependenciesMap =
			new HashMap<>();

		List<Variable> variables = ListUtil.fromCollection(_variables.values());

		for (Variable variable : variables) {
			populateVariableDependenciesMap(variable, variableDependenciesMap);
		}

		return variableDependenciesMap;
	}

	@Override
	public void setBooleanVariableValue(
		String variableName, Boolean variableValue) {

<<<<<<< HEAD
		setVariableValue(variableName, variableValue);
	}

	@Override
	public void setDDMExpressionFunction(
		String functionName, DDMExpressionFunction ddmExpressionFunction) {

		_ddmExpressionFunctions.put(functionName, ddmExpressionFunction);
=======
		setVariableValue(variableName, encode(variableValue));
>>>>>>> compatible
	}

	@Override
	public void setDoubleVariableValue(
		String variableName, Double variableValue) {

<<<<<<< HEAD
		setVariableValue(variableName, variableValue);
=======
		setVariableValue(variableName, new BigDecimal(variableValue));
>>>>>>> compatible
	}

	@Override
	public void setExpressionStringVariableValue(
		String variableName, String variableValue) {

		Variable variable = _variables.get(variableName);

		if (variable == null) {
			return;
		}

		variable.setExpressionString(variableValue);
	}

	@Override
	public void setFloatVariableValue(
		String variableName, Float variableValue) {

<<<<<<< HEAD
		setVariableValue(variableName, variableValue.doubleValue());
=======
		setVariableValue(variableName, new BigDecimal(variableValue));
>>>>>>> compatible
	}

	@Override
	public void setIntegerVariableValue(
		String variableName, Integer variableValue) {

<<<<<<< HEAD
		setVariableValue(variableName, variableValue.doubleValue());
=======
		setVariableValue(variableName, new BigDecimal(variableValue));
>>>>>>> compatible
	}

	@Override
	public void setLongVariableValue(String variableName, Long variableValue) {
<<<<<<< HEAD
		setVariableValue(variableName, variableValue.doubleValue());
	}

	/**
	 * @deprecated As of 2.1.0
	 */
	@Deprecated
	@Override
	public void setMathContext(MathContext mathContext) {
	}

	@Override
	public void setNumberVariableValue(
		String variableName, Number variableValue) {

		setVariableValue(variableName, variableValue.doubleValue());
	}

	@Override
	public void setObjectVariableValue(
		String variableName, Object variableValue) {

		Variable variable = _variables.get(variableName);

		if (variable == null) {
			return;
		}

		variable.setValue(variableValue);

		_variableValues.put(variableName, variableValue);
	}

	@Override
	public void setStringVariableValue(
			String variableName, String variableValue)
		throws DDMExpressionException {

		setVariableValue(variableName, variableValue);
	}

	protected void assertResultTypeClass(
			Class<?> expectedResultTypeClass, Class<?> resultTypeClass)
		throws DDMExpressionException {

		if (!expectedResultTypeClass.isAssignableFrom(resultTypeClass)) {
			throw new DDMExpressionException.IncompatipleReturnType();
		}
	}

	protected DDMExpressionEvaluatorVisitor
			createDDMExpressionEvaluatorVisitor()
		throws DDMExpressionException {

		DDMExpressionEvaluatorVisitor ddmExpressionEvaluatorVisitor =
			new DDMExpressionEvaluatorVisitor();

		// Functions

		ddmExpressionEvaluatorVisitor.addFunctions(_ddmExpressionFunctions);

		// Variables

		for (Map.Entry<String, Variable> entry : _variables.entrySet()) {
			ddmExpressionEvaluatorVisitor.addVariable(
				entry.getKey(), getVariableValue(entry.getValue()));
		}

		return ddmExpressionEvaluatorVisitor;
	}

	protected DDMExpression<Object> createExpression(String expressionString)
		throws DDMExpressionException {

		DDMExpressionImpl<Object> ddmExpression = new DDMExpressionImpl<>(
			expressionString, Object.class);

		for (String variableName : ddmExpression.getExpressionVariableNames()) {
			Variable variable = _variables.get(variableName);

			if (variable != null) {
				Object variableValue = getVariableValue(variable);

				ddmExpression.setVariableValue(variableName, variableValue);
			}
		}

		return ddmExpression;
	}

	protected DDMExpression<Object> createExpression(Variable variable)
=======
		setVariableValue(variableName, new BigDecimal(variableValue));
	}

	@Override
	public void setMathContext(MathContext mathContext) {
		_mathContext = mathContext;
	}

	@Override
	public void setStringVariableValue(
			String variableName, String variableValue)
		throws DDMExpressionException {

		Double doubleValue = parseDoubleValue(variableValue);

		if (doubleValue == null) {
			setVariableValue(variableName, encode(variableValue));

			return;
		}

		if (doubleValue.isNaN() || doubleValue.isInfinite()) {
			throw new DDMExpressionException.NumberExceedsSupportedRange();
		}
		else {
			setVariableValue(variableName, new BigDecimal(variableValue));
		}
	}

	protected Boolean decodeBoolean(BigDecimal bigDecimal) {
		if (bigDecimal.equals(BigDecimal.ONE)) {
			return Boolean.TRUE;
		}
		else {
			return Boolean.FALSE;
		}
	}

	protected String decodeString(BigDecimal bigDecimal) {
		if (bigDecimal.equals(BigDecimal.ZERO)) {
			return StringPool.BLANK;
		}

		BigInteger bigInteger = new BigInteger(bigDecimal.toString());

		return new String(bigInteger.toByteArray());
	}

	protected BigDecimal encode(Boolean variableValue) {
		if (variableValue.equals(Boolean.TRUE)) {
			return BigDecimal.ONE;
		}

		return BigDecimal.ZERO;
	}

	protected BigDecimal encode(String variableValue) {
		if (Validator.isNull(variableValue)) {
			return BigDecimal.ZERO;
		}

		BigInteger bigInteger = new BigInteger(variableValue.getBytes());

		return new BigDecimal(bigInteger);
	}

	protected BigDecimal evaluate(com.udojava.evalex.Expression expression) {
		setExpressionCustomFunctions(expression);
		setExpressionCustomOperators(expression);
		setExpressionMathContext(expression);

		return expression.eval();
	}

	protected com.udojava.evalex.Expression getExpression(
			String expressionString)
		throws DDMExpressionException {

		com.udojava.evalex.Expression expression =
			new com.udojava.evalex.Expression(expressionString);

		TokenExtractor tokenExtractor = new TokenExtractor(expressionString);

		Map<String, String> variableMap = tokenExtractor.getVariableMap();

		for (String key : variableMap.keySet()) {
			Variable variable = _variables.get(key);

			if (variable != null) {
				BigDecimal variableValue = getVariableValue(variable);

				expression.setVariable(key, variableValue);
			}
		}

		return expression;
	}

	protected com.udojava.evalex.Expression getExpression(Variable variable)
>>>>>>> compatible
		throws DDMExpressionException {

		if (variable.getExpressionString() == null) {
			return null;
		}

<<<<<<< HEAD
		DDMExpression<Object> ddmExpression = createExpression(
			variable.getExpressionString());

		return ddmExpression;
	}

	protected ExpressionContext createExpressionContext()
		throws DDMExpressionException {

		try {
			CharStream charStream = new ANTLRInputStream(_expressionString);

			DDMExpressionLexer ddmExpressionLexer = new DDMExpressionLexer(
				charStream);

			DDMExpressionParser ddmExpressionParser = new DDMExpressionParser(
				new CommonTokenStream(ddmExpressionLexer));

			ddmExpressionParser.setErrorHandler(new BailErrorStrategy());

			return ddmExpressionParser.expression();
		}
		catch (Exception e) {
			throw new DDMExpressionException.InvalidSyntax(e);
		}
	}

	protected Set<String> getExpressionFunctionNames() {
		return _expressionFunctionNames;
	}

	protected Set<String> getExpressionVariableNames() {
		return _variables.keySet();
	}

	protected Set<String> getUndefinedFunctionNames() {
		Set<String> undefinedFunctionNames = new HashSet<>(
			getExpressionFunctionNames());

		undefinedFunctionNames.removeAll(_ddmExpressionFunctions.keySet());

		return undefinedFunctionNames;
	}

	protected Object getVariableValue(Variable variable)
		throws DDMExpressionException {

		Object variableValue = _variableValues.get(variable.getName());

		if (variableValue != null) {
			return variableValue;
		}

		DDMExpression<Object> ddmExpression = createExpression(variable);

		variableValue = ddmExpression.evaluate();

		_variableValues.put(variable.getName(), variableValue);

		return variableValue;
=======
		com.udojava.evalex.Expression expression = getExpression(
			variable.getExpressionString());

		return expression;
	}

	protected BigDecimal getVariableValue(Variable variable)
		throws DDMExpressionException {

		BigDecimal variableValue = _variableValues.get(variable.getName());

		if (variableValue != null) {
			return variableValue;
		}

		com.udojava.evalex.Expression expression = getExpression(variable);

		if (expression == null) {
			return variable.getValue();
		}

		variableValue = evaluate(expression);

		_variableValues.put(variable.getName(), variableValue);

		return variableValue;
	}

	protected boolean isStringBlank(BigDecimal... bigDecimals) {
		for (BigDecimal bigDecimal : bigDecimals) {
			if (!bigDecimal.equals(BigDecimal.ZERO)) {
				return false;
			}
		}

		return true;
	}

	protected Double parseDoubleValue(String value) {
		try {
			return Double.parseDouble(value);
		}
		catch (NumberFormatException nfe) {
			return null;
		}
>>>>>>> compatible
	}

	protected VariableDependencies populateVariableDependenciesMap(
			Variable variable,
			Map<String, VariableDependencies> variableDependenciesMap)
		throws DDMExpressionException {

		VariableDependencies variableDependencies = variableDependenciesMap.get(
			variable.getName());

		if (variableDependencies != null) {
			return variableDependencies;
		}

		variableDependencies = new VariableDependencies(variable.getName());

		if (variable.getExpressionString() != null) {
<<<<<<< HEAD
			DDMExpressionImpl<?> ddmExpression = new DDMExpressionImpl<>(
				variable.getExpressionString(), Object.class);

			for (String variableName :
					ddmExpression.getExpressionVariableNames()) {

=======
			TokenExtractor tokensExtractor = new TokenExtractor(
				variable.getExpressionString());

			Map<String, String> variableMap = tokensExtractor.getVariableMap();

			Set<String> variableNames = variableMap.keySet();

			for (String variableName : variableNames) {
>>>>>>> compatible
				if (!_variables.containsKey(variableName)) {
					Variable newVariable = new Variable(variableName);

					_variables.put(variableName, newVariable);
<<<<<<< HEAD
=======

					String token = variableMap.get(variableName);

					if (token != null) {
						setStringVariableValue(variableName, token);
					}
>>>>>>> compatible
				}

				VariableDependencies variableVariableDependencies =
					populateVariableDependenciesMap(
						_variables.get(variableName), variableDependenciesMap);

				variableVariableDependencies.addAffectedVariable(
					variableDependencies.getVariableName());
				variableDependencies.addRequiredVariable(
					variableVariableDependencies.getVariableName());
			}
		}

		variableDependenciesMap.put(variable.getName(), variableDependencies);

		return variableDependencies;
	}

<<<<<<< HEAD
	protected void registerExpressionFunctionsAndVariables() {
		ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

		DDMExpressionListener ddmExpressionListener =
			new DDMExpressionListener();

		parseTreeWalker.walk(ddmExpressionListener, _expressionContext);

		// Function names

		_expressionFunctionNames.addAll(
			ddmExpressionListener.getFunctionNames());

		// Variables

		for (String variableName : ddmExpressionListener.getVariableNames()) {
			_variables.put(variableName, new Variable(variableName));
		}
	}

	protected void registerExpressionModel() {
		DDMExpressionModelVisitor ddmExpressionModelVisitor =
			new DDMExpressionModelVisitor();

		_expressionModel = _expressionContext.accept(ddmExpressionModelVisitor);
	}

	protected void setVariableValue(String variableName, Object variableValue) {
		Variable variable = _variables.get(variableName);

		if (variable == null) {
			return;
		}

		variable.setValue(variableValue);

		_variableValues.put(variableName, variableValue);
	}

	protected double toDouble(Object result) throws DDMExpressionException {
		Number number = (Number)result;

		return number.doubleValue();
	}

	protected float toFloat(Object result) throws DDMExpressionException {
		Number number = (Number)result;

		return number.floatValue();
	}

	protected int toInteger(Object result) throws DDMExpressionException {
		Number number = (Number)result;

		return number.intValue();
	}

	protected long toLong(Object result) throws DDMExpressionException {
		Number number = (Number)result;

		return number.longValue();
	}

	protected Object toRetunType(Object result) throws DDMExpressionException {
		if (String.class.isAssignableFrom(_expressionClass)) {
			return String.valueOf(result);
		}
		else if (Boolean.class.isAssignableFrom(_expressionClass)) {
			assertResultTypeClass(Boolean.class, result.getClass());

			return result;
		}
		else if (Double.class.isAssignableFrom(_expressionClass)) {
			assertResultTypeClass(Number.class, result.getClass());

			return toDouble(result);
		}
		else if (Float.class.isAssignableFrom(_expressionClass)) {
			assertResultTypeClass(Number.class, result.getClass());

			return toFloat(result);
		}
		else if (Integer.class.isAssignableFrom(_expressionClass)) {
			assertResultTypeClass(Number.class, result.getClass());

			return toInteger(result);
		}
		else if (Long.class.isAssignableFrom(_expressionClass)) {
			assertResultTypeClass(Number.class, result.getClass());

			return toLong(result);
		}
		else if (Number.class.isAssignableFrom(_expressionClass)) {
			assertResultTypeClass(Number.class, result.getClass());

			return result;
		}

		return result;
	}

	private final Map<String, DDMExpressionFunction> _ddmExpressionFunctions =
		new HashMap<>();
	private final Class<?> _expressionClass;
	private final ExpressionContext _expressionContext;
	private final Set<String> _expressionFunctionNames = new HashSet<>();
	private Expression _expressionModel;
	private final String _expressionString;
	private final Map<String, Variable> _variables = new TreeMap<>();
	private final Map<String, Object> _variableValues = new HashMap<>();
=======
	protected void setExpressionCustomFunctions(
		com.udojava.evalex.Expression expression) {

		expression.addFunction(
			expression.new Function("between", 3) {

				@Override
				public BigDecimal eval(List<BigDecimal> parameters) {
					BigDecimal parameter = parameters.get(0);

					BigDecimal minParameter = parameters.get(1);
					BigDecimal maxParameter = parameters.get(2);

					if ((parameter.compareTo(minParameter) >= 0) &&
						(parameter.compareTo(maxParameter) <= 0)) {

						return BigDecimal.ONE;
					}

					return BigDecimal.ZERO;
				}

			});

		expression.addFunction(
			expression.new Function("concat", -1) {

				@Override
				public BigDecimal eval(List<BigDecimal> parameters) {
					StringBundler sb = new StringBundler(parameters.size());

					for (BigDecimal parameter : parameters) {
						if (isStringBlank(parameter)) {
							continue;
						}

						String string = decodeString(parameter);

						sb.append(string);
					}

					if (sb.index() == 0) {
						return BigDecimal.ZERO;
					}

					return encode(sb.toString());
				}

			});

		expression.addFunction(
			expression.new Function("contains", 2) {

				@Override
				public BigDecimal eval(List<BigDecimal> parameters) {
					BigDecimal parameter1 = parameters.get(0);
					BigDecimal parameter2 = parameters.get(1);

					if (isStringBlank(parameter1, parameter2)) {
						return BigDecimal.ONE;
					}

					String string1 = decodeString(parameter1);
					String string2 = decodeString(parameter2);

					if (string1.contains(string2)) {
						return BigDecimal.ONE;
					}

					return BigDecimal.ZERO;
				}

			});

		expression.addFunction(
			expression.new Function("equals", 2) {

				@Override
				public BigDecimal eval(List<BigDecimal> parameters) {
					BigDecimal parameter1 = parameters.get(0);
					BigDecimal parameter2 = parameters.get(1);

					if (isStringBlank(parameter1, parameter2)) {
						return BigDecimal.ONE;
					}

					String string1 = decodeString(parameter1);
					String string2 = decodeString(parameter2);

					if (string1.equals(string2)) {
						return BigDecimal.ONE;
					}

					return BigDecimal.ZERO;
				}

			});

		expression.addFunction(
			expression.new Function("isEmailAddress", 1) {

				@Override
				public BigDecimal eval(List<BigDecimal> parameters) {
					String string = decodeString(parameters.get(0));

					if (Validator.isEmailAddress(string)) {
						return BigDecimal.ONE;
					}

					return BigDecimal.ZERO;
				}

			});

		expression.addFunction(
			expression.new Function("isURL", 1) {

				@Override
				public BigDecimal eval(List<BigDecimal> parameters) {
					String string = decodeString(parameters.get(0));

					if (Validator.isUrl(string)) {
						return BigDecimal.ONE;
					}

					return BigDecimal.ZERO;
				}

			});

		expression.addFunction(
			expression.new Function("sum", -1) {

				@Override
				public BigDecimal eval(List<BigDecimal> parameters) {
					BigDecimal sum = new BigDecimal(0);

					for (BigDecimal parameter : parameters) {
						sum = sum.add(parameter);
					}

					return sum;
				}

			});
	}

	protected void setExpressionCustomOperators(
		com.udojava.evalex.Expression expression) {

		expression.addOperator(
			expression.new Operator("+", 20, true) {

				@Override
				public BigDecimal eval(
					BigDecimal parameter1, BigDecimal parameter2) {

					return new BigDecimal(
						parameter1.doubleValue() + parameter2.doubleValue());
				}

			});

		expression.addOperator(
			expression.new Operator("-", 20, true) {

				@Override
				public BigDecimal eval(
					BigDecimal parameter1, BigDecimal parameter2) {

					return new BigDecimal(
						parameter1.doubleValue() - parameter2.doubleValue());
				}

			});

		expression.addOperator(
			expression.new Operator("*", 30, true) {

				@Override
				public BigDecimal eval(
					BigDecimal parameter1, BigDecimal parameter2) {

					return new BigDecimal(
						parameter1.doubleValue() * parameter2.doubleValue());
				}

			});

		expression.addOperator(
			expression.new Operator("/", 30, true) {

				@Override
				public BigDecimal eval(
					BigDecimal parameter1, BigDecimal parameter2) {

					return new BigDecimal(
						parameter1.doubleValue() / parameter2.doubleValue());
				}

			});

		expression.addOperator(
			expression.new Operator("%", 30, true) {

				@Override
				public BigDecimal eval(
					BigDecimal parameter1, BigDecimal parameter2) {

					return new BigDecimal(
						parameter1.doubleValue() % parameter2.doubleValue());
				}

			});

		expression.addOperator(
			expression.new Operator("^", 40, false) {

				@Override
				public BigDecimal eval(
					BigDecimal parameter1, BigDecimal parameter2) {

					double pow = Math.pow(
						parameter1.doubleValue(), parameter2.doubleValue());

					return new BigDecimal(pow);
				}

			});
	}

	protected void setExpressionMathContext(
		com.udojava.evalex.Expression expression) {

		expression.setPrecision(_mathContext.getPrecision());
		expression.setRoundingMode(_mathContext.getRoundingMode());
	}

	protected void setVariableValue(
		String variableName, BigDecimal variableValue) {

		Variable variable = _variables.get(variableName);

		if (variable == null) {
			return;
		}

		variable.setValue(variableValue);
	}

	protected Object toRetunType(BigDecimal result) {
		if (_expressionClass.isAssignableFrom(Boolean.class)) {
			return decodeBoolean(result);
		}
		else if (_expressionClass.isAssignableFrom(Double.class)) {
			return result.doubleValue();
		}
		else if (_expressionClass.isAssignableFrom(Float.class)) {
			return result.floatValue();
		}
		else if (_expressionClass.isAssignableFrom(Integer.class)) {
			return result.intValue();
		}
		else if (_expressionClass.isAssignableFrom(Long.class)) {
			return result.longValue();
		}
		else {
			return decodeString(result);
		}
	}

	private final Class<?> _expressionClass;
	private final String _expressionString;
	private MathContext _mathContext = MathContext.UNLIMITED;
	private final Map<String, Variable> _variables = new TreeMap<>();
	private final Map<String, BigDecimal> _variableValues = new HashMap<>();
>>>>>>> compatible

}