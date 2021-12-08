package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.core.models.StUser;
import org.apache.ibatis.annotations.Param;

public interface StUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StUser record);

    int insertSelective(StUser record);

    StUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StUser record);

    int updateByPrimaryKey(StUser record);

    StUser selectByUsername(@Param("username") String username);
}