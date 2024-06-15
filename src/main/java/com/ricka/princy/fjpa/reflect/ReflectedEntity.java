package com.ricka.princy.fjpa.reflect;

import com.ricka.princy.fjpa.reflect.annotations.ReflectId;
import com.ricka.princy.fjpa.types.Attribute;
import com.ricka.princy.fjpa.types.Model;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
public class ReflectedEntity<T>{
    private final List<Attribute<T>> attributes;
    private final Model<T> model;

    public ReflectedEntity(Class<T> clazz) {
        ReflectId.validateId(clazz);
        this.attributes = ReflectAttribute.getAttributes(clazz);
        var id = this.attributes.stream().filter(Attribute::isId).findFirst().orElseThrow(RuntimeException::new);
        this.model = ReflectModel.getModel(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public T createInstance(Map<Attribute<T>, Object> argsValues) {
        Constructor<T> noArgsConstructor = Arrays.stream((Constructor<T>[]) this.model.clazz().getDeclaredConstructors())
                .filter(ctr -> ctr.getParameterCount() == 0)
                .findFirst().orElseThrow(()-> new RuntimeException("Entity must have noArgsConstructor"));
        try{
            T instance = noArgsConstructor.newInstance();
            argsValues.forEach((attribute, value) -> {
                attribute.invokeSetter(instance, value);
            });
            return instance;
        }catch(InvocationTargetException | InstantiationException | IllegalAccessException error){
            throw new RuntimeException("Instantiation error for " + this.model.name());
        }
    }
}