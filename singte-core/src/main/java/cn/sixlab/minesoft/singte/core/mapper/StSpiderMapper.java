package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.core.models.StSpider;

public interface StSpiderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StSpider record);

    int insertSelective(StSpider record);

    StSpider selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StSpider record);

    int updateByPrimaryKey(StSpider record);
}