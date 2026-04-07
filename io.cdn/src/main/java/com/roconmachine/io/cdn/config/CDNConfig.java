package com.roconmachine.io.cdn.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CDNConfig {

    @Value("$cdn.location")
    public String location;
}
