<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>组件名称</th>
            <th>组件编号</th>
            <th>组件简介</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.widgetName}</td>
                <td>${item.widgetCode}</td>
                <td>${item.widgetIntro}</td>
                <td>${item.widgetStatus}</td>
                <td>
                    <button type="button" class="btn btn-primary"
                            <#if "1"==item.widgetStatus>
                            data-targetStatus="0"
                            <#else>
                            data-targetStatus="1"
                            </#if>
                            data-recordId="${item.id}">修改状态</button>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
