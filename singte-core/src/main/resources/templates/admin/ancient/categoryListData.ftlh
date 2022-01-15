<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>分类</th>
            <th>部属</th>

            <th>数量</th>

            <th>顺序</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.ancientCategory}</td>
                <td>${item.ancientSet}</td>

                <td>${item.count}</td>

                <td>${item.weight}</td>
                <td>${item.status}</td>
                <td>
                    <div class="text-hide">
                        ${item.intro}
                    </div>

                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
