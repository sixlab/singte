package cn.sixlab.minesoft.singte.core.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("st.db")
public class StProperties {
    private String jdbc;
    private String pwd;
    private Integer version;
}
