<#-- @ftlvariable name="configs" type="java.util.Map<java.lang.String, java.lang.String>" -->
<#-- @ftlvariable name="notFound" type="java.lang.Boolean" -->
<#include '../frame/functions.ftlh'>

<#macro PageHeader>
    <meta name="author" content="<@StConfig key="st_site_name" default="singte"/>">
    <meta name="copyright" content="<@StConfig key="st_site_name" default="singte"/>">
    <link type="text/css" rel="stylesheet" href="/static/css/article.css">
</#macro>

<@PageFrame title="配置" keywords="配置" description="配置">
    <div class="column_market w1200 pt20 clearfix">
        <#list configs?keys as item>
            <div>${item}  ---  ${configs[item]}</div>
        </#list>
    </div>
</@PageFrame>