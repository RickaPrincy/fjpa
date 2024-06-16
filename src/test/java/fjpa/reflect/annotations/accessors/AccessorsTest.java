package fjpa.reflect.annotations.accessors;

import com.ricka.princy.fjpa.exceptions.FJPAException;
import com.ricka.princy.fjpa.reflect.annotations.ReflectAccessors;
import fjpa.reflect.annotations.accessors.model.Example;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessorsTest {

    @Test
    void should_throws_if_no_getter_or_setter(){
        assertThrows(FJPAException.class, ()->{
            ReflectAccessors.getGetter(Example.class, getExampleField("withoutGetterAndSetter"));
        });
        assertThrows(FJPAException.class, ()->{
            ReflectAccessors.getGetter(Example.class, getExampleField("withoutGetter"));
        });
        assertThrows(FJPAException.class, ()->{
            ReflectAccessors.getSetter(Example.class, getExampleField("withoutSetter"));
        });
    }

    @Test
    void should_return_default_getter_and_setter(){
        var getter = ReflectAccessors.getGetter(Example.class, getExampleField("id"));
        assertEquals("getId", getter.getName());

        var setter = ReflectAccessors.getSetter(Example.class, getExampleField("id"));
        assertEquals("setId", setter.getName());
    }

    @Test
    void should_return_custom_getter_and_setter(){
        var getter = ReflectAccessors.getGetter(Example.class, getExampleField("customField"));
        assertEquals("getCustom", getter.getName());

        var setter = ReflectAccessors.getSetter(Example.class, getExampleField("customField"));
        assertEquals("setCustom", setter.getName());
    }

    private static Field getExampleField(String name) {
        try {
            return Example.class.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
