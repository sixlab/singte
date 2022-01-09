<#-- @ftlvariable name="page" type="cn.sixlab.minesoft.singte.core.models.StPage" -->
<#include 'frame/functions.ftlh'>

<#assign css>
    <@StStatic type="css" src="article.css" />
</#assign>

<@PageFrame title=page.title keywords=[page.title] description=page.title css=css>
    <div class="column_market w1200 pt20 clearfix">
        <!--left-->
        <div class="detail_left fl">
            <ul class="clearfix title">
                <li><a href="/">首页 > </a></li>
                <li><a href="/page/${page.alias}">${page.title} ></a></li>
                <li><a href="###">正文</a></li>
            </ul>
            <div class="artical">
                <h1><a href="javascript:;">${page.title}</a></h1>
                <div class="clearfix">
                    <ul class="fl origin">
                        <li><a href="/author/${page.author}">编辑：${page.author}</a></li>
                        　
                        <li><a href="/date/${page.publishTime?string("yyyyMMdd")}">${page.publishTime?string("yyyy-MM-dd HH:mm:ss")}</a></li>
                    </ul>
                    <div class="fr view"><img src="/static/images/viewed.png">${page.viewCount}</div>
                </div>
                <div>
                    <p>${page.content?no_esc}</p>
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