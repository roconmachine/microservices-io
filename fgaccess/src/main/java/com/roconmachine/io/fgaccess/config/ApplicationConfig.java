package com.roconmachine.io.fgaccess.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mm = new ModelMapper();
//        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD)
//                .setFieldMatchingEnabled(true)
//                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
//                .setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE)
//                ;


        return mm;
    }

}
