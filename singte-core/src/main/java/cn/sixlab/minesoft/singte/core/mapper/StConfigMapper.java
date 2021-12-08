package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.core.models.StConfig;
import org.apache.ibatis.annotations.Param;

public interface StConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StConfig record);

    int insertSelective(StConfig record);

    StConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StConfig record);

    int updateByPrimaryKey(StConfig record);

    void init();

    void runSQL(String sql);

    StConfig selectByKey(@Param("configKey") String configKey);
}