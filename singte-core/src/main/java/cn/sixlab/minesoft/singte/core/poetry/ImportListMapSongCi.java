package cn.sixlab.minesoft.singte.core.poetry;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("importlistmapsongci")
public class ImportListMapSongCi extends ImportListMap {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            String path = "ci/ci.song." + i + "000.json";
            list.add(new PoetryModel(path, "集部", "词曲类", "全宋词", "合著"));
        }

        return list;
    }

}
