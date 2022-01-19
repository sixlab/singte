
package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;

public class SteAncientSet extends BaseModel {
    // 经学、小学、诸子、天文、地理、医律、史学、金石、类书、词赋、书画、小说
    // 经史子集 -> 四库全书 -> 的订单 -> 的订单 -> 的订单
    // 《四库全书》的内容是十分丰富的。按照内容分类分经、史、子、集四部分，部下有类，类下有属。全书共4部44类66属。
    /**
     * set
     * category
     * book
     * section
     * 经部：易类 书类 诗类 礼类 春秋类 孝经类 四书类 五经类 乐类 小学类/蒙学类
     * 史部：正史类 编年类 纪事本末类 杂史类 别史类 诏令奏议类 传记类 史钞类 展开
     * 子部：儒家类 兵家类 法家类 农家类 医家类 天文算法类 术数类 艺术类 谱录类 展开
     * 集部：楚辞类 别集类 总集类 诗文评类 词曲类
     */

    private String ancientSet;

    private Integer count;

    private Integer weight;

    private String intro;

    public String getAncientSet() {
        return ancientSet;
    }

    public void setAncientSet(String ancientSet) {
        this.ancientSet = ancientSet;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}