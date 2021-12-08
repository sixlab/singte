[#ftl]
[#-- @implicitly included --]
[#-- @ftlvariable name="gWxName" type="java.lang.String" --]
[#-- @ftlvariable name="gWxQrcode" type="java.lang.String" --]
[#-- @ftlvariable name="gWeiboId" type="java.lang.String" --]
[#-- @ftlvariable name="gWeiboQrcode" type="java.lang.String" --]

[#-- @ftlvariable name="gSiteName" type="java.lang.String" --]
[#-- @ftlvariable name="gLogo" type="java.lang.String" --]
[#-- @ftlvariable name="gCopyYear" type="java.lang.String" --]
[#-- @ftlvariable name="gICP" type="java.lang.String" --]

[#-- @ftlvariable name="gMenuGroups" type="java.util.Map<java.lang.String, cn.sixlab.minesoft.singte.models.StMenu[]>" --]
[#-- @ftlvariable name="gHotKeywords" type="java.lang.String[]" --]

[#include 'templates/frame/index.ftlh']
[#macro PageHeader][/#macro]

[#macro StConfig key][/#macro]

[#macro StArticles type='category' num=1 size=10 category=''][/#macro]
[#-- @ftlvariable name="tArticleList" type="cn.sixlab.minesoft.singte.models.StArticle[]" --]
[#-- @ftlvariable name="tArticlePageInfo" type="com.github.pagehelper.PageInfo<cn.sixlab.minesoft.singte.models.StArticle>" --]
