<section id="faq">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="faq-area">
                    <div class="title-area">
                        <h2 class="title">
                            <@liferay.language key="faq" />
                        </h2>
                        <span class="title-dot"></span>
                    </div>
                    <div class="faq-content">
                        <div class="panel-group" id="accordion">
                            <#if FAQQuestion.getSiblings()?has_content>
                                <#list FAQQuestion.getSiblings() as cur_FAQQuestion>
                                    <#assign currentHREF="#" + cur_FAQQuestion?counter>
                                    <#assign collapse=cur_FAQQuestion?counter>
                                    <#assign collapseHREF="#" + collapse>
                                        <#if cur_FAQQuestion?counter==1>
                                            <#assign aria='true'>
                                            <#assign minus='minus'>
                                            <#assign show='show'>
                                            <#else>
                                            <#assign aria='false'>
                                            <#assign minus='plus'>
                                            <#assign show=''>
                                        </#if>

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a aria-expanded="${aria}" data-parent="#accordion" data-toggle="collapse" href="${collapseHREF}">
                                            <span class="fa fa-${minus}"></span>
                                                ${cur_FAQQuestion.getData()}
                                        </a>
                                    </h4>
                                </div>
                                <div class="collapse panel-collapse ${show}" id="${collapse}">
                                    <div class="panel-body">
                                        ${cur_FAQQuestion.FAQAnswer.getData()}
                                    </div>
                                </div>
                            </div>
                                </#list>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>