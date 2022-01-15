<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>工具名称</th>
            <th>分类</th>

            <th>看/赞</th>

            <th>顺序</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.toolName}</td>
                <td>${item.category}</td>

                <td>${item.viewCount} / ${item.thumbCount}</td>

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
