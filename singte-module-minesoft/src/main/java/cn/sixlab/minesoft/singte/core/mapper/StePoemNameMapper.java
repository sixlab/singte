package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.module.minesoft.models.StePoemName;

import java.util.List;

public interface StePoemNameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StePoemName record);

    int insertSelective(StePoemName record);

    StePoemName selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StePoemName record);

    int updateByPrimaryKey(StePoemName record);

    List<StePoemName> selectPoemNames();
}