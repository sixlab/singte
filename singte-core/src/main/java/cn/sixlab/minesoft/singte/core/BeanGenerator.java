package cn.sixlab.minesoft.singte.core;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BeanGenerator {
    public static void main(String[] args) throws Exception {
        String beanName = "StTest";

        String path = BeanGenerator.class.getClassLoader().getResource("").getPath();

        Path packagePath = new File(path).toPath();
        packagePath = packagePath.getParent().getParent().resolve("src/main/java/cn/sixlab/minesoft/singte/core");

        writeFile(packagePath, "models/StTask.java", beanName);
        writeFile(packagePath, "dao/StTaskDao.java", beanName);

        System.out.println("checkMenu(false, \"menu.task.list\", \"menu.system.manage\", \"far fa-circle\", \"/admin/table/StTask/list\", 800600, \"定时任务管理\");"
                .replace("StTask", beanName));
    }

    private static void writeFile(Path packagePath, String tplName, String beanName) throws Exception {
        Path tplPath = packagePath.resolve(tplName);
        Path target = packagePath.resolve(tplName.replace("StTask", beanName));
        if (Files.notExists(target)) {
            BufferedWriter writer = Files.newBufferedWriter(target, StandardCharsets.UTF_8);
            List<String> strings = Files.readAllLines(tplPath);
            for (String string : strings) {
                writer.write(string.replace("StTask", beanName));
                writer.write("\n");
            }
            writer.flush();
            writer.close();
        } else {
            System.out.println(target.toFile().getName() + " 无法生成，目标已存在");
        }
    }
}
