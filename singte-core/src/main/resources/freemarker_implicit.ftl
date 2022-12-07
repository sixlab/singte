[#ftl]
[#-- @implicitly included --]

[#include 'templates/frame/index.ftlh']

[#macro StConfig key default=""][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.ConfigDirective"  --]

[#macro StStatic type='js' src="" prefix="/staic/type" keys=[]][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.StaticDirective"  --]

[#macro StUser][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.UserDirective"  --]
[#-- @ftlvariable name="stUser" type="cn.sixlab.minesoft.singte.core.models.StUser"  --]

[#macro StLang][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.LangDirective"  --]
[#-- @ftlvariable name="stCurrentLangIcon" type="java.lang.String" --]
[#-- @ftlvariable name="stLangList" type="cn.sixlab.minesoft.singte.core.models.StLang[]" --]

[#macro StAside][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.AsideDirective"  --]
[#-- @ftlvariable name="stWidgetList" type="cn.sixlab.minesoft.singte.core.models.StWidget[]" --]

[#macro StKeyword num=5][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.KeywordDirective"  --]
[#-- @ftlvariable name="stHotKeywords" type="cn.sixlab.minesoft.singte.core.models.StKeyword[]" --]

[#macro StArticles type='category' num=1 size=10 category=''][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.ArticlesDirective"  --]
[#-- @ftlvariable name="stArticleList" type="cn.sixlab.minesoft.singte.core.models.StArticle[]" --]
[#-- @ftlvariable name="stArticlePageInfo" type="cn.sixlab.minesoft.singte.core.common.pager.PageResult<cn.sixlab.minesoft.singte.core.models.StArticle>" --]

[#macro StMenu group][/#macro]
[#-- @ftlvariable name="class" type="cn.sixlab.minesoft.singte.core.common.directive.MenuDirective"  --]
[#-- @ftlvariable name="requestUri" type="java.lang.String" --]
[#-- @ftlvariable name="stFolderOpen" type="java.lang.Boolean" --]
[#-- @ftlvariable name="stMenuGroup" type="java.util.Collection<cn.sixlab.minesoft.singte.core.models.StMenu>" --]
[#-- @ftlvariable name="stHasMenu" type="java.lang.Boolean" --]
[#-- @ftlvariable name="stMenuGroupList" type="cn.sixlab.minesoft.singte.core.models.StMenu[]" --]
