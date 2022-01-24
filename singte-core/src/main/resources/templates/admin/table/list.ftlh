<#include '../../frame/functions.ftlh'>

<#assign js>
    <@StStatic type="validate" />
    <@StStatic src="common/stDataTable.js" />
    <@StStatic src="admin/table.js" />
</#assign>
<@AdminFrame title="${title}" js=js>
    <input type="hidden" id="StTableName" value="${tableName}">
    <div class="row">
        <div class="col-12">
            <form id="queryForm" action="/admin/table/${tableName}/listData">
                <input type="hidden" name="pageNum" class="pageNum" value="1">
                <input type="hidden" name="pageSize" class="pageSize" value="10">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4">
                                <button type="button" class="btn btn-primary stCountBtn">
                                    重置
                                </button>
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#modal-default">
                                    新增
                                </button>
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
                                <label for="${column.name}" class="col-sm-2 col-form-label">${column.placeholder}</label>
                                <div class="col-sm-10 form-control-container">
                                <#switch "input">
                                    <#case "small">
                                        <input type="text" name="${column.name}" id="${column.name}"
                                               value="${column.defaultVal}" class="form-control ${column.cssClass}"
                                               placeholder="${column.placeholder}">
                                        <#break>
                                    <#case "text">
                                        <textarea class="form-control ${column.cssClass}" name="${column.name}" rows="3"
                                                  id="${column.name}"
                                                  placeholder="${column.placeholder}">${column.defaultVal}</textarea>
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