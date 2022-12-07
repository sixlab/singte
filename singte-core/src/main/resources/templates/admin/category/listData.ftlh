<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>分类</th>
            <th>文章数量</th>
            <th>标识</th>
            <th><@StI18n 'label.common.weight'/></th>
            <th><@StI18n 'label.common.status'/></th>
            <th><@StI18n 'label.common.intro'/></th>
            <th><@StI18n 'label.common.operate'/></th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.category}</td>
                <td>${item.count?string(",###")}</td>
                <td>${item.flag!''}</td>
                <td>${item.weight!''}</td>
                <td>${item.status!''}</td>
                <td>${item.intro!''}</td>
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

                    <button type="button" class="btn btn-danger stDeleteBtn" data-item-id="${item.id}">删除</button>

                    <button type="button" class="btn btn-primary stModifyBtn" data-item-id="${item.id}">修改</button>

                    <a href="/category/${item.category}" target="_blank">查看</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
