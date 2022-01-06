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

                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
