<#include '../frame/functions.ftlh'>

<#assign static>
    <script src="/static/cssmoban/js/hc-sticky.js"></script>
    <script src="/static/cssmoban/js/aside-sticky.js"></script>
</#assign>

<@PageFrame title=title static=static>
    <div class="pics box whitebg">
        <div class="news-title">
                <span class="hnav">
                    <a href="/ancient/set">古文</a>
                </span>
            <h2>${title}</h2>
        </div>
        <div class="plr20">
            <section>
                <div class="videos-list">
                    <ul>
                        <#list result as item>
                            <li>
                                <a href="/ancient/book/${item.id}">
                                    <i><img src="/static/images/article/${item_index%10}.jpg"></i>
                                    <h2 class="nobg">${item.ancientCategory}</h2>
                                    <p><b class="datetime">${item.ancientSet}</b><span class="viewd nobg">${item.count} 本书</span></p>
                                </a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </section>
        </div>
    </div>
</@PageFrame>