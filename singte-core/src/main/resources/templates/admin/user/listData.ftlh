<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>用户名</th>
            <th>显示名称</th>
            <th>状态</th>

            <th>手机</th>
            <th>邮箱</th>
            <th>角色</th>
            <th>token有效期</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.username}</td>
                <td>${item.showName}</td>
                <td>${item.status}</td>
                <td>${item.mobile!''}</td>
                <td>${item.email!''}</td>
                <td>${item.role}</td>
                <td>${(item.tokenValid?string("yyyy-MM-dd HH:mm:ss"))!"-"}</td>
                <td>${item.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
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

                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
