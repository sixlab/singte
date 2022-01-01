[#ftl]
[#-- @implicitly included --]

[#include 'templates/frame/index.ftlh']

[#macro StMenu group][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.MenuDirective"  --]
[#-- @ftlvariable name="requestUri" type="java.lang.String" --]
[#-- @ftlvariable name="stFolderOpen" type="java.lang.Boolean" --]
[#-- @ftlvariable name="stMenuGroup" type="java.util.Collection<cn.sixlab.minesoft.singte.core.models.StMenu>" --]
[#-- @ftlvariable name="stHasMenu" type="java.lang.Boolean" --]
[#-- @ftlvariable name="stMenuGroupList" type="cn.sixlab.minesoft.singte.core.models.StMenu[]" --]

[#macro StConfig key default=""][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.ConfigDirective"  --]

[#macro StKeyword][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.KeywordDirective"  --]
[#-- @ftlvariable name="stHotKeywords" type="cn.sixlab.minesoft.singte.core.models.StKeyword[]" --]

[#macro StArticles type='category' num=1 size=10 category=''][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.ArticlesDirective"  --]
[#-- @ftlvariable name="tArticleList" type="cn.sixlab.minesoft.singte.core.models.StArticle[]" --]
[#-- @ftlvariable name="tArticlePageInfo" type="cn.sixlab.minesoft.singte.core.common.pager.PageResult<cn.sixlab.minesoft.singte.core.models.StArticle>" --]

[#macro StStatic src type='js'][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.StaticDirective"  --]
