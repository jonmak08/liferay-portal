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

import React, {FocusEventHandler} from 'react';
declare const Numeric: React.FC<IProps>;
export {Numeric};
declare const _default: any;
export default _default;
interface IProps {
	dataType: NumericDataType;
	defaultLanguageId: string;
	id: string;
	inputMask?: boolean;
	inputMaskFormat?: string;
	localizedValue?: {
		[key: string]: string;
	};
	name: string;
	onBlur: FocusEventHandler<HTMLInputElement>;
	onChange: (event: {
		target: {
			value: string;
		};
	}) => void;
	onFocus: FocusEventHandler<HTMLInputElement>;
	placeholder?: string;
	predefinedValue?: string;
	readOnly: boolean;
	symbols: {
		decimalSymbol: string;
		thousandSymbol?: string;
	};
	value?: string;
}
declare type NumericDataType = 'integer' | 'double';
