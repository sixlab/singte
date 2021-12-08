package cn.sixlab.minesoft.singte.core.mapper;

import cn.sixlab.minesoft.singte.core.models.StArticle;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StArticle record);

    int insertSelective(StArticle record);

    StArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StArticle record);

    int updateByPrimaryKeyWithBLOBs(StArticle record);

    int updateByPrimaryKey(StArticle record);

    List<StArticle> selectHot(@Param("size") int size);

    List<StArticle> selectLast(@Param("size")int size);

    Integer selectMaxId();

    StArticle selectRandom(@Param("max") int max, @Param("excludeIds") List<Integer> excludeIds);

    List<StArticle> selectByCategory(@Param("category")String category);

    StArticle selectByAlias(@Param("alias") String alias);

    void addView(@Param("id")Integer id);

    List<StArticle> selectByDate(@Param("begin") Date begin, @Param("end") Date end);
}