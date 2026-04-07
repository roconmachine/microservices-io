package com.pai.app.state.core;

public interface Convertable <E, M>{
    public  E toEntity(M model);

    public M toModel(E entity);
}
