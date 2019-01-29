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

package com.liferay.portal.vulcan.internal.jaxrs.feature;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.odata.filter.ExpressionConvert;
import com.liferay.portal.odata.filter.FilterParserProvider;
import com.liferay.portal.odata.sort.SortParserProvider;
import com.liferay.portal.vulcan.internal.jaxrs.context.provider.AcceptLanguageContextProvider;
import com.liferay.portal.vulcan.internal.jaxrs.context.provider.CompanyContextProvider;
import com.liferay.portal.vulcan.internal.jaxrs.context.provider.FieldsQueryParamContextProvider;
import com.liferay.portal.vulcan.internal.jaxrs.context.provider.FilterContextProvider;
import com.liferay.portal.vulcan.internal.jaxrs.context.provider.PaginationContextProvider;
import com.liferay.portal.vulcan.internal.jaxrs.context.provider.SortContextProvider;
import com.liferay.portal.vulcan.internal.jaxrs.context.provider.UserContextProvider;
import com.liferay.portal.vulcan.internal.jaxrs.context.resolver.ObjectMapperContextResolver;
import com.liferay.portal.vulcan.internal.jaxrs.exception.mapper.ExceptionMapper;
import com.liferay.portal.vulcan.internal.jaxrs.exception.mapper.InvalidFormatExceptionMapper;
import com.liferay.portal.vulcan.internal.jaxrs.exception.mapper.JsonMappingExceptionMapper;
import com.liferay.portal.vulcan.internal.jaxrs.exception.mapper.JsonParseExceptionMapper;
import com.liferay.portal.vulcan.internal.jaxrs.exception.mapper.NoSuchModelExceptionMapper;
import com.liferay.portal.vulcan.internal.jaxrs.exception.mapper.PortalExceptionMapper;
import com.liferay.portal.vulcan.internal.jaxrs.exception.mapper.PrincipalExceptionMapper;
import com.liferay.portal.vulcan.internal.jaxrs.exception.mapper.UnrecognizedPropertyExceptionMapper;
import com.liferay.portal.vulcan.internal.jaxrs.exception.mapper.ValidationExceptionMapper;
import com.liferay.portal.vulcan.internal.jaxrs.exception.mapper.WebApplicationExceptionMapper;
import com.liferay.portal.vulcan.internal.jaxrs.message.body.JSONMessageBodyReader;
import com.liferay.portal.vulcan.internal.jaxrs.message.body.JSONMessageBodyWriter;
import com.liferay.portal.vulcan.internal.jaxrs.message.body.MultipartBodyMessageBodyReader;
import com.liferay.portal.vulcan.internal.jaxrs.validation.BeanValidationInterceptor;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * An {@code Application} requesting this feature will include all the different
 * extensions provided by this module.
 *
 * @author Alejandro Hernández
 * @review
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.extension.select=\\(osgi.jaxrs.name=Liferay.Vulcan\\))",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Liferay.Vulcan"
	},
	scope = ServiceScope.PROTOTYPE, service = Feature.class
)
public class VulcanFeature implements Feature {

	@Override
	public boolean configure(FeatureContext featureContext) {
		featureContext.register(BeanValidationInterceptor.class);
		featureContext.register(ExceptionMapper.class);
		featureContext.register(FieldsQueryParamContextProvider.class);
		featureContext.register(JacksonJsonProvider.class);
		featureContext.register(JsonMappingExceptionMapper.class);
		featureContext.register(JSONMessageBodyReader.class);
		featureContext.register(JSONMessageBodyWriter.class);
		featureContext.register(JsonParseExceptionMapper.class);
		featureContext.register(InvalidFormatExceptionMapper.class);
		featureContext.register(MultipartBodyMessageBodyReader.class);
		featureContext.register(NoSuchModelExceptionMapper.class);
		featureContext.register(ObjectMapperContextResolver.class);
		featureContext.register(PaginationContextProvider.class);
		featureContext.register(PortalExceptionMapper.class);
		featureContext.register(PrincipalExceptionMapper.class);
		featureContext.register(UnrecognizedPropertyExceptionMapper.class);
		featureContext.register(ValidationExceptionMapper.class);
		featureContext.register(WebApplicationExceptionMapper.class);

		featureContext.register(
			new AcceptLanguageContextProvider(_language, _portal));
		featureContext.register(new CompanyContextProvider(_portal));
		featureContext.register(
			new FilterContextProvider(
				_expressionConvert, _filterParserProvider, _language, _portal));
		featureContext.register(
			new SortContextProvider(_language, _portal, _sortParserProvider));
		featureContext.register(new UserContextProvider(_portal));

		return false;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private BundleContext _bundleContext;

	@Reference(
		target = "(result.class.name=com.liferay.portal.kernel.search.filter.Filter)"
	)
	private ExpressionConvert<Filter> _expressionConvert;

	@Reference
	private FilterParserProvider _filterParserProvider;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference
	private SortParserProvider _sortParserProvider;

}