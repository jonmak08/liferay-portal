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

import {cleanup, render, waitForElement} from '@testing-library/react';
import FlagsModal from '../../../../src/main/resources/META-INF/resources/flags/js/components/FlagsModal.es';
import React from 'react';
import {
	STATUS_ERROR,
	STATUS_LOGIN,
	STATUS_REPORT,
	STATUS_SUCCESS
} from '../../../../src/main/resources/META-INF/resources/flags/js/constants.es';

function _renderFlagsModalComponent({
	companyName = 'Liferay',
	handleClose = () => {},
	handleInputChange = () => {},
	handleSubmit = () => {},
	isSending = false,
	pathTermsOfUse = '/',
	selectedReason = 'value',
	reasons = {value: 'text', value2: 'text2'},
	signedIn = true,
	status = STATUS_REPORT
} = {}) {
	return render(
		<FlagsModal
			companyName={companyName}
			handleClose={handleClose}
			handleInputChange={handleInputChange}
			handleSubmit={handleSubmit}
			isSending={isSending}
			pathTermsOfUse={pathTermsOfUse}
			reasons={reasons}
			selectedReason={selectedReason}
			signedIn={signedIn}
			status={status}
		/>,
		{
			baseElement: document.body
		}
	);
}

describe('FlagsModal', () => {
	afterEach(cleanup);

	it('renders', async done => {
		const {getByText, getByRole} = _renderFlagsModalComponent();

		await waitForElement(() => getByText('report-inappropriate-content'));
		await waitForElement(() => getByRole('form'));

		done();
	});

	it('renders as guess and render email field', async done => {
		const {getByLabelText} = _renderFlagsModalComponent({
			signedIn: false
		});

		await waitForElement(() => getByLabelText('email', {exact: false}));

		done();
	});

	it('renders error', async done => {
		const {getByText} = _renderFlagsModalComponent({status: STATUS_ERROR});

		await waitForElement(() =>
			getByText('an-error-occurred', {exact: false})
		);

		done();
	});

	it('renders login', async done => {
		const {getByText} = _renderFlagsModalComponent({status: STATUS_LOGIN});

		await waitForElement(() => getByText('please-sign-in', {exact: false}));

		done();
	});

	it('renders success', async done => {
		const {getByText} = _renderFlagsModalComponent({
			status: STATUS_SUCCESS
		});

		await waitForElement(() => getByText('thank-you', {exact: false}));

		done();
	});
});
