<#include '../frame/functions.ftlh'>

<#macro AdminHead>
    <meta name="author" content="<@StConfig key="st_site_name" default="singte"/>">
    <meta name="copyright" content="<@StConfig key="st_site_name" default="singte"/>">
    <link type="text/css" rel="stylesheet" href="/static/css/article.css">
</#macro>

<@AdminFrame title="配置" keywords=["配置"] description="配置">
    <div class="column_market w1200 pt20 clearfix">
        ${test}
    </div>
</@AdminFrame>