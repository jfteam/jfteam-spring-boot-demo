package org.jfteam.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-28
 * Time: 下午10:35
 */
@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfiguration.class);

    @Autowired
    private HandlerInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.securityInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 添加新的HttpMessageConvert
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().forEach(httpMessageConverter -> LOGGER.info("HttpMessageConvert : {}", httpMessageConverter.getClass().getName()));
        super.configureMessageConverters(converters);
    }

    /**
     * 修改默认的HttpMessageConvert
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().forEach(httpMessageConverter -> {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                List<MediaType> jsonMediaType = new ArrayList<>();
                jsonMediaType.add(MediaType.APPLICATION_JSON_UTF8);
                ((StringHttpMessageConverter) httpMessageConverter).setSupportedMediaTypes(jsonMediaType);
            }
        });
        super.extendMessageConverters(converters);
    }
}
