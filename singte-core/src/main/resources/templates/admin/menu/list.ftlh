<#include '../../frame/functions.ftlh'>

<#assign title><@StI18n "page.title.menus"/></#assign>
<#assign js>
    <script src="/static/plugins/jquery-validation/jquery.validate.min.js"></script>
    <script src="/static/plugins/jquery-validation/additional-methods.min.js"></script>
    <@StStatic src="common/stDataTable.js" />
    <@StStatic src="admin/menu/list.js" />
</#assign>
<@AdminFrame title="${title}" js=js>
    <div class="row">
        <div class="col-12">
            <form id="queryForm" action="/admin/menu/listData">
                <input type="hidden" name="pageNum" class="pageNum" value="1">
                <input type="hidden" name="pageSize" class="pageSize" value="10">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4">
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#modal-default">
                                    新增菜单
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
                    <h4 class="modal-title">菜单信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="modalDataForm">
                        <input type="hidden" name="id" id="id" value="">
                        <div class="form-group row">
                            <div class="offset-sm-2 col-sm-10">
                                <div class="form-check">
                                    <input type="checkbox" name="folderMenu" id="folderMenu" class="form-check-input" value="1">
                                    <label class="form-check-label" for="folderMenu">是菜单文件夹</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="menuCode" class="col-sm-2 col-form-label">菜单编号</label>
                            <div class="col-sm-10 form-control-container">
                                <input type="text" name="menuCode" id="menuCode" class="form-control required" placeholder="菜单编号">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="menuGroup" class="col-sm-2 col-form-label">菜单分组</label>
                            <div class="col-sm-10 form-control-container">
                                <input type="text" name="menuGroup" id="menuGroup" class="form-control required" placeholder="菜单分组">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="menuIcon" class="col-sm-2 col-form-label">菜单图标</label>
                            <div class="col-sm-10 form-control-container">
                                <input type="text" name="menuIcon" id="menuIcon" class="form-control" placeholder="菜单图标">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="menuLink" class="col-sm-2 col-form-label">菜单链接</label>
                            <div class="col-sm-10 form-control-container">
                                <input type="text" name="menuLink" id="menuLink" class="form-control" placeholder="菜单链接">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="weight" class="col-sm-2 col-form-label">菜单顺序</label>
                            <div class="col-sm-10 form-control-container">
                                <input type="text" name="weight" id="weight" class="form-control" placeholder="菜单顺序">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="intro" class="col-sm-2 col-form-label">菜单描述</label>
                            <div class="col-sm-10 form-control-container">
                                <textarea class="form-control" name="intro" id="intro" rows="3" placeholder="菜单描述"></textarea>
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