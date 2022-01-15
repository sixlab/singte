<#include '../frame/functions.ftlh'>

<#assign title><@StI18n "page.title.tool.category.front"/></#assign>

<#assign static>
    <script src="/static/cssmoban/js/hc-sticky.js"></script>
    <script src="/static/cssmoban/js/aside-sticky.js"></script>
</#assign>

<@PageFrame title=title static=static>
    <div class="pics box whitebg">
        <ul id="tab">
            <li class="tab-current"><@StI18n "title.tool.category"/></li>
        </ul>
        <div class="plr20">
            <section>
                <div class="videos-list">
                    <ul>
                        <#list result as item>
                            <li>
                                <a href="/tool/category/${item.id}">
                                    <i><img src="/static/images/article/${item_index%10}.jpg"></i>
                                    <h2 class="nobg">${item.category}</h2>
                                    <p><b class="datetime"></b><span class="viewd nobg">${item.count} 个工具</span></p>
                                </a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </section>
        </div>
    </div>
</@PageFrame>