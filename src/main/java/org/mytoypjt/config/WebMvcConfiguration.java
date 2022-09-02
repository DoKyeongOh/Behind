package org.mytoypjt.config;

import org.mytoypjt.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.mytoypjt"})
@Import({DBConfig.class})
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
        registry.addResourceHandler("/icons/**").addResourceLocations("/icons/").setCachePeriod(31556926);
        registry.addResourceHandler("/pictures/**").addResourceLocations("/pictures/").setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("test 성공");
        registry.addViewController("/test").setViewName("test");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .excludePathPatterns("/register/page/{pageNo}")
                .excludePathPatterns("/account")
                .excludePathPatterns("/account/cert")
                .excludePathPatterns("/profile")
                .excludePathPatterns("/login/page")
                .excludePathPatterns("/login")
                .excludePathPatterns("/id/page")
                .excludePathPatterns("/id/cert")
                .excludePathPatterns("/pw/page/{pageNo}")
                .excludePathPatterns("/pw/cert")
                .excludePathPatterns("/pw")
                .excludePathPatterns("/");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.extendMessageConverters(converters);
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


}
