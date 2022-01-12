<#-- @ftlvariable name="article" type="cn.sixlab.minesoft.singte.core.models.StArticle" -->
<#include '../frame/functions.ftlh'>
<#assign static>
    <script src="/static/cssmoban/js/hc-sticky.js"></script>
    <script src="/static/cssmoban/js/aside-sticky.js"></script>
    <@StStatic src="article/article.js" />
</#assign>
<@PageFrame title=article.title keywords=article.keywords description=article.summary static=static aside=true>
    <input type="hidden" value="${article.id}" class="st-article-id">
    <div class="left-box">
        <div class="whitebg">
            <div class="news-title">
                <span class="weizhi">
                    您现在的位置是：
                    <a href="/">首页</a>
                    >
                    <a href="/category/${article.category}">${article.category}</a>
                    >
                    <a href="###">正文</a>
                </span>
                <h2>正文</h2>
            </div>
            <div class="newstext-box">
                <h1 class="news-title-h1">${article.title}</h1>
                <div class="keywords">
                    <a href="/">时间</a><a href="/">旅行者</a><a href="/">故事</a>
                </div>
                <div class="picview-other">
                    <span class="po-from">${article.author}</span>
                    <span class="po-date">${article.publishTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                    <span class="po-classname">${article.category}</span>
                    <span class="po-view">${article.viewCount}</span>
                </div>
                <div class="smalltext">
                    <i></i>
                    ${article.summary}
                </div>
                <div class="newstext">
                    ${article.content?no_esc}
                </div>
                <div class="diggit">
                    <a href="###"> 很赞哦！ </a>(<b id="diggnum">${article.thumbCount}</b>)
                </div>
                <div class="clear"></div>
<#--                <div class="share"><img src="images/replace/wxgzh.jpg">-->
<#--                    <div class="share-text">-->
<#--                        <p>扫描关注XXX微信公众号，第一时间获取网站模板更新动态</p>-->
<#--                        <p>转载请说明来源于"XXX排名建站"</p>-->
<#--                        <p>本文地址：<a href="http://www.adminbuy.cn/news/s/900.html" target="_blank">http://www.adminbuy.cn/news/s/900.html</a>-->
<#--                        </p>-->
<#--                    </div>-->
<#--                </div>-->
<#--                <div class="clear"></div>-->
<#--                <div class="info-pre-next">-->
<#--                    <ul>-->
<#--                        <li><a href="/"><i><em>上一篇</em><img src="images/list/pic02.jpg"></i>-->
<#--                                <h2>为什么说10月24日是程序员的节日？</h2>-->
<#--                                <p>不知道自己以后还能不能继续干这一行，结婚不到一年，也许某天开始会离开一两年，当再回来工作的时候，不知道是否能有单位再愿意聘请我，或者说自己能否再胜任这工作</p>-->
<#--                            </a></li>-->
<#--                        <li><a href="/"><i><em>下一篇</em><img src="images/list/pic03.jpg"></i>-->
<#--                                <h2>十条设计原则教你学会如何设计网页布局!</h2>-->
<#--                                <p>-->
<#--                                    网页常见的布局有很多种,单列布局,多列布局.其中单列布局是国外很多网站比较常用的.咱们很多站长以及门户网站都使用的是是两列布局,很少用三列布局的.下面我来分享下我们常用的网页布局格式</p>-->
<#--                            </a></li>-->
<#--                    </ul>-->
<#--                </div>-->
            </div>
        </div>
<#--        <div class="clear blank"></div>-->
<#--        <div class="otherlink whitebg">-->
<#--            <div class="news-title">-->
<#--                <h2>相关文章</h2>-->
<#--            </div>-->
<#--            <ul>-->
<#--                <li><a target="_blank" href="http://www.adminbuy.cn/news/life/2018-06-17/873.html">安静地做一个爱设计的女子</a></li>-->
<#--                <li><a target="_blank" href="http://www.adminbuy.cn/news/s/900.html">网易博客关闭，何不从此开始潇洒行走江湖！</a></li>-->
<#--                <li><a target="_blank" href="http://www.adminbuy.cn/jstt/web/923.html">【排名技术建站】杨青助你快速实现主站为核心的搜索排名霸屏</a>-->
<#--                </li>-->
<#--                <li><a target="_blank" href="http://www.adminbuy.cn/news/s/876.html">个人博客，我为什么要用帝国cms？</a></li>-->
<#--                <li><a target="_blank" href="http://www.adminbuy.cn/news/life/2018-04-27/816.html">个人博客，属于我的小世界！</a>-->
<#--                </li>-->
<#--                <li><a target="_blank" href="http://www.adminbuy.cn/qiyewangzhanmuban/963.html">蓝色企业网站模板超实用，完美符合SEO优化，适合企业公司建站的模板</a>-->
<#--                </li>-->
<#--                <li><a target="_blank" href="http://www.adminbuy.cn/news/s/884.html">制作个人博客，我是这么收费的？</a></li>-->
<#--                <li><a target="_blank" href="http://www.adminbuy.cn/news/s/944.html">【告别2018】耕耘才有所得，付出才有收获</a></li>-->
<#--                <li><a target="_blank" href="http://www.adminbuy.cn/jstt/web/885.html">帝国cms结合项如何实现多条件查询</a></li>-->
<#--                <li><a target="_blank" href="http://www.adminbuy.cn/jstt/web/919.html">帝国CMS灵动标签调用昨天、今天、某天及以前以后等特定时间发布的文章</a>-->
<#--                </li>-->
<#--            </ul>-->
<#--        </div>-->
<#--        <div class="advimg advimg-big"><a href="http://www.adminbuy.cn/jstt/web/945.html" target="_blank"><img-->
<#--                        src="images/banner/banner1.jpg"></a></div>-->
<#--        <div class="pinglun-box whitebg">-->
<#--            <div class="news-title">-->
<#--                <h2>文章评论</h2>-->
<#--            </div>-->
<#--            <div class="pinglun">-->
<#--                <div id="plpost">-->
<#--                    <p class="saying"><span><a href="/">共有5条评论</a></span>来说两句吧...</p>-->
<#--                    <input name="username" type="text" class="pl-name" id="username" value="" size="16"-->
<#--                           placeholder="用户名">-->
<#--                    <input name="key" type="text" class="pl-yzm" size="16" placeholder="验证码">-->
<#--                    <textarea name="saytext" rows="6" id="saytext"></textarea>-->
<#--                    <input name="imageField" type="submit" value="提交">-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
    </div>
</@PageFrame>