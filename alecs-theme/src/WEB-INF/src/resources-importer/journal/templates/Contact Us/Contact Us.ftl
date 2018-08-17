<style>
    .portlet-title-text{
        display: none;
    }
    .row {
        margin-top: 1rem;
    }
    .header-img {
        border-top-left-radius: 15px;
        border-top-right-radius: 15px;
    }
    form {
        width: 100%;
        display: contents;
    }
    textarea {
        min-height: 50px;
    }
</style>
<#if BackgroundImage.getData()?? && BackgroundImage.getData() != "">
    <img alt="${BackgroundImage.getAttribute("alt")}" data-fileentryid="${BackgroundImage.getAttribute("fileEntryId")}" src="${BackgroundImage.getData()}" class="header-img"/>
</#if>
<div class="row">
  <form action="${FormBackend.getData()}">
    <p class="field col-sm-6">
      <label for="first">First Name: </label>
      <input type="text" id="first" name="first_name" class="form-control" placeholder="Life"/>
    </p>
    <p class="field col-sm-6">
      <label for="last">Last Name: </label>
      <input type="text" id="last" name="last_name" class="form-control" placeholder="Ray"/>
    </p>
    <p class="field col-sm-12">
      <label for="email">Email: </label>
      <input type="email" id="email" name="email" class="form-control" placeholder="Liferay@Liferay.com"/>
    </p>
    <p class="field col-sm-12">
      <label for="message">Message: </label>
      <textarea id="message" name="message" class="form-control" placeholder="I need help!!!"></textarea>
    </p>
    <p class="field col-sm-12 col-md-2">
      <input type="submit" class="btn btn-primary" value="Send It" <#if FormBackend.getData() == "#">disabled</#if>/>
    </p>
  </form>
</div>
