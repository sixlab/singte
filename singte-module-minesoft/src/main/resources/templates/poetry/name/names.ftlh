
<#include '../../frame/functions.ftlh'>

<#macro PageHead>
    <meta name="author" content="<@StConfig key="st_site_name" default="singte"/>">
    <meta name="copyright" content="<@StConfig key="st_site_name" default="singte"/>">
</#macro>

<@PageFrame title="名字合集" keywords=[] description="">
    <div class="poem-form">
        <form action="/poetry/name/keywords" method="get">
            <div class="form-row">
                <label for="keywords">内容</label>
                <input type="text" id="keywords" name="keywords">
            </div>
            <div class="form-row">
                <button type="submit">提交</button>
            </div>
        </form>
    </div>

    <div class="data-table">
        <#list pageInfo.list as nameItem>
            <div class="data-row">
                <div class="data-cell cell-index">${nameItem?index + 1}</div>
                <div class="data-cell cell-name">${nameItem.nameKeywords}</div>
                <div class="data-cell cell-name">${nameItem.peopleName}</div>
                <div class="data-cell cell-author">${nameItem.atomContent}</div>
            </div>
        </#list>
    </div>
</@PageFrame>
