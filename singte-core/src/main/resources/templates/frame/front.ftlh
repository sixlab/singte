<#--
页面整体的模板，需要用户自己实现 PageHead：
<#macro PageHead></#macro>
-->
<#macro PageFrame title="" keywords=[] description="">
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>${title!'singte首页'}</title>
    <meta name="keywords" content="${keywords?join(",")!'singte'}"/>
    <meta name="description" content="${description!'singte'}"/>
    <link rel="shortcut icon" href="/favicon.ico">
    <link rel="bookmark" href="/favicon.ico">
    <link rel="apple-touch-icon" href="<@StConfig key="st_site_logo" default="/static/logo-h.png"/>">
    <script src="/static/libs/jquery/1.9.1/jquery.min.js"></script>
    <link type="text/css" rel="stylesheet" href="/static/css/normalize.css">
    <link type="text/css" rel="stylesheet" href="/static/css/base.css">
    <@PageHead />
</head>
<body>
<@BodyHeader />
<!--Cnav-->
<div class="Cnav_wrap">
    <ul class="w1200 Cnav mb20 clearfix">
        <@MenuItems groupName="nav" hasLast=true />
    </ul>
</div>
<!--/Cnav-->
<#nested>
<@BodyFooter />
</body>
</html>
</#macro>

<#--  页面头部  -->
<#macro BodyHeader>
<!--top-->
<div class="top_wrap">
    <div class="clearfix top w1200">
        <div class="fl top_left"><a href="/" target="_blank" title="首页">首页</a></div>
        <ul class="fr clearfix top_right">
            <li class="register"><a href="/reg">注册</a></li>
            <li class="login"><a href="/login">登陆</a></li>
            <li class="weibo"><a href="https://weibo.com/<@StConfig key="st_wbo_id" default="314566975"/>">微博</a>
                <div><img src="<@StConfig key="st_wbo_qr" default="/static/logo.png"/>" alt="微博"></div>
            </li>
            <li class="wechat"><a href="javascript:;">微信</a>
                <div><img src="<@StConfig key="st_wx_qr" default="/static/images/wx/mp.jpg"/>" alt="微信二维码"></div>
            </li>
        </ul>
    </div>
</div>
<!--/top-->
<!--head-->
<div class="w1200 head clearfix">
    <h1 class="fl logo mt33"><a href="/" target="_blank" style="background: url(<@StConfig key="st_site_logo" default="/static/logo-h.png"/>) no-repeat;" title="首页">首页</a></h1>
    <div class="search fr mt33">
        <form action="/search" method="get">
            <div class="searchBox clearfix">
                <label>
                    <input class="fl" name="keyword" type="text" value="请输入所搜资讯关键字"
                           onFocus="if(this.value=='请输入所搜资讯关键字'){this.value=''}"
                           onblur="if(this.value===''){this.value='请输入所搜资讯关键字'}"/>
                </label>
                <input class="fl zoom" value="" type="submit"/>
            </div>
        </form>
        <ul class="clearfix mt5">
            <@StKeyword>
                <#list stHotKeywords as item>
                    <li><a href="/keyword/${item.keyword}">${item.keyword}</a></li>
                </#list>
            </@StKeyword>
        </ul>
    </div>
</div>
<!--head-->
</#macro>

<#--  页面脚部  -->
<#macro BodyFooter>
<!--footer-->
<div class="footer">
    <div class="w1200 clearfix">
        <div class="fl copyright">
            <p class="p1">
                <@MenuItems groupName="footer" hasLi=false />
            </p>
            <p class="mt40">©<@StConfig key="st_copyright_year" default="2021"/> <@StConfig key="st_site_name" default="singte"/>版权所有 <@StConfig key="st_icp" /></p>
        </div>
        <div class="clearfix fl wechat">
            <img src="<@StConfig key="st_wx_qr" default="/static/images/wx/mp.jpg"/>" class="fl ml30">
            <div class="fl"><p class="p1"><@StConfig key="st_site_name" default="singte"/>官方微信</p>
                <p class="p3"><@StConfig key="st_wx_name" default="满茵科技"/></p></div>
        </div>
    </div>
</div>
<!--/footer-->
<!--gotop-->
<div>
    <ul class="sibar">
        <li class="li1"><a href="javascript:;" class="a1"><img src="/static/images/sibar/sibar_qr.png" class="sibar_img_qr"><i class="wxmp"><img src="<@StConfig key="st_wx_qr" default="/static/images/wx/mp.jpg"/>"></i></a></li>
        <li class="li2"><a href="javascript:;" class="a2"><img src="/static/images/sibar/sibar_feedback.png" class="sibar_img_feedback"><i>用户反馈</i></a></li>
        <li class="li3"><a href="#" class="a3"><img src="/static/images/sibar/sibar_gotop.png" class="sibar_img_gotop"><i>返回顶部</i></a></li>
    </ul>
</div>
    <script>
        $(".sibar_img_qr").mousemove(function () {
            $(this).attr("src", "/static/images/sibar/sibar_qr_l.png")
        })
        $(".sibar_img_qr").mouseout(function () {
            $(this).attr("src", "/static/images/sibar/sibar_qr.png")
        })
        $(".sibar_img_feedback").mousemove(function () {
            $(this).attr("src", "/static/images/sibar/sibar_feedback_l.png")
        })
        $(".sibar_img_feedback").mouseout(function () {
            $(this).attr("src", "/static/images/sibar/sibar_feedback.png")
        })
        $(".sibar_img_gotop").mousemove(function () {
            $(this).attr("src", "/static/images/sibar/sibar_gotop_l.png")
        })
        $(".sibar_img_gotop").mouseout(function () {
            $(this).attr("src", "/static/images/sibar/sibar_gotop.png")
        })
    </script>
</#macro>

<#--  页面菜单  -->
<#macro MenuItems groupName hasLi=true hasLast=false>

<#--        <li><a href="#">咨询首页</a></li>-->
<#--        <li class="on"><a href="#">市场</a></li>-->
<#--        <li><a href="#">专栏</a></li>-->
<#--        <li><a href="#">家装</a></li>-->
<#--        <li><a href="#">建筑</a></li>-->
<#--        <li><a href="#">电气</a></li>-->
<#--        <li><a href="#">机械</a></li>-->
<#--        <li><a href="#">化工</a></li>-->
<#--        <li><a href="#">工具</a></li>-->
<#--        <li><a href="#">电子</a></li>-->
<#--        <li><a href="#">安防</a></li>-->
<#--        <li class="last_li"><a href="#">百科</a></li>-->

<@StMenu group=groupName>
    <#list stMenuGroup as item>
        <#assign isLast = hasLast && !item_has_next />
        <#if hasLi>
            <li class="st-menu-li<#if isLast> last_li</#if>">
        </#if>
        <a <#if !hasLi && isLast>class="last_li" </#if>href="${item.menuLink}">${item.menuName}</a>
        <#if hasLi>
            </li>
        </#if>
    </#list>
</@StMenu>

</#macro>
