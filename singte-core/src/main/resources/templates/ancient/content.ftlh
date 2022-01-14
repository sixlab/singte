<#-- @ftlvariable name="ancientSection" type="cn.sixlab.minesoft.singte.core.models.SteAncientSection" -->
<#include '../frame/functions.ftlh'>
<#assign static>
    <script src="/static/cssmoban/js/hc-sticky.js"></script>
    <script src="/static/cssmoban/js/aside-sticky.js"></script>
    <@StStatic src="ancient/content.js" />
</#assign>

<@PageFrame title=title static=static aside=true>
    <input type="hidden" value="${ancientSection.id}" class="st-section-id">
    <div class="left-box">
        <div class="whitebg">
            <div class="news-title">
                <span class="weizhi">
                    您现在的位置是：
                    <a href="/ancient/set">古文</a>
                    >
                    <a href="/category/${ancientSection.id}">${ancientSection.ancientSet}</a>
                    >
                    <a href="/book/${ancientSection.id}">${ancientSection.ancientCategory}</a>
                    >
                    <a href="/section/${ancientSection.id}">${ancientSection.bookName}</a>
                </span>
                <h2>正文</h2>
            </div>
            <div class="newstext-box">
                <h1 class="news-title-h1">${ancientSection.sectionName}</h1>
<#--                <div class="keywords">-->
<#--                    <a href="/">时间</a><a href="/">旅行者</a><a href="/">故事</a>-->
<#--                </div>-->
                <div class="picview-other">
                    <span class="po-from">${ancientSection.author}</span>
<#--                    <span class="po-date">${article.publishTime?string("yyyy-MM-dd HH:mm:ss")}</span>-->
<#--                    <span class="po-classname">${article.category}</span>-->
                    <span class="po-view">${ancientSection.viewCount!'0'}</span>
                </div>
<#--                <div class="smalltext">-->
<#--                    <i></i>-->
<#--                    ${ancientSection.contentHtml}-->
<#--                </div>-->
                <div class="newstext">
                    ${ancientSection.contentHtml?no_esc}
                </div>
                <div class="diggit">
                    <a href="###"> 很赞哦！ </a>(<b id="diggnum">${ancientSection.thumbCount!'0'}</b>)
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</@PageFrame>