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

            <th>分类</th>

            <th>看/赞</th>
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

                <td>${item.category}</td>

                <td>${item.viewCount}/${item.thumbCount}</td>
                <td>${item.publishStatus}</td>
                <td>${(item.publishTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
                <td>

                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
