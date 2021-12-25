[#ftl]
[#-- @implicitly included --]
[#-- @ftlvariable name="stHotKeywords" type="java.util.Collection<java.lang.String>" --]
[#-- @ftlvariable name="stMenuGroup" type="java.util.Collection<cn.sixlab.minesoft.singte.core.models.StMenu>" --]

[#include 'templates/frame/index.ftlh']

[#macro PageHeader][/#macro]

[#macro StMenu group][/#macro]

[#macro StConfig key default=""][/#macro]

[#macro StKeyword][/#macro]

[#macro StArticles type='category' num=1 size=10 category=''][/#macro]
[#-- @ftlvariable name="tArticleList" type="cn.sixlab.minesoft.singte.models.StArticle[]" --]
[#-- @ftlvariable name="tArticlePageInfo" type="com.github.pagehelper.PageInfo<cn.sixlab.minesoft.singte.models.StArticle>" --]
