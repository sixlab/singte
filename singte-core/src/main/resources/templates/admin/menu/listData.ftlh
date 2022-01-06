<#include '../../frame/functions.ftlh'>

<!-- /.card-header -->
<div class="card-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="width: 10px">#</th>
            <th>菜单编号</th>
            <th>菜单分组</th>
            <th>菜单链接</th>

            <th>文件夹</th>
            <th>菜单状态</th>
            <th>菜单顺序</th>
            <th>菜单简介</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list result.list as item>
            <tr>
                <td>${item_index + 1}.</td>
                <td>
                    <i class="nav-icon ${item.menuIcon}"></i>
                    <@StI18n item.menuCode/></td>
                <td><@StI18n item.menuGroup/></td>
                <td>
                    <#if item.menuLink??>
                        <a href="${item.menuLink}" target="_blank">${item.menuLink}</a>
                    </#if>
                </td>

                <td>${item.folderMenu?string("是", "否")}</td>
                <td>${item.status}</td>
                <td>${item.weight}</td>
                <td>${item.intro}</td>
                <td>

                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
