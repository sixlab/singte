package cn.sixlab.minesoft.singte.core.poetry;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("importlistmaptangshi5")
public class ImportListMapTangShi5 extends ImportListMap {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list = new ArrayList<>();

        for (int i = 501; i <= 600; i++) {
            String path = "quan_tang_shi/json/" + String.format("%03d", i) + ".json";
            list.add(new PoetryModel(path, "经部", "诗类", "全唐诗", "合著"));
        }

        return list;
    }

}
