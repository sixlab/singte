<#include '../../frame/functions.ftlh'>

<#assign title><@StI18n "page.title.ancient.section"/></#assign>
<#assign js>
    <script src="/static/plugins/jquery-validation/jquery.validate.min.js"></script>
    <script src="/static/plugins/jquery-validation/additional-methods.min.js"></script>
    <@StStatic src="common/stDataTable.js" />
    <@StStatic src="admin/ancient/section.js" />
</#assign>
<@AdminFrame title="${title}" js=js>
    <div class="row">
        <div class="col-12">
            <form id="queryForm" action="/admin/ancient/section/listData">
                <input type="hidden" name="pageNum" class="pageNum" value="1">
                <input type="hidden" name="pageSize" class="pageSize" value="10">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4">
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#modal-default">
                                    新增古文章节
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

    <div class="modal fade" id="modal-default">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">古文章节信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="modalDataForm">
                        <div class="form-group row">
                            <label for="sectionName" class="col-sm-2 col-form-label">标题</label>
                            <div class="col-sm-10">
                                <input type="text" name="sectionName" id="sectionName" class="form-control required" placeholder="标题">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="bookName" class="col-sm-2 col-form-label">书名</label>
                            <div class="col-sm-10">
                                <input type="text" name="bookName" id="bookName" class="form-control required" placeholder="书名">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="author" class="col-sm-2 col-form-label">作者</label>
                            <div class="col-sm-10">
                                <input type="text" name="author" id="author" class="form-control required" placeholder="作者">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="ancientSet" class="col-sm-2 col-form-label">部属</label>
                            <div class="col-sm-10">
                                <input type="text" name="ancientSet" id="ancientSet" class="form-control required"
                                       placeholder="部属">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="ancientCategory" class="col-sm-2 col-form-label">分类</label>
                            <div class="col-sm-10">
                                <input type="text" name="ancientCategory" id="ancientCategory" class="form-control required" placeholder="分类">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="weight" class="col-sm-2 col-form-label">顺序</label>
                            <div class="col-sm-10">
                                <input type="text" name="weight" id="weight" class="form-control" placeholder="顺序">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="intro" class="col-sm-2 col-form-label">简介</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="intro" id="intro" rows="3" placeholder="简介"></textarea>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="contentText" class="col-sm-2 col-form-label">内容</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="contentText" id="contentText" rows="3" placeholder="内容"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer justify-content-between">
                    <button type="button" class="btn btn-default cancelBtn" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary saveDataBtn">保存</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
</@AdminFrame>