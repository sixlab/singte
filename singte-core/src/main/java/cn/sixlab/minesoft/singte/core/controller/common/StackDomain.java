package cn.sixlab.minesoft.singte.core.controller.common;

import cn.sixlab.minesoft.singte.core.common.config.BaseDomain;
import cn.sixlab.minesoft.singte.core.common.utils.WebUtils;
import cn.sixlab.minesoft.singte.core.dao.StLinkDao;
import cn.sixlab.minesoft.singte.core.dao.StLinkDomainDao;
import cn.sixlab.minesoft.singte.core.dao.StLinkTypeDao;
import cn.sixlab.minesoft.singte.core.models.StLink;
import cn.sixlab.minesoft.singte.core.models.StLinkDomain;
import cn.sixlab.minesoft.singte.core.models.StLinkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;

@Component
public class StackDomain implements BaseDomain {

    @Autowired
    private StLinkDao linkDao;

    @Autowired
    private StLinkTypeDao typeDao;

    @Autowired
    private StLinkDomainDao domainDao;

    @Override
    public String index(ModelMap modelMap) {
        String domain = WebUtils.getDomainWithPort();

        StLinkDomain linkDomain = domainDao.selectEnableByDomain(domain);

        if (linkDomain == null) {
            return "error";
        }

        String domainGroup = linkDomain.getMultiDomainGroup();
        List<StLinkType> typeList = typeDao.selectEnableByDomain(domainGroup);
        List<StLink> groupList = linkDao.selectGroupByDomain(domainGroup);
        List<StLink> linkList = linkDao.selectEnableByDomain(domainGroup);

        modelMap.put("linkDomain", linkDomain);
        modelMap.put("typeList", typeList);
        modelMap.put("groupList", groupList);
        modelMap.put("linkList", linkList);

        return "/index";
    }

    @Override
    public String test(ModelMap modelMap) {
        return "/test";
    }

}
