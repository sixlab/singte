[#ftl]
[#-- @implicitly included --]

[#include 'templates/frame/index.ftlh']

[#macro PageHead][/#macro]

[#macro StMenu group][/#macro]
[#-- @ftlvariable name="stMenuGroup" type="java.util.Collection<cn.sixlab.minesoft.singte.core.models.StMenu>" --]

[#macro StConfig key default=""][/#macro]

[#macro StKeyword][/#macro]
[#-- @ftlvariable name="stHotKeywords" type="cn.sixlab.minesoft.singte.core.models.StKeyword[]" --]

[#macro StArticles type='category' num=1 size=10 category=''][/#macro]
[#-- @ftlvariable name="tArticleList" type="cn.sixlab.minesoft.singte.core.models.StArticle[]" --]
[#-- @ftlvariable name="tArticlePageInfo" type="cn.sixlab.minesoft.singte.core.common.pager.PageResult<cn.sixlab.minesoft.singte.core.models.StArticle>" --]

[#macro StStatic src type='js'][/#macro]