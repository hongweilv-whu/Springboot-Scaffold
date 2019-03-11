package com.whu.sres.lhw.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import com.whu.sres.lhw.filter.UserFilter;
import com.whu.sres.lhw.interceptor.MyInterceptor;
import com.whu.sres.lhw.listener.MyServletContextListener;
import com.whu.sres.lhw.servlet.MyCustomServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by lvhw on 2019/3/7 0:16.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private MyInterceptor myInterceptor;

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 格式化展示
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        // 中文乱码解决方案
        List<MediaType> mediaTypes = new ArrayList<>();
        //设定json格式且编码为UTF-8
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);

        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }

    @Bean
    /**
     * 注册自定义servlet
     */
    public ServletRegistrationBean servletRegistrationBean() {

        return new ServletRegistrationBean(new MyCustomServlet(), "/servlet/myServlet");
    }

    @Bean
    /**
     * 注册自定义过滤器，此种方式doFilter方法会执行两次
     */
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(new UserFilter());

        List<String> urlPatters = Lists.newArrayList();
        urlPatters.add("/*");

        frb.setUrlPatterns(urlPatters);
        //frb.setEnabled(false);
        //frb.setDispatcherTypes(DispatcherType.REQUEST);
        return frb;
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {

        return new ServletListenerRegistrationBean(new MyServletContextListener());
    }

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
    }*/

    @Override
    // 服务端解决CORS跨域问题
    // 改方式是粗粒度的控制；更细力度的方式是在各个Controller上加上@CrossOrigin注解
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*");
    }
}
