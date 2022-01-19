package cn.sixlab.minesoft.singte.core.poetry;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("importlistmaptangshi7")
public class ImportListMapTangShi7 extends ImportListMap {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list = new ArrayList<>();

        for (int i = 701; i <= 800; i++) {
            String path = "quan_tang_shi/json/" + String.format("%03d", i) + ".json";
            list.add(new PoetryModel(path, "经部", "诗类", "全唐诗", "合著"));
        }

        return list;
    }

}
