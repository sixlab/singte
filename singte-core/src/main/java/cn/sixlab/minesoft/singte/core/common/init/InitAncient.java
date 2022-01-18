package cn.sixlab.minesoft.singte.core.common.init;

import cn.sixlab.minesoft.singte.core.dao.SteAncientCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSetDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import cn.sixlab.minesoft.singte.core.models.SteAncientSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitAncient implements BaseInitComponent {

    @Autowired
    private SteAncientSetDao ancientSetDao;

    @Autowired
    private SteAncientCategoryDao categoryDao;

    /**
     * 经部：易类 书类 诗类 礼类 春秋类 孝经类 五经总义类 四书类 乐类 小学类/蒙学类
     * 史部：正史类 编年类 纪事本末类 杂史类 别史类 诏令奏议类 传记类 史钞类 展开
     * 子部：儒家类 兵家类 法家类 农家类 医家类 天文算法类 术数类 艺术类 谱录类 展开
     * 集部：楚辞类 别集类 总集类 诗文评类 词曲类
     */
    @Override
    public void init() {
        checkSet("经部", 1);
        checkSet("史部", 2);
        checkSet("子部", 3);
        checkSet("集部", 4);

        checkCategory("经部", "易类", 1);
        checkCategory("经部", "书类", 2);
        checkCategory("经部", "诗类", 3);
        checkCategory("经部", "礼类", 4);
        checkCategory("经部", "春秋类", 5);
        checkCategory("经部", "孝经类", 6);
        checkCategory("经部", "五经总义类", 7);
        checkCategory("经部", "四书类", 8);
        checkCategory("经部", "乐类", 9);
        checkCategory("经部", "蒙学类", 10);

        checkCategory("史部", "正史类", 1);
        checkCategory("史部", "编年类", 2);
        checkCategory("史部", "纪事本末类", 3);
        checkCategory("史部", "杂史类", 4);
        checkCategory("史部", "别史类", 5);
        checkCategory("史部", "诏令奏议类", 6);
        checkCategory("史部", "传记类", 7);
        checkCategory("史部", "史钞类", 8);

        checkCategory("史部", "儒家类", 1);
        checkCategory("史部", "兵家类", 2);
        checkCategory("史部", "法家类", 3);
        checkCategory("史部", "农家类", 4);
        checkCategory("史部", "医家类", 5);
        checkCategory("史部", "天文算法类", 6);
        checkCategory("史部", "术数类", 7);
        checkCategory("史部", "艺术类", 8);
        checkCategory("史部", "谱录类", 9);

        checkCategory("史部", "楚辞类", 1);
        checkCategory("史部", "别集类", 2);
        checkCategory("史部", "总集类", 3);
        checkCategory("史部", "诗文评类", 4);
        checkCategory("史部", "词曲类", 5);
    }

    private void checkSet(String ancientSet, int weight) {
        SteAncientSet steAncientSet = ancientSetDao.selectByName(ancientSet);

        if (steAncientSet == null) {
            steAncientSet = new SteAncientSet();
            steAncientSet.setAncientSet(ancientSet);
            steAncientSet.setCount(0);
            steAncientSet.setWeight(weight);
            steAncientSet.setIntro("");
            steAncientSet.setCreateTime(new Date());

            ancientSetDao.save(steAncientSet);
        }
    }

    private void checkCategory(String ancientSet, String ancientCategory, int weight) {
        SteAncientCategory steAncientCategory = categoryDao.selectBySetAndName(ancientSet, ancientCategory);

        if (steAncientCategory == null) {
            steAncientCategory = new SteAncientCategory();
            steAncientCategory.setAncientSet(ancientSet);
            steAncientCategory.setAncientCategory(ancientCategory);
            steAncientCategory.setCount(0);
            steAncientCategory.setWeight(weight);
            steAncientCategory.setIntro("");
            steAncientCategory.setCreateTime(new Date());

            categoryDao.save(steAncientCategory);
        }
    }
}
