<#-- @ftlvariable name="page" type="cn.sixlab.minesoft.singte.core.models.StPage" -->
<#include 'frame/functions.ftlh'>

<@PageFrame title=page.title keywords=[page.title] description=page.title>
    <div class="page whitebg">
        <div class="page-box">
            <div class="page-box-content">
                <h2>${page.title}</h2>

                <p>${page.content?no_esc}</p>
            </div>
        </div>
    </div>
</@PageFrame>