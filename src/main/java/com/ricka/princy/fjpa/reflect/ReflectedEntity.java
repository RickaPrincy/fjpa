package com.ricka.princy.fjpa.reflect;

import com.ricka.princy.fjpa.annotations.Entity;
import com.ricka.princy.fjpa.exceptions.FJPAException;
import lombok.Getter;

public class ReflectedEntity<T> {
    private final Class<T> clazz;
    @Getter
    private final String tableName;

    public ReflectedEntity(Class<T> clazz) {
        this.clazz = clazz;
        this.tableName = ReflectedEntity.getTableName(this.clazz);
    }

    private static String getTableName(Class<?> clazz){
        if(!clazz.isAnnotationPresent(Entity.class)){
            throw new FJPAException("Class must be annotated with @Entity to be used with fjpa");
        }
        Entity entity = clazz.getAnnotation(Entity.class);
        return entity.name().isEmpty() ? clazz.getSimpleName().toLowerCase() : entity.name();
    }
}