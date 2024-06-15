package com.ricka.princy.fjpa.reflect;

import com.ricka.princy.fjpa.reflect.annotations.ReflectEntity;
import com.ricka.princy.fjpa.types.Attribute;
import com.ricka.princy.fjpa.types.Model;

public class ReflectModel {
    public static <T> Model getModel(Class<T> clazz, Attribute id){
        return Model
                .builder()
                    .id(id)
                    .name(clazz.getSimpleName())
                    .sqlMetaData(ReflectEntity.getTableMetaData(clazz))
                .build();
    }
}