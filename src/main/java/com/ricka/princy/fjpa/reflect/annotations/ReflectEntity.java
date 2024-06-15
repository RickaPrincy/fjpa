package com.ricka.princy.fjpa.reflect.annotations;

import com.ricka.princy.fjpa.annotations.Entity;
import com.ricka.princy.fjpa.exceptions.MissingAnnotationException;
import com.ricka.princy.fjpa.types.SqlMetaData;

public class ReflectEntity {
    public static <T> SqlMetaData getTableMetaData(Class<T> clazz){
        if(!clazz.isAnnotationPresent(Entity.class)){
            throw new MissingAnnotationException(Entity.class);
        }
        var entity = clazz.getAnnotation(Entity.class);
        var tableName =  entity.name().isEmpty() ? clazz.getSimpleName().toLowerCase() : entity.name();
        return new SqlMetaData(tableName);
    }
}