<#include '../frame/functions.ftlh'>

<#assign static>
    <script src="/static/cssmoban/js/hc-sticky.js"></script>
    <script src="/static/cssmoban/js/aside-sticky.js"></script>
</#assign>

<@PageFrame title=title static=static aside=true>
    <div class="wztitle left-box">
        <div class="wztitle-box whitebg">
            <div class="news-title">
                <span class="hnav">
                    <a href="/ancient/set">古文</a>
                    <a href="/ancient/category/${ancientSet.id}">${ancientSet.ancientSet}</a>
                    <a href="/ancient/book/${ancientCategory.id}">${ancientCategory.ancientCategory}</a>
                </span>
                <h2 class="ancient">${title}</h2>
            </div>
            <div class="wztitle-list">
                <ul>
                    <#list result.list as item>
                        <li>
                            <span>${item.author}</span>
                            <a href="/ancient/content/${item.id}" target="_blank">${item.sectionName}</a>
                        </li>
                    </#list>
                </ul>
            </div>
            <@PagePager url="${springMacroRequestContext.requestUri}?pageNum=" result=result/>
        </div>
    </div>
</@PageFrame>