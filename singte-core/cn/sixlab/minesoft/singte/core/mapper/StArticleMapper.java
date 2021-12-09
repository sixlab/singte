package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.core.models.StArticle;

public interface StArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StArticle record);

    int insertSelective(StArticle record);

    StArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StArticle record);

    int updateByPrimaryKeyWithBLOBs(StArticle record);

    int updateByPrimaryKey(StArticle record);
}