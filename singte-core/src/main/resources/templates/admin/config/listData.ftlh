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
                    <#if "1"==item.status>
                        <button type="button" class="btn btn-danger stStatusBtn" data-target-status="0"
                                data-item-id="${item.id}">禁用
                        </button>
                    <#else>
                        <button type="button" class="btn btn-success stStatusBtn" data-target-status="1"
                                data-item-id="${item.id}">启用
                        </button>
                    </#if>

                    <button type="button" class="btn btn-primary stModifyBtn" data-item-id="${item.id}">修改</button>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
