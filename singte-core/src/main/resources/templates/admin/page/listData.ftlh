<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>文章标题</th>
            <th>作者</th>
            <th>别名</th>

            <th>看过人数</th>
            <th>状态</th>
            <th>时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>${item.title}</td>
                <td>${item.author}</td>
                <td>${item.alias}</td>

                <td>${item.viewCount}</td>
                <td>${item.status}</td>
                <td>${(item.publishTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
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

                    <a href="/page/${item.alias}" target="_blank">查看</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
