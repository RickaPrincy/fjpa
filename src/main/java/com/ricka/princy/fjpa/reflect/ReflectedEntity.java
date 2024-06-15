package com.ricka.princy.fjpa.reflect;

import com.ricka.princy.fjpa.reflect.annotations.ReflectId;
import com.ricka.princy.fjpa.types.Attribute;
import com.ricka.princy.fjpa.types.Model;
import lombok.Getter;

import java.util.List;

@Getter
public class ReflectedEntity<T>{
    private final Class<T> clazz;
    private final List<Attribute> attributes;
    private final Model model;

    public ReflectedEntity(Class<T> clazz) {
        ReflectId.validateId(clazz);
        this.clazz = clazz;
        this.attributes = ReflectAttribute.getAttributes(clazz);
        var id = this.attributes.stream().filter(Attribute::isId).findFirst().orElseThrow(RuntimeException::new);
        this.model = ReflectModel.getModel(clazz, id);
    }
}