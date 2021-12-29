<#include '../frame/functions.ftlh'>

<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <#assign title><@spring.message "common.login"/></#assign>

    <title>${title}</title>
    <meta name="keywords" content="${title}"/>
    <meta name="description" content="${title}"/>
    <meta name="author" content="<@StConfig key="st_site_name" default="singte"/>">
    <meta name="copyright" content="<@StConfig key="st_site_name" default="singte"/>">

    <link rel="shortcut icon" href="/favicon.ico">
    <link rel="bookmark" href="/favicon.ico">
    <link rel="apple-touch-icon" href="/favicon.ico">

    <link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.login.css"/>
    <link rel="stylesheet" type="text/css" href="/static/libs/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css"/>
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal" method="post">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="username" name="username" type="text" placeholder="账户" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input id="captchaKey" type="hidden" name="captchaKey" value=""/>
                    <input id="captchaCode" class="input-text size-L" type="text" placeholder="验证码"
                           onblur="if(this.value===''){this.value='验证码'}"
                           onclick="if(this.value==='验证码'){this.value='';}"
                           value="验证码" style="width:150px;">
                    <img class="reloadImg" id="captchaImg" src="" style="width: 100px;height: 41px;">
                    <a class="reloadImg" href="javascript:;">看不清，换一张</a>
                </div>
            </div>
<#--            <div class="row cl">-->
<#--                <div class="formControls col-xs-8 col-xs-offset-3">-->
<#--                    <label for="online">-->
<#--                        <input type="checkbox" name="online" id="online" value="">-->
<#--                        使我保持登录状态</label>-->
<#--                </div>-->
<#--            </div>-->
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="" type="button" class="btn btn-success radius size-L"
                           id="loginBtn"
                           value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input name="" type="reset" class="btn btn-default radius size-L"
                           value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright <@StConfig key="st_site_name" default="singte"/></div>

<script type="text/javascript" src="/static/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/static/libs/layer/2.4/layer.js"></script>

<script type="text/javascript" src="/static/h-ui/js/H-ui.js"></script>
<@StStatic type="js" src="admin/login.js" />
</body>
</html>