<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title><@spring.message "common.login"/></#assign>
    <title>${title}</title>

    <link rel="shortcut icon" href="/favicon.ico">
    <link rel="bookmark" href="/favicon.ico">
    <link rel="apple-touch-icon" href="/favicon.ico">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet" href="/static/fonts/sourcesanspro.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome-free/css/all.min.css">
    <!-- icheck bootstrap -->
    <link rel="stylesheet" href="/static/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/adminlte/css/adminlte.min.css">
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="/static/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <!-- /.login-logo -->
    <div class="card card-outline card-primary">
        <div class="card-header text-center">
            <a href="/" class="h1"><b>Singte</b></a>
        </div>
        <div class="card-body">
<#--            <p class="login-box-msg">Sign in to start your session</p>-->

            <form method="post">
                <div class="input-group mb-3">
                    <input type="text" id="username" class="form-control" placeholder="账户">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-user"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" id="password" class="form-control" placeholder="密码">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <input id="captchaKey" type="hidden" name="captchaKey" value=""/>
                    <div class="col-5">
                        <div>
                            <input type="text" id="captchaCode" class="form-control" placeholder="验证码">
                        </div>
                    </div>
                    <!-- /.col -->
                    <div class="col-4">
                        <img class="reloadImg" id="captchaImg" src="" style="width: 100px;height: 41px;">
                    </div>
                    <!-- /.col -->
                    <div class="col-3" style="line-height: 41px;">
                        <a class="reloadImg" href="javascript:;">换一张</a>
                    </div>
                    <!-- /.col -->
                </div>
<#--                <div class="row">-->
<#--                    <div class="col-8">-->
<#--                        <div class="icheck-primary">-->
<#--                            <input type="checkbox" id="remember">-->
<#--                            <label for="remember">-->
<#--                                Remember Me-->
<#--                            </label>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                    <!-- /.col &ndash;&gt;-->
<#--                    <div class="col-4">-->
<#--                        <button type="submit" class="btn btn-primary btn-block">Sign In</button>-->
<#--                    </div>-->
<#--                    <!-- /.col &ndash;&gt;-->
<#--                </div>-->
            </form>

            <div class="social-auth-links text-center mt-2 mb-3">
                <a href="#" class="btn btn-block btn-primary" id="loginBtn">
<#--                    <i class="fab fa-facebook mr-2"></i> Sign in using Facebook-->
                    <@spring.message "common.login"/>
                </a>
<#--                <a href="#" class="btn btn-block btn-danger">-->
<#--                    <i class="fab fa-google-plus mr-2"></i> Sign in using Google+-->
<#--                </a>-->
            </div>
            <!-- /.social-auth-links -->

<#--            <p class="mb-1">-->
<#--                <a href="forgot-password.html">I forgot my password</a>-->
<#--            </p>-->
<#--            <p class="mb-0">-->
<#--                <a href="register.html" class="text-center">Register a new membership</a>-->
<#--            </p>-->
        </div>
        <!-- /.card-body -->
    </div>
    <!-- /.card -->
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="/static/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/adminlte/js/adminlte.min.js"></script>
<!-- SweetAlert2 -->
<script src="/static/plugins/sweetalert2/sweetalert2.min.js"></script>
<@StStatic src="admin/login.js" />
</body>
</html>
