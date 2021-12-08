package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.module.minesoft.models.SeoItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeoItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SeoItem record);

    int insertSelective(SeoItem record);

    SeoItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SeoItem record);

    int updateByPrimaryKey(SeoItem record);

    List<SeoItem> selectItems(@Param("dataType")String dataType);

    List<SeoItem> selectByCategory(@Param("category") String category);

    SeoItem selectUser(@Param("dataType")String dataType, @Param("uid") String uid);

    List<String> selectUsers();

}