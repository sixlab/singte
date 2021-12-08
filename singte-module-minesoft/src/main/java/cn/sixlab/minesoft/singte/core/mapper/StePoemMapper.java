package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.module.minesoft.models.StePoem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StePoemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StePoem record);

    int insertSelective(StePoem record);

    StePoem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StePoem record);

    int updateByPrimaryKeyWithBLOBs(StePoem record);

    int updateByPrimaryKey(StePoem record);

    List<StePoem> selectPoems(@Param("withBLOBs") boolean withBLOBs);
}