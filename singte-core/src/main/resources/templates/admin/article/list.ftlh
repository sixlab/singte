<#include '../../frame/functions.ftlh'>

<#assign title><@StI18n "page.title.articles"/></#assign>
<#assign js>
    <script src="/static/plugins/jquery-validation/jquery.validate.min.js"></script>
    <script src="/static/plugins/jquery-validation/additional-methods.min.js"></script>
    <@StStatic src="common/stDataTable.js" />
    <@StStatic src="admin/article/list.js" />
</#assign>
<@AdminFrame title="${title}" js=js>
    <div class="row">
        <div class="col-12">
            <form id="queryForm" action="/admin/article/listData">
                <input type="hidden" name="pageNum" class="pageNum" value="1">
                <input type="hidden" name="pageSize" class="pageSize" value="10">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4">
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#modal-default">
                                    新增文章
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
                    <h4 class="modal-title">文章信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="modalDataForm">
                        <input type="hidden" name="id" id="id" value="">
                        <div class="form-group row">
                            <label for="title" class="col-sm-2 col-form-label">文章标题</label>
                            <div class="col-sm-10">
                                <input type="text" name="title" id="title" class="form-control required" placeholder="文章标题">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="author" class="col-sm-2 col-form-label">作者</label>
                            <div class="col-sm-10">
                                <input type="text" name="author" id="author" class="form-control required" placeholder="作者">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="alias" class="col-sm-2 col-form-label">文章别名</label>
                            <div class="col-sm-10">
                                <input type="text" name="alias" id="alias" class="form-control required" placeholder="文章别名">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="sourceUrl" class="col-sm-2 col-form-label">来源网址</label>
                            <div class="col-sm-10">
                                <input type="text" name="sourceUrl" id="sourceUrl" class="form-control" placeholder="来源网址">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="category" class="col-sm-2 col-form-label">分类</label>
                            <div class="col-sm-10">
                                <input type="text" name="category" id="category" class="form-control" placeholder="分类">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="keywords" class="col-sm-2 col-form-label">关键词</label>
                            <div class="col-sm-10">
                                <input type="text" name="keywords" id="keywords" class="form-control" placeholder="关键词">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="summary" class="col-sm-2 col-form-label">摘要</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="summary" id="summary" rows="3" placeholder="摘要"></textarea>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="content" class="col-sm-2 col-form-label">内容</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="content" id="content" rows="3" placeholder="内容"></textarea>
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