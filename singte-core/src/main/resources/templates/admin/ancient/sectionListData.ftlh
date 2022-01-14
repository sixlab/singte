<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>标题</th>
            <th>作者</th>
            <th>书名</th>

            <th>部属</th>
            <th>分类</th>

            <th>顺序</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.sectionName}</td>
                <td>${item.author}</td>
                <td>${item.bookName}</td>

                <td>${item.ancientSet}</td>
                <td>${item.ancientCategory}</td>

                <td>${item.weight}</td>
                <td>
                    <div class="text-hide">
                        ${item.intro}
                        ${item.contentHtml}
                        ${item.contentText}
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
