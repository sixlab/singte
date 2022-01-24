<#include '../../frame/functions.ftlh'>

<#assign title><@StI18n "page.title.lang"/></#assign>
<#assign js>
    <@StStatic type="validate" />
    <@StStatic src="common/stDataTable.js" />
    <@StStatic src="admin/lang/list.js" />
</#assign>
<@AdminFrame title="${title}" js=js>
    <div class="row">
        <div class="col-12">
            <form id="queryForm" action="/admin/lang/listData">
                <input type="hidden" name="pageNum" class="pageNum" value="1">
                <input type="hidden" name="pageSize" class="pageSize" value="10">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4">
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#modal-default">
                                    新增语言
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
                    <h4 class="modal-title">配置信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="modalDataForm">
                        <input type="hidden" name="id" id="id" value="">
                        <div class="form-group row">
                            <label for="langCode" class="col-sm-2 col-form-label">编号</label>
                            <div class="col-sm-10 form-control-container">
                                <input type="text" name="langCode" id="langCode" class="form-control required" placeholder="编号">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="langText" class="col-sm-2 col-form-label">语言</label>
                            <div class="col-sm-10 form-control-container">
                                <input type="text" name="langText" id="langText" class="form-control required" placeholder="语言">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="langIcon" class="col-sm-2 col-form-label">图标</label>
                            <div class="col-sm-10 form-control-container">
                                <input type="text" name="langIcon" id="langIcon" class="form-control required" placeholder="图标">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="intro" class="col-sm-2 col-form-label">简介</label>
                            <div class="col-sm-10 form-control-container">
                                <textarea class="form-control" name="intro" id="intro" rows="3" placeholder="简介"></textarea>
                            </div>
                        </div>
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