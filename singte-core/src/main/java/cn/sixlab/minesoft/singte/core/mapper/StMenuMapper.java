package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.core.models.StMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StMenu record);

    int insertSelective(StMenu record);

    StMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StMenu record);

    int updateByPrimaryKey(StMenu record);

    List<StMenu> selectGroupMenus(@Param("menuGroup") String menuGroup);

}