package com.ricka.princy.fjpa.reflect.annotations;

import com.ricka.princy.fjpa.annotations.Accessors;
import com.ricka.princy.fjpa.exceptions.FJPAException;
import com.ricka.princy.fjpa.utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class ReflectAccessors {
    public static <T> Method getGetter(Class<T> clazz, Field field){
        if(field.isAnnotationPresent(Accessors.class)){
            final var getter = field.getAnnotation(Accessors.class).getter();
            return getMethod(clazz, getter)
                        .orElseThrow(() -> new FJPAException("Getter " + getter + " was not found"));
        }

        final var DEFAULT_GETTER = String.format("get%s", Utils.toCamelCase(field.getName()));
        return getMethod(clazz, DEFAULT_GETTER)
                    .orElseThrow(() -> new FJPAException("Getter method not found for field: " + field.getName()));
    }

    public static <T> Method getSetter(Class<T> clazz, Field field){
        if(field.isAnnotationPresent(Accessors.class)){
            final var setter = field.getAnnotation(Accessors.class).setter();
            return getMethod(clazz, setter)
                    .orElseThrow(() -> new FJPAException("Setter " + setter + " was not found"));
        }

        final var DEFAULT_SETTER = String.format("set%s", Utils.toCamelCase(field.getName()));
        return getMethod(clazz, DEFAULT_SETTER)
                .orElseThrow(() -> new FJPAException("Setter method not found for field: " + field.getName()));
    }

    private static Optional<Method> getMethod(Class<?> clazz, String methodName){
        return Arrays
                    .stream(clazz.getDeclaredMethods())
                    .filter(method -> method.getName().equals(methodName))
                    .findFirst();
    }
}