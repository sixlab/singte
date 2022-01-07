<#include '../../frame/functions.ftlh'>

<#assign title><@StI18n "page.title.spiders"/></#assign>
<#assign js>
    <script src="/static/plugins/jquery-validation/jquery.validate.min.js"></script>
    <script src="/static/plugins/jquery-validation/additional-methods.min.js"></script>
    <@StStatic src="common/stDataTable.js" />
    <@StStatic src="admin/spider/list.js" />
</#assign>
<@AdminFrame title="${title}" js=js>
    <div class="row">
        <div class="col-12">
            <form id="queryForm" action="/admin/spider/listData">
                <input type="hidden" name="pageNum" class="pageNum" value="1">
                <input type="hidden" name="pageSize" class="pageSize" value="10">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4">
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#modal-default">
                                    新增爬虫
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
                    <h4 class="modal-title">爬虫信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="modalDataForm">
                        <div class="form-group row">
                            <label for="spiderType" class="col-sm-2 col-form-label">爬虫类型</label>
                            <div class="col-sm-10">
                                <input type="text" name="spiderType" id="spiderType" class="form-control required" placeholder="爬虫类型 PageLinkSpider">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="spiderName" class="col-sm-2 col-form-label">爬虫名称</label>
                            <div class="col-sm-10">
                                <input type="text" name="spiderName" id="spiderName" class="form-control required" placeholder="爬虫名称">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="pagerRule" class="col-sm-2 col-form-label">页面规则</label>
                            <div class="col-sm-10">
                                <input type="text" name="pagerRule" id="pagerRule" class="form-control" placeholder="页面 xPath 规则">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="linkRule" class="col-sm-2 col-form-label">链接规则</label>
                            <div class="col-sm-10">
                                <input type="text" name="linkRule" id="linkRule" class="form-control" placeholder="链接 xPath 规则">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="titleRule" class="col-sm-2 col-form-label">标题规则</label>
                            <div class="col-sm-10">
                                <input type="text" name="titleRule" id="titleRule" class="form-control" placeholder="标题 xPath 规则">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="contentRule" class="col-sm-2 col-form-label">正文规则</label>
                            <div class="col-sm-10">
                                <input type="text" name="contentRule" id="contentRule" class="form-control" placeholder="正文 xPath 规则">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="summaryRule" class="col-sm-2 col-form-label">摘要规则</label>
                            <div class="col-sm-10">
                                <input type="text" name="summaryRule" id="summaryRule" class="form-control" placeholder="摘要 xPath 规则">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="categoryRule" class="col-sm-2 col-form-label">分类规则</label>
                            <div class="col-sm-10">
                                <input type="text" name="categoryRule" id="categoryRule" class="form-control" placeholder="分类 xPath 规则">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="keywordRule" class="col-sm-2 col-form-label">关键词规则</label>
                            <div class="col-sm-10">
                                <input type="text" name="keywordRule" id="keywordRule" class="form-control" placeholder="关键词 xPath 规则">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="waitTimes" class="col-sm-2 col-form-label">任务间隔</label>
                            <div class="col-sm-10">
                                <input type="text" name="waitTimes" id="waitTimes" class="form-control" placeholder="任务间隔，毫秒">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="startUrl" class="col-sm-2 col-form-label">开始链接</label>
                            <div class="col-sm-10">
                                <input type="text" name="startUrl" id="startUrl" class="form-control required" placeholder="开始链接">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="urlParam" class="col-sm-2 col-form-label">链接参数</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="urlParam" id="urlParam" rows="3" placeholder="链接参数"></textarea>
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