package com.roconmachine.io.fgaccess;

import com.roconmachine.io.dataframe.access.models.Properties;
import com.roconmachine.io.dataframe.access.models.Resource;
import com.roconmachine.io.fgaccess.entity.PropertiesEntity;
import com.roconmachine.io.fgaccess.entity.ResourceEntity;
import com.roconmachine.io.fgaccess.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FgaccessApplicationTests {


    private ModelMapper getMapper(){
        ModelMapper ob = new ModelMapper();
        ob.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ob.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return ob;
    };


    @Test
    public void dummy() {

        List<Properties> arrayList= new ArrayList<>();
        arrayList.add(Properties.builder().key("key").uuid("uuid").valueBoolean(false).build());
        arrayList.add(Properties.builder().key("key 3").uuid("uuid 32").valueString("string value").build());
        Resource resource = Resource.builder()
                .name("name")
                .owner("owner")
                .source("source")
                .properties(arrayList)
                .build();

        ResourceEntity entity = getMapper().map(resource, ResourceEntity.class);

    }

    @Autowired
    private PropertyService propertyService;

    @Test
    public void testSearchProperties(){
        propertyService.search("RES_27", null).log();
    }

    @Autowired
    private R2dbcEntityTemplate r2dbcTemplate;

    @Autowired
    private PropertyService propertiesService;
    @Test
    void testSearchWithUuidAndKey() {
        // Given
        String uuid = "RES_27";

        // When
        Flux<PropertiesEntity> result = propertiesService.search(uuid, null);
        // Then
        result.count()
                .doOnNext(count -> System.out.println("Searched count: " + count))  // Print count
                .as(StepVerifier::create)
                .expectNext(2L)  // Expecting count to be 2
                .verifyComplete();

    }

}
