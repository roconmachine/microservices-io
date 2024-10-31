package com.roconmachine.io.fgaccess.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyDto {
    private Long id;
    private String name;
    private EffectEnum effect;


    public enum EffectEnum {
        ALLOW,
        DENY;
    }
}



