<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>标题</th>
            <th>类型</th>
            <th>分类</th>

            <th>章节</th>
            <th>作者</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.ancientName}</td>
                <td>${item.ancientType}</td>
                <td>${item.ancientCategory}</td>

                <td>${item.ancientSection}</td>
                <td>${item.ancientAuthor}</td>
                <td>
                    <div class="text-hide">
                        <#list item.ancientLines as lines>
                            ${lines}<br/>
                        </#list>
                    </div>
                    <a href="/ancient/item/${item.id}" target="_blank">查看</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
