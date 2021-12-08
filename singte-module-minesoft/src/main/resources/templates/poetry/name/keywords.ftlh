<#include '../../frame/functions.ftlh'>

<#macro PageHeader>
    <meta name="author" content="${gSiteName!'singte'}">
    <meta name="copyright" content="${gSiteName!'singte'}">
</#macro>

<@PageFrame title="含有关键词的诗句" keywords="" description="">
    <div class="data-table">
        <#list poemAtomList as atomItem>
            <div class="data-row">
                <div class="data-cell cell-index">${atomItem?index + 1}</div>
                <div class="data-cell cell-name">${atomItem.poemName}</div>
                <div class="data-cell cell-content" data-keywords="${RequestParameters["keywords"]}">${atomItem.atomContent}</div>
            </div>
        </#list>
    </div>
    <script>
        $(function () {
            $(".cell-content").each(function () {
                let that = $(this);
                let keywords = that.data("keywords");
                let content = that.text();
                let keywordList = keywords.split(" ");
                let result = [];
                for (let i = 0; i < content.length; i++) {
                    let element = content[i];
                    if(keywordList.indexOf(element) >=0){
                        result.push("<span class='red'>" + element + "</span>");
                    }else{
                        result.push(element);
                    }
                }
                that.html(result.join(""));
            });
        });
    </script>
</@PageFrame>
