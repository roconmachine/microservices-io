package com.roconmachine.io.fgaccess.converter;

import com.roconmachine.io.dataframe.access.models.Properties;
import com.roconmachine.io.dataframe.access.models.Resource;
import com.roconmachine.io.fgaccess.entity.PropertiesEntity;
import com.roconmachine.io.fgaccess.entity.ResourceEntity;

import java.util.ArrayList;
import java.util.List;

public class ResourceConverter {

    public static ResourceEntity convert(Resource resource){
        ResourceEntity re= ResourceEntity.builder()
                .id(resource.getId())
                .name(resource.getName())
                .source(resource.getSource())
                .type(resource.getType().getValue()).owner(resource.getOwner()).build();
        if(resource.getProperties() != null && !resource.getProperties().isEmpty()){
            List<PropertiesEntity> propertiesEntityList = new ArrayList<>(0);
            for (Properties properties : resource.getProperties()){
                propertiesEntityList.add(convert(properties));
            }
            re.setProperties(propertiesEntityList);
        }
        return re;
    }

    public static Resource convert(ResourceEntity resource){
        Resource re= Resource.builder()
                .id(resource.getId())
                .name(resource.getName())
                .source(resource.getSource())
                .type(resource.getType() != null ? Resource.TypeEnum.fromValue(resource.getType()) : null)
                .owner(resource.getOwner())
                .build();
        if(resource.getProperties() != null && !resource.getProperties().isEmpty()){
            List<Properties> propertiesEntityList = new ArrayList<>(0);
            for (PropertiesEntity properties : resource.getProperties()){
                propertiesEntityList.add(convert(properties));
            }
            re.setProperties(propertiesEntityList);
        }
        return re;
    }

    public static PropertiesEntity convert(Properties properties){

        PropertiesEntity entity = new PropertiesEntity();
        entity.setUuid(properties.getUuid());
        entity.setKey(properties.getKey());
        entity.setValue_string(properties.getValueString());
        if(properties.getValueNumber() != null)  entity.setValue_number(properties.getValueNumber());
        if(properties.getValueBoolean() != null)  entity.setValue_boolean(properties.getValueBoolean());
        return entity;
    }

    public static Properties convert(PropertiesEntity entity){
        Properties properties = new Properties();
        properties.setId(entity.getId());
        properties.setKey(entity.getKey());
        properties.setUuid(entity.getUuid());
        properties.setValueBoolean(entity.isValue_boolean());
        properties.setValueNumber(entity.getValue_number());
        properties.setValueString(entity.getValue_string());
        return properties;
    }
}
