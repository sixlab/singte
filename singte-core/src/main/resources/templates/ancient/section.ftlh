<#-- @ftlvariable name="result" type="cn.sixlab.minesoft.singte.core.common.pager.PageResult<cn.sixlab.minesoft.singte.core.models.SteAncientBook>" -->
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
                    <a href="/ancient/category/${grandFather}">${grandFather}</a>
                    <a href="/ancient/book/${father}">${father}</a>
                </span>
                <h2>${title}</h2>
            </div>
            <div class="wztitle-list">
                <ul>
                    <#list result as item>
                        <li>
                            <span>${item.author}</span>
                            <a href="/ancient/content/${item.id}" target="_blank">${item.sectionName}</a>
                        </li>
                    </#list>
                </ul>
            </div>
            <div class="pagelist"><a title="Total record">&nbsp;<b>51</b> </a>&nbsp;&nbsp;&nbsp;<b>1</b>&nbsp;<a
                        href="/">2</a>&nbsp;<a href="/">3</a>&nbsp;<a href="/">4</a>&nbsp;<a href="/">5</a>&nbsp;<a
                        href="/">下一页</a>&nbsp;<a href="/">尾页</a></div>
        </div>
    </div>
</@PageFrame>