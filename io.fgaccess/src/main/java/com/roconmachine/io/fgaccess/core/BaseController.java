package com.roconmachine.io.fgaccess.core;

import com.roconmachine.io.dataframe.fgaccess.models.Role;
import com.roconmachine.io.dataframe.fgaccess.models.UserRole;
import com.roconmachine.io.fgaccess.converters.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public abstract class BaseController<E, S extends IService<E>> {

    public Mono<ResponseEntity<E>> deleteEntity(Long aLong, S service){
        return service.delete(aLong)
                .flatMap(
                        aBoolean -> {
                            if (aBoolean) return Mono.just(ResponseEntity.status(HttpStatus.OK).build());
                            else return Mono.just(ResponseEntity.notFound().build());
                        }
                );
    }
}
