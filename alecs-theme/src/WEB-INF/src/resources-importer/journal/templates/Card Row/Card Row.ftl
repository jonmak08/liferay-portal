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
<div class="row">
    <#if HTML18zr.getSiblings()?has_content>
        <#list HTML18zr.getSiblings() as cur_HTML18zr>
            <div class="col-sm-12 col-md-6">
                ${cur_HTML18zr.getData()}
            </div>
        </#list>
    </#if>
</div>
