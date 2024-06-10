package com.ricka.princy.fjpa.reflect;

import com.ricka.princy.fjpa.annotations.Column;
import com.ricka.princy.fjpa.annotations.Entity;
import com.ricka.princy.fjpa.annotations.Id;
import com.ricka.princy.fjpa.exceptions.FJPAException;
import com.ricka.princy.fjpa.types.Attribute;
import com.ricka.princy.fjpa.utils.Utils;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReflectedEntity<T> {
    @Getter
    private final String tableName;
    @Getter
    private final Attribute idAttribute;
    @Getter
    private final List<Attribute> attributes;
    private final Class<T> clazz;

    public ReflectedEntity(Class<T> clazz) {
        this.clazz = clazz;
        this.tableName = ReflectedEntity.getTableName(this.clazz);
        this.attributes = ReflectedEntity.getAttributes(this.clazz);
        this.idAttribute = attributes.stream()
                .filter(Attribute::isId)
                .findFirst().orElseThrow(()->new FJPAException("@Id is required for one field"));
    }

    public T createInstance(Map<Attribute, Object> argsValues) {
        var noArgsConstructor = Arrays.stream((Constructor<T>[]) clazz.getDeclaredConstructors())
                .filter(ctr-> ctr.getParameterCount() == 0)
                .findFirst().orElseThrow(()-> new FJPAException("Entity must have noArgsConstructor"));
        try{
            T instance = noArgsConstructor.newInstance();
            return ReflectedEntity.invokeSetters(instance, argsValues);
        }catch(InvocationTargetException | InstantiationException | IllegalAccessException error){
            throw new FJPAException("Instantiation error for " + this.tableName);
        }
    }

    private static <T> Object invokeGetters(T instance, Attribute attribute) {
        try {
            return attribute.getter().invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException error) {
            throw new FJPAException(error.getMessage());
        }
    }

    private static <T> T invokeSetters(T instance, Map<Attribute, Object> argsValues) {
        argsValues.forEach((attribute, value) -> {
            try {
                attribute.setter().invoke(instance, value);
            } catch (IllegalAccessException | InvocationTargetException error) {
                throw new FJPAException(error.getMessage());
            }
        });
        return instance;
    }

    private static String getTableName(Class<?> clazz){
        if(!clazz.isAnnotationPresent(Entity.class)){
            throw new FJPAException("Class must be annotated with @Entity to be used with fjpa");
        }
        var entity = clazz.getAnnotation(Entity.class);
        return entity.name().isEmpty() ? clazz.getSimpleName().toLowerCase() : entity.name();
    }

    private static List<Attribute> getAttributes(Class<?> clazz){
        var fields = Arrays
                .stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .toList();

        return fields.stream().map(field -> {
            var setterName = String.format("set%s", Utils.toCamelCase(field.getName()));
            var getterName = String.format("get%s", Utils.toCamelCase(field.getName()));

            var setter = Arrays.stream(clazz.getMethods())
                    .filter(method -> method.getName().equals(setterName))
                    .findFirst()
                    .orElseThrow(() -> new FJPAException("Setter method not found for field: " + field.getName()));
            var getter = Arrays.stream(clazz.getMethods())
                    .filter(method -> method.getName().equals(getterName))
                    .findFirst()
                    .orElseThrow(() -> new FJPAException("Getter method not found for field: " + field.getName()));

            return Attribute
                    .builder()
                        .name(field.getName())
                        .isId(field.isAnnotationPresent(Id.class))
                        .setter(setter)
                        .getter(getter)
                    .build();
        }).toList();
    }
}