package com.ricka.princy.fjpa.reflect;

import com.ricka.princy.fjpa.reflect.annotations.ReflectAccessors;
import com.ricka.princy.fjpa.reflect.annotations.ReflectColumn;
import com.ricka.princy.fjpa.reflect.annotations.ReflectId;
import com.ricka.princy.fjpa.types.Attribute;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectAttribute {
    public static <T> List<Attribute<T>> getAttributes(Class<T> clazz){
        return ReflectColumn.getColumnFields(clazz)
                .stream()
                .map(field -> getAttributeData(clazz, field))
                .toList();
    }

    private static <T> Attribute<T> getAttributeData(Class<T> clazz, Field field){
        return new Attribute<>(
            field.getType(),
            field.getName(),
            ReflectId.isId(field),
            ReflectAccessors.getGetter(clazz, field),
            ReflectAccessors.getSetter(clazz, field),
            ReflectColumn.getColumnSqlMetaData(field)
        );
    }
}