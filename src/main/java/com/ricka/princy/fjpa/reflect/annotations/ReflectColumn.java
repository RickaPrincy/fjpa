package com.ricka.princy.fjpa.reflect.annotations;

import com.ricka.princy.fjpa.annotations.Column;
import com.ricka.princy.fjpa.exceptions.MissingAnnotationException;
import com.ricka.princy.fjpa.types.ColumnSqlMetaData;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ReflectColumn {
    public static <T> List<Field> getColumnFields(Class<T> clazz){
        return Arrays
                .stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .toList();
    }

    public static <T> ColumnSqlMetaData getColumnSqlMetaData(Class<T> clazz, Field field){
        if(field.isAnnotationPresent(Column.class)){
            throw new MissingAnnotationException(Column.class);
        }

        var column = field.getAnnotation(Column.class);
        var columnName = column.name().isEmpty() ? field.getName().toLowerCase() : column.name();
        return new ColumnSqlMetaData(column.required(), columnName);
    }
}
