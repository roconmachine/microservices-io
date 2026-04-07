package com.roconmachine.io.fgaccess.converters;

import org.springframework.beans.BeanUtils;

public interface BaseConverter<E, M> {

    public  E toEntity(M model);
    public M toModel(E entity);
}
