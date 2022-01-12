<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>分组</th>
            <th>key</th>
            <th>值</th>
            <th>简介</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.configGroup!'-'}</td>
                <td>${item.configKey}</td>
                <td>${item.configVal}</td>
                <td>${item.intro}</td>
                <td>${item.status}</td>
                <td>

                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
