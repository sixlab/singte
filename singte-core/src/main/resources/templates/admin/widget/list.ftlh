<#include '../../frame/functions.ftlh'>

<#assign title><@StI18n "page.title.widgets"/></#assign>
<#assign js>
    <@StStatic type="validate" />
    <@StStatic src="common/stDataTable.js" />
    <@StStatic src="admin/widget/list.js" />
</#assign>
<@AdminFrame title="${title}" js=js>
    <div class="row">
        <div class="col-12">
            <form id="queryForm" action="/admin/widget/listData">
                <input type="hidden" name="pageNum" class="pageNum" value="1">
                <input type="hidden" name="pageSize" class="pageSize" value="10">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-9">

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
</@AdminFrame>