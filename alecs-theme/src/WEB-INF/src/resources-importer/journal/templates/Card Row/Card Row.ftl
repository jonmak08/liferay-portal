<style>
  .autofit-col {
    display: inline-block;
  }

  .icon-shopping-cart {
    margin-left: auto;
    font-size: 32px;
    color: #000;
  }

  .icon-shopping-cart:hover {
    cursor: pointer;
    text-decoration: none;
    color: #323232;
  }

  .card {
    transition: 1s;
    cursor: pointer;
  }

  .card:hover {
    transform: scale(1.05);
    box-shadow: 0 1px 20px -4px rgba(0, 0, 0, 0.6);
  }
</style>

<#if product.getSiblings()?has_content>
  <div class="row">
    <#list product.getSiblings() as cur_product>
      <div class="col-sm-12 col-md-6">
        <div class="card card-type-asset">
          <div class="aspect-ratio card-item-first">
            <div class="aspect-ratio-item-center-middle aspect-ratio-item-fluid card-type-asset-icon" style="padding: 0; margin: 0;">
              <img src="${cur_product.Image.getData()}" />
            </div>
          </div>
          <div class="card-body">
            <div class="card-row">
              <div class="autofit-col autofit-col-expand">
                <a href="${cur_product.PurchaseLink.getData()}" target="_blank">
                  <span class="icon-shopping-cart icon-monospaced pull-right">
                    &nbsp;
                  </span>
                </a>
                <div class="card-title text-truncate" title="${cur_product.getData()}">
                  ${cur_product.getData()}
                </div>
                <div class="card-subtitle text-truncate" title="${cur_product.Subtitle.getData()}">
                  ${cur_product.Subtitle.getData()}
                </div>
                <div class="card-detail">
                  <#if getterUtil.getBoolean(cur_product.InStock.getData())>
                    <span class="label label-success">
                      ${cur_product.Price.getData()?trim?number?string.currency}
                    </span>
                  <#else>
                    <span class="label label-danger">
                      Out of Stock
                    </span>
                  </#if>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </#list>
  </div>
</#if>