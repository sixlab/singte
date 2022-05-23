<#include '../../frame/functions.ftlh'>

<#assign title><@StI18n "page.title.server.info"/></#assign>
<#assign js>
    <@StStatic src="admin/server/info.js" />
</#assign>
<@AdminFrame title="${title}" js=js>
    <div class="row">
        <div class="col-md-3">

            <!-- Profile Image -->
            <div class="card card-primary card-outline">
                <div class="card-body box-profile">
                    <p class="text-muted text-center">发布时间：${startTime?string("yyyy-MM-dd HH:mm:ss")}</p>

                    <a href="#" class="btn btn-primary btn-block stRebootBtn"><b>重启服务</b></a>

                    <a href="#" class="btn btn-primary btn-block stUpdateBtn"><b>更新服务</b></a>
                </div>
                <!-- /.card-body -->
            </div>
            <!-- /.card -->
        </div>
    </div>
    <!-- /.row -->
</@AdminFrame>