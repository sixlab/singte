<#include 'functions.ftlh'>
<#macro AdminFrame title="" keywords=[] description="" css="" js="" >
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <title>${title!'singte首页'}</title>
    <meta name="keywords" content="<@StConfig key="st_site_name" default="singte"/>"/>
    <meta name="description" content="<@StConfig key="st_site_name" default="singte"/>"/>

    <link rel="shortcut icon" href="/favicon.ico">
    <link rel="bookmark" href="/favicon.ico">
    <link rel="apple-touch-icon" href="/favicon.ico">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Google Font: Source Sans Pro -->
<#--    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">-->
    <link rel="stylesheet" href="/static/fonts/sourcesanspro.css">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="/static/plugins/fontawesome-free/css/all.min.css">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="/static/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/adminlte/css/adminlte.min.css">
    ${css}
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">
    <@AdminHeader />

    <@AdminMenu groupName="nav" hasLast=true />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
<#--        <div class="content-header">-->
<#--            <div class="container-fluid">-->
<#--                <div class="row mb-2">-->
<#--                    <div class="col-sm-6">-->
<#--                        <h1 class="m-0">Starter Page</h1>-->
<#--                    </div><!-- /.col &ndash;&gt;-->
<#--                    <div class="col-sm-6">-->
<#--                        <ol class="breadcrumb float-sm-right">-->
<#--                            <li class="breadcrumb-item"><a href="#">Home</a></li>-->
<#--                            <li class="breadcrumb-item active">Starter Page</li>-->
<#--                        </ol>-->
<#--                    </div><!-- /.col &ndash;&gt;-->
<#--                </div><!-- /.row &ndash;&gt;-->
<#--            </div><!-- /.container-fluid &ndash;&gt;-->
<#--        </div>-->
        <!-- /.content-header -->

        <!-- Main content -->
        <div class="content">
            <div class="container-fluid" style="padding:1rem;">
                <#nested>
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <@AdminSider />

    <@AdminFooter />
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="/static/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/adminlte/js/adminlte.min.js"></script>
${js}
</body>
</html>
</#macro>

<#--  页面头部  -->
<#macro AdminHeader>
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="/" class="nav-link">前台首页</a>
            </li>
<#--            <li class="nav-item d-none d-sm-inline-block">-->
<#--                <a href="#" class="nav-link">Contact</a>-->
<#--            </li>-->
        </ul>

        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <!-- Navbar Search -->
            <li class="nav-item">
                <a class="nav-link" data-widget="navbar-search" href="#" role="button">
                    <i class="fas fa-search"></i>
                </a>
                <div class="navbar-search-block">
                    <form class="form-inline">
                        <div class="input-group input-group-sm">
                            <input class="form-control form-control-navbar" type="search" placeholder="Search"
                                   aria-label="Search">
                            <div class="input-group-append">
                                <button class="btn btn-navbar" type="submit">
                                    <i class="fas fa-search"></i>
                                </button>
                                <button class="btn btn-navbar" type="button" data-widget="navbar-search">
                                    <i class="fas fa-times"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </li>

            <!-- Messages Dropdown Menu -->
            <li class="nav-item dropdown">
                <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="far fa-comments"></i>
                    <span class="badge badge-danger navbar-badge">3</span>
                </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <a href="#" class="dropdown-item">
                        <!-- Message Start -->
                        <div class="media">
                            <img src="/static/adminlte/img/user1-128x128.jpg" alt="User Avatar" class="img-size-50 mr-3 img-circle">
                            <div class="media-body">
                                <h3 class="dropdown-item-title">
                                    Brad Diesel
                                    <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
                                </h3>
                                <p class="text-sm">Call me whenever you can...</p>
                                <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                            </div>
                        </div>
                        <!-- Message End -->
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <!-- Message Start -->
                        <div class="media">
                            <img src="/static/adminlte/img/user8-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
                            <div class="media-body">
                                <h3 class="dropdown-item-title">
                                    John Pierce
                                    <span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>
                                </h3>
                                <p class="text-sm">I got your message bro</p>
                                <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                            </div>
                        </div>
                        <!-- Message End -->
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <!-- Message Start -->
                        <div class="media">
                            <img src="/static/adminlte/img/user3-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
                            <div class="media-body">
                                <h3 class="dropdown-item-title">
                                    Nora Silvester
                                    <span class="float-right text-sm text-warning"><i class="fas fa-star"></i></span>
                                </h3>
                                <p class="text-sm">The subject goes here</p>
                                <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                            </div>
                        </div>
                        <!-- Message End -->
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
                </div>
            </li>
            <!-- Notifications Dropdown Menu -->
            <li class="nav-item dropdown">
                <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="far fa-bell"></i>
                    <span class="badge badge-warning navbar-badge">15</span>
                </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <span class="ropdown-item dropdown-header">15 Notifications</span>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-envelope mr-2"></i> 4 new messages
                        <span class="float-right text-muted text-sm">3 mins</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-users mr-2"></i> 8 friend requests
                        <span class="float-right text-muted text-sm">12 hours</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-file mr-2"></i> 3 new reports
                        <span class="float-right text-muted text-sm">2 days</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-widget="fullscreen" href="#" role="button">
                    <i class="fas fa-expand-arrows-alt"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
                    <i class="fas fa-th-large"></i>
                </a>
            </li>
        </ul>
    </nav>
    <!-- /.navbar -->
</#macro>

<#--  页面脚部  -->
<#macro AdminFooter>
    <!-- Main Footer -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="float-right d-none d-sm-inline">
            Anything you want
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2021 <a href="/"><@StConfig key="st_site_name" default="singte"/></a>.</strong> All rights reserved.
    </footer>
</#macro>

<#macro AdminSider>
    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
        <div class="p-3">
            <h5>Title</h5>
            <p>Sidebar content</p>
        </div>
    </aside>
    <!-- /.control-sidebar -->
</#macro>

<#--  页面菜单  -->
<#macro AdminMenu groupName hasLi=true hasLast=false>
    <!-- Main Sidebar Container -->
    <aside class="main-sidebar main-sidebar-custom sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="/admin/index" class="brand-link">
            <img src="/static/adminlte/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
                 style="opacity: .8">
            <span class="brand-text font-weight-light"><@StConfig key="st_site_name" default="singte"/></span>
        </a>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <img src="/static/adminlte/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
                </div>
                <div class="info">
                    <a href="/admin/user/info" class="d-block">个人中心</a>
                </div>
            </div>

            <!-- SidebarSearch Form -->
            <div class="form-inline">
                <div class="input-group" data-widget="sidebar-search">
                    <input class="form-control form-control-sidebar" type="search" placeholder="Search"
                           aria-label="Search">
                    <div class="input-group-append">
                        <button class="btn btn-sidebar">
                            <i class="fas fa-search fa-fw"></i>
                        </button>
                    </div>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                    data-accordion="false">
                    <!-- Add icons to the links using the .nav-icon class
                         with font-awesome or any other icon font library -->

                    <@StMenu group="menu.st-level1">
                        <#list stMenuGroupList as groupItem>
                            <#if groupItem.folderMenu>
                                <@StMenu group=groupItem.menuCode>
                                    <#if stHasMenu>
                                        <li class="nav-item <#if stFolderOpen>menu-open</#if>">
                                            <a href="#" class="nav-link">
                                                <i class="nav-icon fas ${groupItem.menuIcon}"></i>
                                                <p>
                                                    <@StI18n groupItem.menuCode/>
                                                    <#if groupItem.folderMenu>
                                                        <i class="right fas fa-angle-left"></i>
                                                    </#if>
                                                </p>
                                            </a>
                                            <ul class="nav nav-treeview">
                                            <#list stMenuGroup as item>
                                                <li class="nav-item">
                                                    <a href="${item.menuLink}" class="nav-link <#if requestUri==item.menuLink>active</#if>">
                                                        <i class="nav-icon ${item.menuIcon}"></i>
                                                        <p><@StI18n item.menuCode/></p>
                                                    </a>
                                                </li>
                                            </#list>
                                            </ul>
                                        </li>
                                    </#if>
                                </@StMenu>
                            <#else>
                                <li class="nav-item">
                                    <a href="${groupItem.menuLink}" class="nav-link <#if requestUri==groupItem.menuLink>active</#if>">
                                        <i class="nav-icon fas ${groupItem.menuIcon}"></i>
                                        <p>
                                            <@StI18n groupItem.menuCode/>
                                        </p>
                                    </a>
                                </li>
                            </#if>
                        </#list>
                    </@StMenu>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->

        <div class="sidebar-custom">
            <a href="/admin/index" class="btn btn-link"><i class="fas fa-cogs"></i></a>
            <a href="/admin/index" class="btn btn-secondary hide-on-collapse pos-right">Help</a>
        </div>
        <!-- /.sidebar-custom -->
    </aside>
</#macro>

