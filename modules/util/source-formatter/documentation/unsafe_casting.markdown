## Unsafe Casting

To avoid ClassCastExceptions, we want to use

<<<<<<< HEAD
`PortalUtil.getLiferayPortletRequest(renderRequest)` or
`PortalUtil.getLiferayPortletResponse(renderResponse)`
=======
```PortalUtil.getLiferayPortletRequest(renderRequest)``` or
```PortalUtil.getLiferayPortletResponse(renderResponse)```
>>>>>>> compatible

### Example

Incorrect:

```java
LiferayPortletRequest liferayPortletRequest =
	(LiferayPortletRequest)portletRequest;

LiferayPortletResponse liferayPortletResponse =
	(LiferayPortletResponse)portletResponse;
```

Correct:

```java
LiferayPortletRequest liferayPortletRequest =
	PortalUtil.getLiferayPortletRequest(portletRequest);

LiferayPortletResponse liferayPortletResponse =
	PortalUtil.getLiferayPortletResponse(portletResponse);
```