package cn.sixlab.minesoft.singte.core.common.utils;

import cn.hutool.core.io.IoUtil;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class IoUtils {
    public static String readAsserts(String filename) {
        ClassPathResource classPathResource = new ClassPathResource(filename);
        try {
            InputStream inputStream = classPathResource.getInputStream();

            return IoUtil.read(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
