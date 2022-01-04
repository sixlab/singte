<#include '../frame/functions.ftlh'>

<#assign title><@spring.message code="page.title.menus"/></#assign>
<#assign css>
    <!-- DataTables -->
    <link rel="stylesheet" href="/static/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
</#assign>
<#assign js>
    <!-- DataTables  & Plugins -->
    <script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="/static/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
    <script src="/static/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
    <script src="/static/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
    <script src="/static/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
    <script src="/static/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
    <script src="/static/plugins/jszip/jszip.min.js"></script>
    <script src="/static/plugins/pdfmake/pdfmake.min.js"></script>
    <script src="/static/plugins/pdfmake/vfs_fonts.js"></script>
    <script src="/static/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
    <script src="/static/plugins/datatables-buttons/js/buttons.print.min.js"></script>
    <script src="/static/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
    <@StStatic src="admin/menus.js" />
</#assign>
<@AdminFrame title="${title}" css=css js=js>
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">DataTable with default features</h3>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                    <table id="list_table" class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>菜单编号</th>
                            <th>菜单名称</th>
                            <th>菜单链接</th>
                            <th>菜单图标</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <!-- /.card-body -->
            </div>
            <!-- /.card -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</@AdminFrame>