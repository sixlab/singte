package cn.sixlab.minesoft.singte.core.poetry;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("importlistmapsongci")
public class ImportListMapSongCi extends ImportListMap {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list = new ArrayList<>();

        list.add(new PoetryModel("ci/宋词三百首.json", "集部", "词曲类", "宋词三百首", "合著"));
        list.add(new PoetryModel("ci/ci.song.2009y.json", "集部", "词曲类", "全宋词", "合著"));
        list.add(new PoetryModel("ci/ci.song.0.json", "集部", "词曲类", "全宋词", "合著"));

        for (int i = 1; i <= 21; i++) {
            String path = "ci/ci.song." + i + "000.json";
            list.add(new PoetryModel(path, "集部", "词曲类", "全宋词", "合著"));
        }

        return list;
    }

}
