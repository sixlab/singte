<#-- @ftlvariable name="result" type="cn.sixlab.minesoft.singte.core.common.pager.PageResult<cn.sixlab.minesoft.singte.core.models.StArticle>" -->
<#include '../frame/functions.ftlh'>

<#assign static>
    <script src="/static/cssmoban/js/hc-sticky.js"></script>
    <script src="/static/cssmoban/js/aside-sticky.js"></script>
</#assign>

<#assign title>
    <@StI18nArgs code="title.list" args=[result.pageNum] />
</#assign>

<@PageFrame title=title static=static aside=true>
    <div class="blogs left-box">
        <div class="blogs-box whitebg">
            <div class="news-title">
                <h2>文章列表</h2>
            </div>
            <div class="blogs-list">
                <ul>
                    <#list result.list as item>
                        <li>
                            <h2><a href="/article/content/${item.alias}">${item.title}</a></h2>
                            <i><a href="/article/content/${item.alias}"><img src="/static/images/article/${item_index%10}.jpg" alt="${item.title}"></a></i>
                            <p>
                                ${item.publishTime?string("yyyy-MM-dd HH:mm:ss")}<br/>
                                ${item.summary}
                            </p>
                            <span>
                            <#list item.keywords as keyword>
                                <a href="/keyword/${keyword}">${keyword}</a>
                            </#list>
                            </span>
                        </li>
                    </#list>
                </ul>
            </div>
            <@PagePager url="/articles?pageNum=" result=result/>
        </div>
    </div>
</@PageFrame>