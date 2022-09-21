package cn.sixlab.minesoft.singte.core.service;

import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
public class AdminServerService {
    private static boolean newServer = true;

    @Autowired
    private ConfigUtils configUtils;

    public boolean isNewServer(){
        return newServer;
    }

    public ModelResp runShell(String file) {
        newServer = false;
        String path = configUtils.getConfig(StConst.SERVER_SH_PATH);

        Process exec;
        try {
            String[] cmd = {"/bin/sh", "-c", path + file};
            exec = Runtime.getRuntime().exec(cmd, null, new File(path));

            InputStreamReader streamReader = new InputStreamReader(exec.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            String line;

            log.info("命令行输出：\n");
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }

            streamReader = new InputStreamReader(exec.getErrorStream());
            reader = new BufferedReader(streamReader);
            log.error("命令行异常输出：\n");
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }

            exec.waitFor();
        } catch (IOException e) {
            log.error("脚本运行异常", e);
            return ModelResp.error(StErr.UNKNOWN, "脚本运行异常");
        } catch (InterruptedException e) {
            log.error("脚本运行中断", e);
            return ModelResp.error(StErr.UNKNOWN, "脚本运行中断");
        }

        return ModelResp.success();
    }
}
