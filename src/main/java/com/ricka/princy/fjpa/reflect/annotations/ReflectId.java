package com.ricka.princy.fjpa.reflect.annotations;

import com.ricka.princy.fjpa.annotations.Id;
import com.ricka.princy.fjpa.exceptions.FJPAException;
import com.ricka.princy.fjpa.exceptions.MissingAnnotationException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ReflectId {
    public static boolean isId(Field field){
        return field.isAnnotationPresent(Id.class);
    }

    public static <T> void validateId(Class<T> clazz){
        var fields = clazz.getDeclaredFields();
        final var ids = Arrays.stream(fields).toList().stream().filter(ReflectId::isId).toList();

        if(ids.isEmpty()){
            throw new MissingAnnotationException(Id.class);
        }
        if(ids.size() > 1){
            throw new FJPAException("Multiple Ids is not allowed");
        }
    }
}