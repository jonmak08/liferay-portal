<#if entries?has_content>
    <div class="blogs-layout">
        <div class="row widget-mode-card">
            <#list entries as curBlogEntry>
                <#if curBlogEntry.getCoverImageURL(themeDisplay)??>
                    <#assign cardImage = true />
                <#else>
                    <#assign cardImage = false />
                </#if>

                <#assign viewEntryPortletURL = renderResponse.createRenderURL() />

                ${viewEntryPortletURL.setParameter("mvcRenderCommandName", "/blogs/view_entry")}

                <#if validator.isNotNull(curBlogEntry.getUrlTitle())>
                    ${viewEntryPortletURL.setParameter("urlTitle", curBlogEntry.getUrlTitle())}
                <#else>
                    ${viewEntryPortletURL.setParameter("entryId", curBlogEntry.getEntryId()?string)}
                </#if>

                <div class="col-lg-4 col-sm-6">
                    <div class="card">
                        <div class="entry-section">
                            <#if cardImage>
                                <div class="aspect-ratio aspect-ratio-3-to-2 entry-image">
                                    <a href="${viewEntryPortletURL.toString()}" title="${curBlogEntry.title}">
                                        <img alt="thumbnail" class="aspect-ratio-item-center-middle aspect-ratio-item-vertical-fluid" data-fileentryid="${curBlogEntry.coverImageFileEntryId}" src="${curBlogEntry.getCoverImageURL(themeDisplay)}">
                                    </a>
                                </div>
                            </#if>

                            <header class="entry-header">
                                <div class="entry-data">
                                    <span class="posted-on">
                                        <a href="${viewEntryPortletURL.toString()}" rel="bookmark">
                                            <time class="entry-date" datetime="${curBlogEntry.getStatusDate()?datetime}">
                                                ${dateUtil.getDate(curBlogEntry.getStatusDate(), "MMMM dd, yyyy", locale)}
                                            </time>
                                        </a>
                                    </span>
                                </div>

                                <div class="autofit-row card-title">
                                    <h1 class="entry-title">
                                        <a href="${viewEntryPortletURL.toString()}">
                                            ${blogsEntryUtil.getDisplayTitle(resourceBundle, curBlogEntry)}
                                        </a>
                                    </h1>

                                    <div class="autofit-col visible-interaction">
                                        <div class="dropdown dropdown-action">
                                            <@liferay_ui["icon-menu"]
                                                direction="left-side"
                                                icon=""
                                                markupView="lexicon"
                                                message=""
                                                showWhenSingleIcon=true
                                            >
                                                <#if blogsEntryPermission.contains(permissionChecker, curBlogEntry, "UPDATE")>
                                                    <#assign editEntryPortletURL = renderResponse.createRenderURL() />

                                                    ${editEntryPortletURL.setWindowState(windowStateFactory.getWindowState("MAXIMIZED"))}
                                                    ${editEntryPortletURL.setParameter("mvcRenderCommandName", "/blogs/edit_entry")}
                                                    ${editEntryPortletURL.setParameter("redirect", currentURL)}
                                                    ${editEntryPortletURL.setParameter("entryId", curBlogEntry.getEntryId()?string)}

                                                    <@liferay_ui["icon"]
                                                        label=true
                                                        message="edit"
                                                        url=editEntryPortletURL.toString()
                                                    />
                                                </#if>
                                                <#if blogsEntryPermission.contains(permissionChecker, curBlogEntry, "PERMISSIONS")>
                                                    <#assign permissionsEntryURL = permissionsURLTag.doTag(null, "com.liferay.blogs.model.BlogsEntry", blogsEntryUtil.getDisplayTitle(resourceBundle, curBlogEntry), curBlogEntry.getGroupId()?string, curBlogEntry.getEntryId()?string, windowStateFactory.getWindowState("POP_UP").toString(), null, request) />

                                                    <@liferay_ui["icon"]
                                                        label=true
                                                        message="permissions"
                                                        method="get"
                                                        url=permissionsEntryURL
                                                        useDialog=true
                                                    />
                                                </#if>
                                                <#if blogsEntryPermission.contains(permissionChecker, curBlogEntry, "DELETE")>
                                                    <#assign deleteEntryPortletURL = renderResponse.createActionURL() />

                                                    ${deleteEntryPortletURL.setParameter("javax.portlet.action", "/blogs/edit_entry")}
                                                    ${deleteEntryPortletURL.setParameter("cmd", trashHelper.isTrashEnabled(themeDisplay.getScopeGroupId())?then("move_to_trash", "delete"))}
                                                    ${deleteEntryPortletURL.setParameter("redirect", currentURL)}
                                                    ${deleteEntryPortletURL.setParameter("entryId", curBlogEntry.getEntryId()?string)}

                                                    <@liferay_ui["icon-delete"]
                                                        label=true
                                                        trash=trashHelper.isTrashEnabled(themeDisplay.getScopeGroupId())
                                                        url=deleteEntryPortletURL.toString()
                                                    />
                                                </#if>
                                            </@>
                                        </div>
                                    </div>
                                </div>
                            </header>

                            <div class="entry-content">
                                <#if cardImage>
                                    <#assign descriptionLength = 200 />
                                <#else>
                                    <#assign descriptionLength = 400 />
                                </#if>

                                <p class="widget-resume">${stringUtil.shorten(htmlUtil.stripHtml(curBlogEntry.getContent()), descriptionLength)}</p>

                                <#if (curBlogEntry.getContent()?length > descriptionLength) >
                                    <a class="read-more" href="${viewEntryPortletURL.toString()}">Continue reading</a>
                                <#else>
                                    <a class="read-more" href="${viewEntryPortletURL.toString()}">View entry</a>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>

            </#list>
        </div>
    </div>
</#if>