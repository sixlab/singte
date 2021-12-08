package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.core.models.StCategory;

public interface StCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StCategory record);

    int insertSelective(StCategory record);

    StCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StCategory record);

    int updateByPrimaryKey(StCategory record);
}