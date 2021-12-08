package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.module.minesoft.models.StePoemAtom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StePoemAtomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StePoemAtom record);

    int insertSelective(StePoemAtom record);

    StePoemAtom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StePoemAtom record);

    int updateByPrimaryKey(StePoemAtom record);

    List<StePoemAtom> selectByKeywords(@Param("keywordList") String[] keywordList);
}