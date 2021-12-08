package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.module.minesoft.models.SeoData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeoDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SeoData record);

    int insertSelective(SeoData record);

    SeoData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SeoData record);

    int updateByPrimaryKey(SeoData record);

    List<SeoData> selectByDate(@Param("date") String date);

    List<SeoData> selectBeforeDate(@Param("date") String date);

    List<SeoData> selectUserCategory(@Param("uid") String uid, @Param("category") String category, @Param("date") String date);

    SeoData selectUserDateCategory(@Param("uid") String uid, @Param("date") String date, @Param("category") String category);
}