package cn.sixlab.minesoft.singte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.*;

@Configuration
public class WebConfig {

    @Bean
    public SimpleUrlHandlerMapping customFaviconHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        Map<String, ResourceHttpRequestHandler> map = Collections.singletonMap("/favicon.ico", faviconRequestHandler());
        mapping.setUrlMap(map);
        return mapping;
    }

    @Bean
    protected ResourceHttpRequestHandler faviconRequestHandler() {
        ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
        List<Resource> locations = Arrays.asList(new ClassPathResource("/static/"));
        requestHandler.setLocations(locations);
        return requestHandler;
    }

}
