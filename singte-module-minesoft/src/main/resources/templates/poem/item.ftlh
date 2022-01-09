<#-- @ftlvariable name="stePoem" type="cn.sixlab.minesoft.singte.module.minesoft.models.StePoem" -->
<#include '../frame/functions.ftlh'>

<#assign css>
    <@StStatic type="css" src="article.css" />
</#assign>

<@PageFrame title=stePoem.poemName keywords=[stePoem.poemName] description=stePoem.poemName css=css>
    <div class="column_market w1200 pt20 clearfix">
        <!--left-->
        <div class="detail_left fl">
            <ul class="clearfix title">
                <li><a href="/">首页 > </a></li>
                <li><a href="/poem/item/${stePoem.id}">${stePoem.poemName} ></a></li>
                <li><a href="###">正文</a></li>
            </ul>
            <div class="artical">
                <h1><a href="javascript:;">${stePoem.poemName}</a></h1>
                <div class="clearfix">
                    <ul class="fl origin">
                        <li><a href="/poem/author/${stePoem.poemAuthor}">作者：${stePoem.poemAuthor}</a></li>
                        　
<#--                        <li><a href="/date/${stePoem.publishTime?string("yyyyMMdd")}">${stePoem.publishTime?string("yyyy-MM-dd HH:mm:ss")}</a></li>-->
                    </ul>
<#--                    <div class="fr view"><img src="/static/images/viewed.png">${stePoem.viewCount}</div>-->
                </div>
<#--                <div class="abstract">-->
<#--                    ${stePoem.summary}-->
<#--                </div>-->
                <div>
                    <p style="text-indent: 0;padding-top: 30px;">
                        <#list stePoem.poemLines as lines>
                            ${lines}<br/>
                        </#list>
                    </p>
                </div>
<#--                <div class="clearfix bottom">-->
<#--                    <div class="fl"><span>标签:</span>-->
<#--                        <#list stePoem.keywords as keyword>-->
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