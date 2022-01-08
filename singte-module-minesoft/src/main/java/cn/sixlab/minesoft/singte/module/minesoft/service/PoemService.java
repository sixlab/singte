package cn.sixlab.minesoft.singte.module.minesoft.service;

import cn.sixlab.minesoft.singte.module.minesoft.dao.StePoemAtomDao;
import cn.sixlab.minesoft.singte.module.minesoft.dao.StePoemDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoem;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoemAtom;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PoemService {

    @Autowired
    private StePoemDao poemMapper;

    @Autowired
    private StePoemAtomDao poemAtomMapper;


    public void addPoem(StePoem poem) {
        poem.setCreateTime(new Date());
        poemMapper.save(poem);

        String[] atoms = split(poem.getPoemContent(), "[\\.\\!\\?\\r\\n\\s。！？]");
        if (ArrayUtils.isNotEmpty(atoms)) {
            for (int i = 0; i < atoms.length; i++) {
                String atomContent = atoms[i];

                if (StringUtils.isNotEmpty(atomContent)) {
                    StePoemAtom atom = new StePoemAtom();
                    atom.setPoemId(poem.getId());
                    atom.setPoemName(poem.getPoemName());
                    atom.setAtomContent(atomContent);
                    atom.setAtomOrder(i);
                    atom.setCreateTime(new Date());

                    poemAtomMapper.save(atom);
                }
            }
        }
    }

    public String[] split(String str, String regex) {
        String[] splits = str.split(regex);
        List<String> result = new ArrayList<>();
        for (String split : splits) {
            if (StringUtils.isNotEmpty(split)) {
                result.add(split);
            }
        }
        System.out.println("\n\n");
        System.out.println(result);
        System.out.println("\n\n");
        return result.toArray(new String[]{});
    }
}
