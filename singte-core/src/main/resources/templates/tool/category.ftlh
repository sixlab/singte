<#include '../frame/functions.ftlh'>

<#assign static>
    <script src="/static/cssmoban/js/hc-sticky.js"></script>
    <script src="/static/cssmoban/js/aside-sticky.js"></script>
</#assign>

<@PageFrame title=title static=static>
    <div class="pics box whitebg">
        <div class="news-title">
                <span class="hnav">
                    <a href="/tool/list">工具</a>
                </span>
            <h2>${title}</h2>
        </div>
        <div class="plr20">
            <section>
                <div class="videos-list">
                    <ul>
                        <#list result as item>
                            <li>
                                <a href="/tool/item/${item.id}">
                                    <i><img src="/static/images/article/${item_index%10}.jpg"></i>
                                    <h2 class="nobg">${item.toolName}</h2>
                                    <p><b class="datetime">${item.category}</b><span class="viewd">${item.viewCount}</span></p>
                                </a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </section>
        </div>
    </div>
</@PageFrame>