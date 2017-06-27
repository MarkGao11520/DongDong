package com.zrkj.ecp.config;

import com.zrkj.ecp.converter.StringToDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/logout").setViewName("System/admin/login");
        registry.addViewController("/login").setViewName("System/admin/login");
    }

    @Bean
    public StringToDateConverter stringToDateConverter(){
        return new StringToDateConverter("yyyy-MM-dd");
    }
}
