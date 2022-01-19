package cn.sixlab.minesoft.singte.core.poetry;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("importlistmapsongci1")
public class ImportListMapSongCi1 extends ImportListMap {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list = new ArrayList<>();

        for (int i = 11; i <= 21; i++) {
            String path = "ci/ci.song." + i + "000.json";
            list.add(new PoetryModel(path, "集部", "词曲类", "全宋词", "合著"));
        }

        return list;
    }

}
