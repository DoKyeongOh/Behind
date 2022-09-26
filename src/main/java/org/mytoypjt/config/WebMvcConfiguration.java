package org.mytoypjt.config;

import org.mytoypjt.interceptor.login.LoginCheckInterceptor;
import org.mytoypjt.interceptor.login.LoginNotRequireInterceptor;
import org.mytoypjt.interceptor.login.LoginRequireInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.mytoypjt"})
@Import({DBConfig.class})
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("*/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
        registry.addResourceHandler("*/icons/**").addResourceLocations("/icons/").setCachePeriod(31556926);
        registry.addResourceHandler("*/pictures/**").addResourceLocations("/pictures/").setCachePeriod(31556926);

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("test");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> loginRequireUrlPatterns = new ArrayList<>();
        loginRequireUrlPatterns.add("/register/page/{pageNo}");
        loginRequireUrlPatterns.add("/account");
        loginRequireUrlPatterns.add("/account/cert");
        loginRequireUrlPatterns.add("/profile");
        loginRequireUrlPatterns.add("/login/page");
        loginRequireUrlPatterns.add("/id/page");
        loginRequireUrlPatterns.add("/id/cert");
        loginRequireUrlPatterns.add("/pw/page/{pageNo}");
        loginRequireUrlPatterns.add("/pw/cert");
        loginRequireUrlPatterns.add("/pw");
        loginRequireUrlPatterns.add("/");

        registry.addInterceptor(new LoginNotRequireInterceptor())
                .addPathPatterns(loginRequireUrlPatterns);

        loginRequireUrlPatterns.add("/login");
        registry.addInterceptor(new LoginRequireInterceptor())
                .excludePathPatterns(loginRequireUrlPatterns);

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
