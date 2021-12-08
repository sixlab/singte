package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.core.models.StData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StData record);

    int insertSelective(StData record);

    StData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StData record);

    int updateByPrimaryKeyWithBLOBs(StData record);

    int updateByPrimaryKey(StData record);

    List<StData> selectByGroup(@Param("dataGroup") String dataGroup);

    StData selectByGroupKey(@Param("dataGroup") String dataGroup, @Param("dataKey") String dataKey);
}