<#-- @ftlvariable name="linkDomain" type="cn.sixlab.minesoft.singte.core.models.StLinkDomain" -->
<#-- @ftlvariable name="linkList" type="java.util.List<cn.sixlab.minesoft.singte.core.models.StLink>" -->
<#-- @ftlvariable name="groupList" type="java.util.List<cn.sixlab.minesoft.singte.core.models.StLink>" -->
<#-- @ftlvariable name="typeList" type="java.util.List<cn.sixlab.minesoft.singte.core.models.StLinkType>" -->
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="author" content="viggo" />
    <title>${linkDomain.domainTitle}</title>
    <meta name="keywords" content="${linkDomain.domainKeyword}">
    <meta name="description" content="${linkDomain.domainDescription}">
    <link rel="shortcut icon" href="${linkDomain.domainFavicon}">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
    <link rel="stylesheet" href="/static/stack/css/fonts/linecons/css/linecons.css">
    <link rel="stylesheet" href="/static/stack/css/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/stack/css/bootstrap.css">
    <link rel="stylesheet" href="/static/stack/css/xenon-core.css">
    <link rel="stylesheet" href="/static/stack/css/xenon-components.css">
    <link rel="stylesheet" href="/static/stack/css/xenon-skins.css">
    <link rel="stylesheet" href="/static/stack/css/nav.css">
    <script src="/static/stack/js/jquery-1.11.1.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- / FB Open Graph -->
    <meta property="og:type" content="article">
    <meta property="og:title" content="${linkDomain.domainTitle}">
    <meta property="og:description" content="${linkDomain.domainDescription}">
    <meta property="og:image" content="${linkDomain.domainFavicon}">
    <meta property="og:site_name" content="${linkDomain.domainTitle}">
    <!-- / Twitter Cards -->
    <meta name="twitter:card" content="summary_large_image">
    <meta name="twitter:title" content="${linkDomain.domainTitle}">
    <meta name="twitter:description" content="${linkDomain.domainDescription}">
    <meta name="twitter:image" content="${linkDomain.domainFavicon}">
</head>

<body class="page-body">
    <!-- skin-white -->
    <div class="page-container">
        <div class="sidebar-menu toggle-others fixed">
            <div class="sidebar-menu-inner">
                <header class="logo-env">
                    <!-- logo -->
                    <div class="logo">
                        <a href="index.html" class="logo-expanded">
                            <img src="/static/stack/images/logo@2x.png" width="100%" alt="" />
                        </a>
                        <a href="index.html" class="logo-collapsed">
                            <img src="/static/stack/images/logo-collapsed@2x.png" width="40" alt="" />
                        </a>
                    </div>
                    <div class="mobile-menu-toggle visible-xs">
                        <a href="#" data-toggle="user-info-menu">
                            <i class="linecons-cog"></i>
                        </a>
                        <a href="#" data-toggle="mobile-menu">
                            <i class="fa-bars"></i>
                        </a>
                    </div>
                </header>
                <ul id="main-menu" class="main-menu">
                    <#list typeList as type>
                        <li>
                            <a <#if type.hasSub == 0>href="#${type.linkType}" class="smooth"</#if>>
                                <i class="linecons-star"></i>
                                <span class="title">${type.linkType}</span>
                            </a>
                            <#if type.hasSub != 0>
                                <ul>
                                    <#list groupList as group>
                                        <#if type.linkType == group.linkType>
                                            <li>
                                                <a href="#${group.linkGroup}" class="smooth">
                                                    <span class="title">${group.linkGroup}</span>
                                                </a>
                                            </li>
                                        </#if>
                                    </#list>
                                </ul>
                            </#if>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
        <div class="main-content">
            <nav class="navbar user-info-navbar" role="navigation">
                <!-- User Info, Notifications and Menu Bar -->
                <!-- Left links for user info navbar -->
                <ul class="user-info-menu left-links list-inline list-unstyled">
                    <li class="hidden-sm hidden-xs">
                        <a href="#" data-toggle="sidebar">
                            <i class="fa-bars"></i>
                        </a>
                    </li>
                    <li class="dropdown hover-line language-switcher">
                        <a href="../cn/index.html" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="/static/stack/images/flags/flag-cn.png" alt="flag-cn" /> Chinese
                        </a>
                        <ul class="dropdown-menu languages">
                            <li>
                                <a href="../en/index.html">
                                    <img src="/static/stack/images/flags/flag-us.png" alt="flag-us" /> English
                                </a>
                            </li>
                            <li class="active">
                                <a href="../cn/index.html">
                                    <img src="/static/stack/images/flags/flag-cn.png" alt="flag-cn" /> Chinese
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <ul class="user-info-menu right-links list-inline list-unstyled">
                    <li class="hidden-sm hidden-xs">
                        <a href="https://github.com/WebStackPage/WebStackPage.github.io" target="_blank">
                            <i class="fa-github"></i>  GitHub
                        </a>
                    </li>
                </ul>
                <!-- <a href="https://github.com/WebStackPage/WebStackPage.github.io" target="_blank"><img style="position: absolute; top: 0; right: 0; border: 0;" src="https://s3.amazonaws.com/github/ribbons/forkme_right_darkblue_121621.png" alt="Fork me on GitHub"></a> -->

            </nav>

            <#list groupList as group>
                <h4 class="text-gray"><i class="linecons-tag" style="margin-right: 7px;" id="${group.linkGroup}"></i>${group.linkGroup}</h4>

                <#assign index = 0/>
                <#list linkList as link>
                    <#if group.linkGroup == link.linkGroup>
                        <#assign index++/>
                        <#if index == 1>
                            <div class="row">
                        </#if>

                        <div class="col-sm-3">
                            <div class="xe-widget xe-conversations box2 label-info"
                                 onclick="window.open('${link.linkUrl}', '_blank')" data-toggle="tooltip"
                                 data-placement="bottom" title="" data-original-title="${link.linkUrl}">
                                <div class="xe-comment-entry">
                                    <a class="xe-user-img">
                                        <#assign linkIcon = "/favicon.ico"/>
                                        <#if link.linkIcon?? && link.linkIcon != "">
                                            <#assign linkIcon = link.linkIcon/>
                                        </#if>
                                        <img src="${linkIcon}" class="lozad img-circle" width="40"  alt="${link.linkName}"/>
                                    </a>
                                    <div class="xe-comment">
                                        <a href="#" class="xe-user-name overflowClip_1">
                                            <strong>${link.linkName}</strong>
                                        </a>
                                        <p class="overflowClip_2">${link.linkRemark}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <#if index == 4>
                            </div>
                            <#assign index = 0/>
                        </#if>
                    </#if>
                </#list>

                <#if (index >0 && index < 4)>
                    </div>
                </#if>
                <br/>
            </#list>

            <!-- Main Footer -->
            <!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
            <!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
            <!-- Or class "fixed" to  always fix the footer to the end of page -->
            <footer class="main-footer sticky footer-type-1">
                <div class="footer-inner">
                    <!-- Add your copyright text here -->
                    <div class="footer-text">
                        &copy; 2022
                        <a href="../cn/about.html"><strong>WebStack</strong></a> design by <a href="http://viggoz.com" target="_blank"><strong>Viggo</strong></a>
                        <!--  - Purchase for only <strong>23$</strong> -->
                    </div>
                    <!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
                    <div class="go-up">
                        <a href="#" rel="go-top">
                            <i class="fa-angle-up"></i>
                        </a>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <!-- 锚点平滑移动 -->
    <script type="text/javascript">
    $(document).ready(function() {
         //img lazy loaded
         const observer = lozad();
         observer.observe();

        $(document).on('click', '.has-sub', function(){
            var _this = $(this)
            if(!$(this).hasClass('expanded')) {
               setTimeout(function(){
                    _this.find('ul').attr("style","")
               }, 300);
              
            } else {
                $('.has-sub ul').each(function(id,ele){
                    var _that = $(this)
                    if(_this.find('ul')[0] != ele) {
                        setTimeout(function(){
                            _that.attr("style","")
                        }, 300);
                    }
                })
            }
        })
        $('.user-info-menu .hidden-sm').click(function(){
            if($('.sidebar-menu').hasClass('collapsed')) {
                $('.has-sub.expanded > ul').attr("style","")
            } else {
                $('.has-sub.expanded > ul').show()
            }
        })
        $("#main-menu li ul li").click(function() {
            $(this).siblings('li').removeClass('active'); // 删除其他兄弟元素的样式
            $(this).addClass('active'); // 添加当前元素的样式
        });
        $("a.smooth").click(function(ev) {
            ev.preventDefault();

            public_vars.$mainMenu.add(public_vars.$sidebarProfile).toggleClass('mobile-is-visible');
            ps_destroy();
            $("html, body").animate({
                scrollTop: $($(this).attr("href")).offset().top - 30
            }, {
                duration: 500,
                easing: "swing"
            });
        });
        return false;
    });

    var href = "";
    var pos = 0;
    $("a.smooth").click(function(e) {
        $("#main-menu li").each(function() {
            $(this).removeClass("active");
        });
        $(this).parent("li").addClass("active");
        e.preventDefault();
        href = $(this).attr("href");
        pos = $(href).position().top - 30;
    });
    </script>
    <!-- Bottom Scripts -->
    <script src="/static/stack/js/bootstrap.min.js"></script>
    <script src="/static/stack/js/TweenMax.min.js"></script>
    <script src="/static/stack/js/resizeable.js"></script>
    <script src="/static/stack/js/joinable.js"></script>
    <script src="/static/stack/js/xenon-api.js"></script>
    <script src="/static/stack/js/xenon-toggles.js"></script>
    <!-- JavaScripts initializations and stuff -->
    <script src="/static/stack/js/xenon-custom.js"></script>
    <script src="/static/stack/js/lozad.js"></script>
</body>

</html>
