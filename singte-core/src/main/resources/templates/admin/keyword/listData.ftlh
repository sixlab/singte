<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>关键词</th>
            <th>文章数量</th>
            <th>标识</th>
            <th>顺序</th>
            <th>简介</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.keyword}</td>
                <td>${item.count?string(",###")}</td>
                <td>${item.flag!''}</td>
                <td>${item.weight!''}</td>
                <td>${item.intro!''}</td>
                <td>

                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
