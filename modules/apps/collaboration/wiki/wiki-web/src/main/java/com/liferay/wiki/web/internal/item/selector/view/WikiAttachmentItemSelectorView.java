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

package com.liferay.wiki.web.internal.item.selector.view;

<<<<<<< HEAD
import com.liferay.document.library.display.context.DLMimeTypeDisplayContext;
=======
>>>>>>> compatible
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorReturnTypeResolverHandler;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
<<<<<<< HEAD
import com.liferay.wiki.item.selector.constants.WikiItemSelectorViewConstants;
import com.liferay.wiki.item.selector.criterion.WikiAttachmentItemSelectorCriterion;
import com.liferay.wiki.web.internal.item.selector.constants.WikiItemSelectorWebKeys;
=======
import com.liferay.wiki.item.selector.criterion.WikiAttachmentItemSelectorCriterion;
>>>>>>> compatible
import com.liferay.wiki.web.internal.item.selector.view.display.context.WikiAttachmentItemSelectorViewDisplayContext;

import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
<<<<<<< HEAD
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Iván Zaera
 * @author Roberto Díaz
 */
@Component(
	property = {
		"item.selector.view.key=" + WikiItemSelectorViewConstants.ITEM_SELECTOR_VIEW_KEY
	}
)
public class WikiAttachmentItemSelectorView
	implements ItemSelectorView<WikiAttachmentItemSelectorCriterion> {

=======

/**
 * @author Iván Zaera
 */
@Component
public class WikiAttachmentItemSelectorView
	implements ItemSelectorView<WikiAttachmentItemSelectorCriterion> {

	public static final String
		WIKI_ATTACHMENT_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT =
			"WIKI_ATTACHMENT_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT";

>>>>>>> compatible
	@Override
	public Class<WikiAttachmentItemSelectorCriterion>
		getItemSelectorCriterionClass() {

		return WikiAttachmentItemSelectorCriterion.class;
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return LanguageUtil.get(locale, "page-attachments");
	}

	@Override
	public boolean isShowSearch() {
		return true;
	}

	@Override
	public boolean isVisible(ThemeDisplay themeDisplay) {
		return true;
	}

	@Override
	public void renderHTML(
			ServletRequest request, ServletResponse response,
			WikiAttachmentItemSelectorCriterion
				wikiAttachmentItemSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		WikiAttachmentItemSelectorViewDisplayContext
			wikiAttachmentItemSelectorViewDisplayContext =
				new WikiAttachmentItemSelectorViewDisplayContext(
					wikiAttachmentItemSelectorCriterion, this,
					_itemSelectorReturnTypeResolverHandler,
					itemSelectedEventName, search, portletURL);

		request.setAttribute(
<<<<<<< HEAD
			WikiItemSelectorWebKeys.
				WIKI_ATTACHMENT_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT,
			wikiAttachmentItemSelectorViewDisplayContext);

		request.setAttribute(
			WikiItemSelectorWebKeys.DL_MIME_TYPE_DISPLAY_CONTEXT,
			_dlMimeTypeDisplayContext);

=======
			WIKI_ATTACHMENT_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT,
			wikiAttachmentItemSelectorViewDisplayContext);

>>>>>>> compatible
		ServletContext servletContext = getServletContext();

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher(
				"/item/selector/wiki_page_attachments.jsp");

		requestDispatcher.include(request, response);
	}

<<<<<<< HEAD
	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	public void setDLMimeTypeDisplayContext(
		DLMimeTypeDisplayContext dlMimeTypeDisplayContext) {

		_dlMimeTypeDisplayContext = dlMimeTypeDisplayContext;
	}

=======
>>>>>>> compatible
	@Reference(unbind = "-")
	public void setItemSelectorReturnTypeResolverHandler(
		ItemSelectorReturnTypeResolverHandler
			itemSelectorReturnTypeResolverHandler) {

		_itemSelectorReturnTypeResolverHandler =
			itemSelectorReturnTypeResolverHandler;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.wiki.web)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

<<<<<<< HEAD
	public void unsetDLMimeTypeDisplayContext(
		DLMimeTypeDisplayContext dlMimeTypeDisplayContext) {

		_dlMimeTypeDisplayContext = null;
	}

=======
>>>>>>> compatible
	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.unmodifiableList(
			ListUtil.fromArray(
				new ItemSelectorReturnType[] {
					new FileEntryItemSelectorReturnType()
				}));

<<<<<<< HEAD
	private DLMimeTypeDisplayContext _dlMimeTypeDisplayContext;
=======
>>>>>>> compatible
	private ItemSelectorReturnTypeResolverHandler
		_itemSelectorReturnTypeResolverHandler;
	private ServletContext _servletContext;

}