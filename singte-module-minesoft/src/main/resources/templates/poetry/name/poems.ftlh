
<#include '../../frame/functions.ftlh'>

<#macro PageHeader>
    <meta name="author" content="<@StConfig key="st_site_name" default="singte"/>">
    <meta name="copyright" content="<@StConfig key="st_site_name" default="singte"/>">
</#macro>

<@PageFrame title="诗歌合集" keywords="" description="">
    <div class="poem-form">
        <form action="/poetry/name/addPoem" method="post">
            <div class="form-row">
                <label for="poemName">内容</label>
                <input type="text" id="poemName" name="poemName">
            </div>
            <div class="form-row">
                <label for="poemAuthor">内容</label>
                <input type="text" id="poemAuthor" name="poemAuthor">
            </div>
            <div class="form-row">
                <label for="poemContent">内容</label>
                <textarea id="poemContent" name="poemContent"></textarea>
            </div>
            <div class="form-row">
                <button type="submit">提交</button>
            </div>
        </form>
    </div>
    <div class="data-table">
        <#list poemList as poemItem>
            <div class="data-row">
                <div class="data-cell cell-index">${poemItem?index + 1}</div>
                <div class="data-cell cell-name">${poemItem.poemName}</div>
                <div class="data-cell cell-author">${poemItem.poemAuthor}</div>
                <div class="data-cell cell-op">
                    <button>删除</button>
                </div>
            </div>
        </#list>
    </div>
</@PageFrame>
