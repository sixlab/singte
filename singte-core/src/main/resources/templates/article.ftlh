<#-- @ftlvariable name="notFound" type="java.lang.Boolean" -->
<#-- @ftlvariable name="article" type="cn.sixlab.minesoft.singte.core.models.StArticle" -->
<#include 'frame/functions.ftlh'>

<#macro PageHeader>
    <meta name="author" content="<@StConfig key="st_site_name" default="singte"/>">
    <meta name="copyright" content="<@StConfig key="st_site_name" default="singte"/>">
    <link type="text/css" rel="stylesheet" href="/static/css/article.css">
</#macro>

<@PageFrame title=article.title keywords=article.keywords description=article.summary>
    <div class="column_market w1200 pt20 clearfix">
        <!--left-->
        <div class="detail_left fl">
            <ul class="clearfix title">
                <li><a href="/">首页 > </a></li>
                <li><a href="/category/${article.categoryId}">${article.category} ></a></li>
                <li><a href="###">正文</a></li>
            </ul>
            <div class="artical">
                <h1><a href="javascript:;">${article.title}</a></h1>
                <div class="clearfix">
                    <ul class="fl origin">
                        <li><a href="/author/${article.author}">编辑：${article.author}</a></li>
                        　
                        <li><a href="/date/${article.publishTime?string("yyyyMMdd")}">${article.publishTime?string("yyyy-MM-dd HH:mm:ss")}</a></li>
                    </ul>
                    <div class="fr view"><img src="/static/images/viewed.png">${article.viewCount}</div>
                </div>
                <div class="abstract">
                    ${article.summary}
                </div>
                <div>
                    <p>${article.content}</p>
                </div>
                <div class="clearfix bottom">
                    <div class="fl"><span>标签:</span>
                        <#list article.keywords?split(",") as keyword>
                            <a href="#">${keyword}</a>
                        </#list>
                    </div>
                </div>
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