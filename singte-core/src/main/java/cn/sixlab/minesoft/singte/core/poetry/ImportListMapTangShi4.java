package cn.sixlab.minesoft.singte.core.poetry;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("importlistmaptangshi4")
public class ImportListMapTangShi4 extends ImportListMap {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list = new ArrayList<>();

        for (int i = 401; i <= 500; i++) {
            String path = "quan_tang_shi/json/" + String.format("%03d", i) + ".json";
            list.add(new PoetryModel(path, "经部", "诗类", "全唐诗", "合著"));
        }

        return list;
    }

}
