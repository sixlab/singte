<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>标题</th>
            <th>作者</th>

            <th>部属</th>
            <th>分类</th>

            <th>章节数量</th>
            <th>顺序</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.bookName}</td>
                <td>${item.author}</td>

                <td>${item.ancientSet}</td>
                <td>${item.ancientCategory}</td>

                <td>${item.count}</td>
                <td>${item.weight}</td>
                <td>${item.status}</td>
                <td>
                    <div class="text-hide">
                        ${item.intro}
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
