package com.ricka.princy.fjpa.types;

import com.ricka.princy.fjpa.exceptions.FJPAException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public record Attribute<T>(
    Class<?> clazz,
    String name,
    boolean isId,
    Method getter,
    Method setter,
    ColumnSqlMetaData sqlMetaData
) {
    public void invokeSetter(T instance, Object ...values){
        try {
            this.setter.invoke(instance, values);
        } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            throw new FJPAException("TODO");
        }
    }

    public Object invokeGetter(T instance){
        try {
            return this.getter.invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new FJPAException("TODO");
        }
    }
}