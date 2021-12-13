<#--  热文榜  -->
<#macro ArticleBox type='' size="10">
<div class="classify">
    <div class="hot">
        <div class="title mb5">
            <#switch type>
                <#case 'hot'>热文榜<#break>
                <#case 'last'>最新好文<#break>
                <#default>随机推荐
            </#switch>
        </div>
        <div class="tab-bd2">
            <div class="tab-pal">
                <ul>
                    <@StArticles type=type size=size>
                        <#list tArticleList as item>
                    <li><span<#if (item_index<4)> class="on"</#if>>${item_index+1}</span><a href="/article/${item.id}" class="h">${item.title}</a></li>
                        </#list>
                    </@StArticles>
                </ul>
            </div>
        </div>
    </div>
</div>
</#macro>

<#--  文章列表  -->
<#macro ArticleList type url num=1 size=10 category="" date="">
<@StArticles type=type num=num size=size category=category date=date>
    <ul class="li_list">
        <h4 class="mb10"><a href="#">${category}</a></h4>
    <#list tArticlePageInfo.list as item>
        <li class="clearfix">
            <img src="/static/images/article/${item_index%10}.jpg" class="fl">
            <div class="right fr">
                <div class="top">
                    <p class="p1"><a href="/article/${item.alias}">${item.title}</a></p>
                    <p class="p2"><a href="/article/${item.alias}">${item.summary}</a></p>
                </div>
                <div class="clearfix bottom">
                    <div class="fl">
                        <#list item.keywords?split(",") as keyword>
                            <a href="#">${keyword}</a>
                        </#list>
                    </div>
                    <span class="time fr">${item.publishTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                </div>
            </div>
        </li>
    </#list>
    </ul>
    <ul class="clearfix list_page pt40 pb20">
        <#--第一页，禁用“上一页”按钮-->
        <#if tArticlePageInfo.totalNum == 0 || tArticlePageInfo.pageNum == 1>
            <li><span class="prev-disabled">上一页</span></li>
        <#else>
            <li><a href="${url}/${tArticlePageInfo.pageNum-1}">上一页</a></li>
        </#if>

        <#--第一页-->
        <#if (tArticlePageInfo.totalNum > 0)>
            <li <#if tArticlePageInfo.pageNum == 1>class="thisclass"</#if>><a href="${url}">1</a></li>
        </#if>

        <#--如果不只有一页-->
        <#if (tArticlePageInfo.totalPages > 1)>
        <#--如果当前页往前查3页不是第2页-->
            <#if ((tArticlePageInfo.pageNum - 3) > 2)>
                <li><a href="#">···</a></li>
            </#if>

        <#--当前页的前3页和后3页-->
            <#list (tArticlePageInfo.pageNum - 3)..(tArticlePageInfo.pageNum + 3) as index>
            <#--如果位于第一页和最后一页之间-->
                <#if (index > 1) && (index < tArticlePageInfo.totalPages)>
                    <li <#if tArticlePageInfo.pageNum == index>class="thisclass"</#if>><a href="${url}/${index}">${index}</a></li>
                </#if>
            </#list>

        <#--如果当前页往后查3页不是倒数第2页-->
            <#if (tArticlePageInfo.pageNum + 3) < (tArticlePageInfo.totalPages - 1)>
                <li><a href="#">···</a></li>
            </#if>

        <#--最后页-->
            <li <#if tArticlePageInfo.pageNum == tArticlePageInfo.totalPages>class="thisclass"</#if>><a href="${url}/${tArticlePageInfo.totalPages}">${tArticlePageInfo.totalPages}</a></li>
        </#if>

        <#--最后页，禁用“下一页”按钮-->
        <#if tArticlePageInfo.totalNum == 0 || tArticlePageInfo.pageNum == tArticlePageInfo.totalPages>
            <li><span class="prev-disabled">下一页</span></li>
        <#else>
            <li><a href="${url}/${tArticlePageInfo.pageNum+1}">下一页</a></li>
        </#if>
    </ul>
</@StArticles>
</#macro>