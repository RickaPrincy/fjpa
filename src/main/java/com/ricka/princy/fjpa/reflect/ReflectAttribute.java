package com.ricka.princy.fjpa.reflect;

import com.ricka.princy.fjpa.reflect.annotations.ReflectAccessors;
import com.ricka.princy.fjpa.reflect.annotations.ReflectColumn;
import com.ricka.princy.fjpa.reflect.annotations.ReflectId;
import com.ricka.princy.fjpa.types.Attribute;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectAttribute {
    public static <T> List<Attribute> getAttributes(Class<T> clazz){
        return ReflectColumn.getColumnFields(clazz)
                .stream()
                .map(field -> getAttributeData(clazz, field))
                .toList();
    }

    private static <T> Attribute getAttributeData(Class<T> clazz, Field field){
        return Attribute
                .builder()
                    .name(field.getName())
                    .isId(ReflectId.isId(field))
                    .getter(ReflectAccessors.getGetter(clazz, field))
                    .setter(ReflectAccessors.getSetter(clazz, field))
                    .sqlMetaData(ReflectColumn.getColumnSqlMetaData(clazz, field))
                .build();
    }
}