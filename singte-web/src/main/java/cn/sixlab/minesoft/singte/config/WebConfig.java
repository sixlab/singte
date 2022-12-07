package cn.sixlab.minesoft.singte.config;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

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

    @Bean
    public LocaleResolver localeResolver() {
        Locale.setDefault(StConst.DEFAULT_LOCALE);

        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("lang");
        localeResolver.setCookieMaxAge((int) StConst.SECONDS_YEAR_1);
       localeResolver.setDefaultLocale(StConst.DEFAULT_LOCALE);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        return localeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()).excludePathPatterns("/static/**", "/admin/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
