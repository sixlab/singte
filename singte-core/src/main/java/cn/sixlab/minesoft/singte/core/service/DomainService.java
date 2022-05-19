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

    public List<StDomain> selectDomainByBean(String domainBean) {
        return domainDao.selectByBean(domainBean);
    }

}
