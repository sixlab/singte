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
                    <#if !item.folderMenu>
                        <a href="${item.menuLink}" target="_blank">${item.menuLink}</a>
                    </#if>
                </td>

                <td>${item.folderMenu?string("是", "否")}</td>
                <td>${item.status}</td>
                <td>${item.weight}</td>
                <td>${item.intro}</td>
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

                    <#if !item.folderMenu>
                        <a href="${item.menuLink}" target="_blank">查看</a>
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- /.card-body -->
<@AdminPager adminPageInfo=result />
