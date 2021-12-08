<#-- @ftlvariable name="date" type="java.lang.String" -->
<#-- @ftlvariable name="category" type="java.lang.String" -->
<#-- @ftlvariable name="pageType" type="java.lang.String" -->
<#-- @ftlvariable name="pageNum" type="java.lang.Integer" -->
<#-- @ftlvariable name="pageSize" type="java.lang.Integer" -->
<#-- @ftlvariable name="pageUri" type="java.lang.String" -->

<#include 'frame/functions.ftlh'>

<#macro PageHeader>
    <meta name="author" content="${gSiteName!'singte'}">
    <meta name="copyright" content="${gSiteName!'singte'}">
    <link type="text/css" rel="stylesheet" href="/static/css/list.css">
</#macro>

<@PageFrame>
    <div class="column_market w1200 pt20 clearfix">
        <div class="column_left fl">
<#--            <div class="news_center_ico">-->
<#--                <img src="images/column_ico1.png">-->
<#--                <p class="pb40">五金建材电商<br/>网络新闻中心</p>-->
<#--            </div>-->
            <h3 class="bold">推荐</h3>
            <ul class="hd">
                <@MenuItems groupName="sider" hasLast=true />
                <#--                <li><a href="#">前景</a></li>-->
<#--                <li class="thisclass"><a href="#">动态</a></li>-->
<#--                <li class="last_li"><a href="#">数据</a></li>-->
            </ul>
            <div class="wechat">
                <div><img style="width: 114px;height: 114px;" src="${gWxQrcode!'/static/images/wx/mp.jpg'}"></div>
                <p>官方微信</p>
            </div>
<#--            <div class="sina">-->
<#--                <div><img src="img/column_img1.png"></div>-->
<#--                <p>官方微博</p>-->
<#--            </div>-->
        </div>
        <!--center-->
        <div class="fl column_center">
            <@ArticleList num=pageNum size=pageSize url=pageUri type=pageType category=category date=date />
        </div>
        <!--/center-->
        <!--right-->
        <div class="right fr">
            <!--热文榜-->
            <div class="mb10">
                <@ArticleBox type="hot"/>
            </div>
            <#if pageType!="index">
            <div class="mb10">
                <@ArticleBox type="last"/>
            </div>
            </#if>
            <div class="mb10">
                <@ArticleBox type="random"/>
            </div>
            <!--/热文榜-->
        </div>
        <!--/right-->
    </div>
</@PageFrame>