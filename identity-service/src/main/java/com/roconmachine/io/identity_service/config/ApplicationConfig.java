package com.roconmachine.io.identity_service.config;

import com.roconmachine.io.identity_service.noti.api.NotificationApi;
import com.roconmachine.io.identity_service.noti.client.ApiClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Value("${notification.url}")
    private String notificationUrl;
    @Bean
    public NotificationApi getNotificationApi(){
        ApiClient client = new ApiClient();
        client.setBasePath(notificationUrl);
        return new NotificationApi(client);
    }



}
