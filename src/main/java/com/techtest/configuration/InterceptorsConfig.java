package com.techtest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.techtest.interceptor.ServiceInterceptor;

@Configuration
public class InterceptorsConfig extends WebMvcConfigurationSupport {
	
	@Autowired
    private ServiceInterceptor serviceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(serviceInterceptor)
		        .addPathPatterns("/**")
		        .excludePathPatterns("/auth");
        super.addInterceptors(registry);
    }
    
}