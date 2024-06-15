package com.ricka.princy.fjpa.reflect;

import com.ricka.princy.fjpa.annotations.Entity;
import com.ricka.princy.fjpa.exceptions.MissingAnnotationException;

public class ReflectEntity {
    public static <T> String getTableName(Class<T> clazz){
        if(!clazz.isAnnotationPresent(Entity.class)){
            throw new MissingAnnotationException(Entity.class);
        }
        var entity = clazz.getAnnotation(Entity.class);
        return entity.name().isEmpty() ? clazz.getSimpleName().toLowerCase() : entity.name();
    }
}