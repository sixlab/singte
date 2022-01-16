<#include '../frame/functions.ftlh'>
<#assign static>
    <script src="/static/cssmoban/js/hc-sticky.js"></script>
    <script src="/static/cssmoban/js/aside-sticky.js"></script>
    <@StStatic src="tool/content.js" />
</#assign>

<@PageFrame title=title static=static aside=true>
    <input type="hidden" value="${toolItem.id}" class="st-tool-id">
    <div class="left-box">
        <div class="whitebg">
            <div class="news-title">
                <span class="weizhi">
                    您现在的位置是：
                    <a href="/tool/list">工具</a>
                    >
                    <a href="/tool/category/${categoryId}">${toolItem.category}</a>
                    >
                    <a href="###">正文</a>
                </span>
                <h2 class="long-text">${toolItem.toolName}</h2>
            </div>
            <div class="newstext-box">
<#--                <h1 class="news-title-h1">${toolItem.toolName}</h1>-->
<#--                <div class="keywords">-->
<#--                    <a href="/">时间</a><a href="/">旅行者</a><a href="/">故事</a>-->
<#--                </div>-->
                <div class="picview-other">
<#--                    <span class="po-from">${toolItem.author}</span>-->
<#--                    <span class="po-date">${article.publishTime?string("yyyy-MM-dd HH:mm:ss")}</span>-->
<#--                    <span class="po-classname">${article.category}</span>-->
                    <span class="po-view">${toolItem.viewCount!'0'}</span>
                </div>
                <div class="smalltext">
                    <i></i>
                    ${toolItem.intro}
                </div>
                <div class="newstext">
                    ${toolItem.toolCode}实现
                </div>
                <div class="diggit">
                    <a href="###"> 很赞哦！ </a>(<b id="diggnum">${toolItem.thumbCount!'0'}</b>)
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</@PageFrame>