package cn.sixlab.minesoft.singte.core.service;

import cn.sixlab.minesoft.singte.core.common.utils.WebUtils;
import cn.sixlab.minesoft.singte.core.dao.StDomainDao;
import cn.sixlab.minesoft.singte.core.models.StDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService {

    @Autowired
    private StDomainDao domainDao;

    public StDomain activeDomain() {
        String domain = WebUtils.getDomainWithPort();
        StDomain activeDomain = domainDao.selectActiveDomain(domain);

        if (null == activeDomain) {
            activeDomain = domainDao.selectHighWeight();
        }
        return activeDomain;
    }

    /**
     * 获取到当前域名，是否配置了指定类，如果配置了返回对应配置
     * @param domainBean 指定类
     * @return StDomain
     */
    public StDomain activeDomain(String domainBean) {
        String domain = WebUtils.getDomainWithPort();
        return domainDao.selectActiveDomain(domain, domainBean);
    }

    public List<StDomain> selectDomainByBean(String domainBean) {
        return domainDao.selectByBean(domainBean);
    }

}
