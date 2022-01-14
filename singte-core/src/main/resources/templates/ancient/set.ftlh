<#-- @ftlvariable name="result" type="cn.sixlab.minesoft.singte.core.common.pager.PageResult<cn.sixlab.minesoft.singte.core.models.SteAncientSet>" -->
<#include '../frame/functions.ftlh'>

<#assign title><@StI18n "page.title.ancient.set.front"/></#assign>

<#assign static>
    <script src="/static/cssmoban/js/hc-sticky.js"></script>
    <script src="/static/cssmoban/js/aside-sticky.js"></script>
</#assign>

<@PageFrame title=title static=static>
    <div class="pics box whitebg">
        <ul id="tab">
            <li class="tab-current"><@StI18n "title.ancient.set"/></li>
        </ul>
        <div class="plr20">
            <section>
                <div class="videos-list">
                    <ul>
                        <#list result as item>
                            <li>
                                <a href="/ancient/category/${item.id}">
                                    <i><img src="/static/images/article/${item_index%6 +1}.jpg"></i>
                                    <h2 class="nobg">${item.ancientSet}</h2>
                                    <p><b class="datetime"></b><span class="viewd nobg">${item.count} 本书</span></p>
                                </a>
                            </li>
                        </#list>
                    </ul>
                </div>
        </div>
    </div>
</@PageFrame>