<#-- @ftlvariable name="steAncient" type="cn.sixlab.minesoft.singte.core.models.SteAncient" -->
<#include '../frame/functions.ftlh'>

<#assign css>
    <@StStatic type="css" src="article.css" />
</#assign>

<@PageFrame title=steAncient.ancientName keywords=[steAncient.ancientName] description=steAncient.ancientName css=css>
    <div class="column_market w1200 pt20 clearfix">
        <!--left-->
        <div class="detail_left fl">
            <ul class="clearfix title">
                <li><a href="/">首页 > </a></li>
                <li><a href="/ancient/item/${steAncient.id}">${steAncient.ancientName} ></a></li>
                <li><a href="###">正文</a></li>
            </ul>
            <div class="artical">
                <h1><a href="javascript:;">${steAncient.ancientName}</a></h1>
                <div class="clearfix">
                    <ul class="fl origin">
                        <li><a href="/ancient/author/${steAncient.ancientAuthor}">作者：${steAncient.ancientAuthor}</a></li>
                        　
<#--                        <li><a href="/date/${steAncient.publishTime?string("yyyyMMdd")}">${steAncient.publishTime?string("yyyy-MM-dd HH:mm:ss")}</a></li>-->
                    </ul>
<#--                    <div class="fr view"><img src="/static/images/viewed.png">${steAncient.viewCount}</div>-->
                </div>
<#--                <div class="abstract">-->
<#--                    ${steAncient.summary}-->
<#--                </div>-->
                <div>
                    <p style="text-indent: 0;padding-top: 30px;">
                        <#list steAncient.ancientLines as lines>
                            ${lines}<br/>
                        </#list>
                    </p>
                </div>
<#--                <div class="clearfix bottom">-->
<#--                    <div class="fl"><span>标签:</span>-->
<#--                        <#list steAncient.keywords as keyword>-->
<#--                            <a href="/keyword/${keyword}">${keyword}</a>-->
<#--                        </#list>-->
<#--                    </div>-->
<#--                </div>-->
            </div>
        </div>
        <!--/left-->

        <!--right-->
        <div class="right fr">
            <!--热文榜-->
            <div class="mb10">
                <@ArticleBox type="hot"/>
            </div>
            <div class="mb10">
                <@ArticleBox type="last"/>
            </div>
            <div class="mb10">
                <@ArticleBox type="random"/>
            </div>
            <!--/热文榜-->
        </div>
        <!--/right-->
    </div>
</@PageFrame>