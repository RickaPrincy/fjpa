package com.ricka.princy.fjpa.reflect;

import com.ricka.princy.fjpa.reflect.annotations.ReflectEntity;
import com.ricka.princy.fjpa.types.Attribute;
import com.ricka.princy.fjpa.types.Model;

public class ReflectModel {
    public static <T> Model<T> getModel(Class<T> clazz, Attribute<T> id){
        return new Model<>(
            clazz,
            clazz.getSimpleName(),
            id,
            ReflectEntity.getTableMetaData(clazz)
        );
    }
}