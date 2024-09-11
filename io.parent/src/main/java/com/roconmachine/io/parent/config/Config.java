package com.roconmachine.io.parent.config;

import com.roconmachine.io.parent.gexc.GlobalExceptionHandler;
import com.roconmachine.io.parent.glog.LoggingService;
import com.roconmachine.io.parent.glog.LoggingServiceImpl;
import com.roconmachine.io.parent.glog.RequestBodyInterceptor;
import com.roconmachine.io.parent.glog.ResponseBodyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public GlobalExceptionHandler getExceptionHandler()
    {
        return new GlobalExceptionHandler();
    }

    @Bean
    public LoggingService getLoggingService(){
        return new LoggingServiceImpl();
    }
    @Bean
    public ResponseBodyInterceptor getResponseBodyInterceptor(){
        return new ResponseBodyInterceptor();
    }

    @Bean
    public RequestBodyInterceptor getRequestBodyInterceptor(){
        return new RequestBodyInterceptor();
    }
}
