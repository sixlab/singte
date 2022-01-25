<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>组件名称</th>
            <th>组件编号</th>
            <th><@StI18n 'label.common.intro'/></th>
            <th><@StI18n 'label.common.weight'/></th>
            <th><@StI18n 'label.common.status'/></th>
            <th><@StI18n 'label.common.operate'/></th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.widgetName}</td>
                <td>${item.widgetCode}</td>
                <td>${item.widgetIntro}</td>
                <td>${item.weight}</td>
                <td>${item.status}</td>
                <td>
                    <#if "1"==item.status>
                        <button type="button" class="btn btn-warning stStatusBtn" data-target-status="0"
                                data-item-id="${item.id}">禁用
                        </button>
                    <#else>
                        <button type="button" class="btn btn-success stStatusBtn" data-target-status="1"
                                data-item-id="${item.id}">启用
                        </button>
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
