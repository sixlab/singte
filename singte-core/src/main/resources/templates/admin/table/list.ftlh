<#include '../../frame/functions.ftlh'>

<#assign title><@StI18n tableInfo.title/></#assign>
<#assign js>
    <@StStatic type="validate" />
    <@StStatic src="common/stDataTable.js" />
    <@StStatic src="admin/table.js" />
</#assign>
<@AdminFrame title="${title}" js=js>
    <input type="hidden" id="StTableName" value="${tableInfo.tableName}">
    <input type="hidden" id="reloadUri" value="${tableInfo.reloadUri}">
    <div class="row">
        <div class="col-12">
            <form id="queryForm" action="/admin/table/${tableInfo.tableName}/listData">
                <input type="hidden" name="pageNum" class="pageNum" value="1">
                <input type="hidden" name="pageSize" class="pageSize" value="10">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4">
                                <#if tableInfo.reload >
                                    <button type="button" class="btn btn-primary stReloadBtn">
                                        重置
                                    </button>
                                </#if>
                                <#if tableInfo.insert >
                                    <button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#modal-default">
                                        新增
                                    </button>
                                </#if>
                            </div>
                            <div class="col-5">

                            </div>
                            <div class="col-3">
                                <div class="input-group input-group">
                                    <input type="text" name="keyword" class="form-control">
                                    <span class="input-group-append">
                                        <button type="button" class="btn btn-primary btn-flat searchBtn">Go!</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- /.card -->
        </div>
        <div class="col-12">
            <div class="card" id="queryData">
            </div>
            <!-- /.card -->
        </div>
    </div>
    <!-- /.row -->

    <div class="modal fade" id="modal-default" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">编辑信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="modalDataForm">
                        <input type="hidden" name="id" id="id" value="">
                        <#list columns as column>
                            <div class="form-group row">
                                <label for="${column.columnName}" class="col-sm-2 col-form-label"><@StI18n column.text /></label>
                                <div class="col-sm-10 form-control-container">
                                    <#switch column.type>
                                        <#case "input">
                                            <input type="text" class="form-control st-fields ${column.inputCss}"
                                                   name="${column.columnName}" id="${column.columnName}"
                                                   value="${column.defaultVal}" placeholder="<@StI18n column.placeholder />">
                                            <#break>
                                        <#case "text">
                                            <textarea class="form-control st-fields ${column.inputCss}" rows="3"
                                                      name="${column.columnName}"
                                                      id="${column.columnName}"
                                                      placeholder="<@StI18n column.placeholder />">${column.defaultVal}</textarea>
                                            <#break>
                                        <#default>
                                            类型：[${column.type}] 未实现 under development
                                        <#break>
                                    </#switch>
                                </div>
                            </div>
                        </#list>
                    </form>
                </div>
                <div class="modal-footer justify-content-between">
                    <button type="button" class="btn btn-default cancelBtn" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary stSaveBtn">保存</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
</@AdminFrame>