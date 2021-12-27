<#-- @ftlvariable name="version" type="java.lang.String" -->

<#macro PageHead>
    <meta name="author" content="singte">
    <meta name="copyright" content="singte">
</#macro>

<@PageFrame>
    <div style="margin: 200px auto;width:150px;align-content: center;">
        <img src="/static/images/wx/mp.jpg" alt="singte"><br/>
        官方微信：<@StConfig key="st_wx_name" default="满茵科技"/><br/>
        <div style="text-align: left;">
            当前版本：${version}
            ____________________<br/>
            国家：${springMacroRequestContext.locale.country}<br/>
            语言：${springMacroRequestContext.locale.language}<br/>
            ____________________<br/>
            ISO3：
            国家：${springMacroRequestContext.locale.ISO3Country}<br/>
            语言：${springMacroRequestContext.locale.ISO3Language}<br/>
            ____________________<br/>
            显示：
            国家：${springMacroRequestContext.locale.displayCountry}<br/>
            语言：${springMacroRequestContext.locale.displayLanguage}<br/>
        </div>
    </div>
</@PageFrame>