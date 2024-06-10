package fjpa.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ricka.princy.fjpa.exceptions.FJPAException;
import com.ricka.princy.fjpa.reflect.ReflectedEntity;
import fjpa.reflect.model.Dummy;
import fjpa.reflect.model.Example;
import fjpa.reflect.model.WithoutGetter;
import fjpa.reflect.model.WithoutSetter;
import org.junit.jupiter.api.Test;

public class AttributeTest {
    @Test
    void should_get_all_attributes(){
        var dummyReflectedEntity = new ReflectedEntity<>(Dummy.class);
        assertEquals(3, dummyReflectedEntity.getAttributes().size());

        var exampleReflectedEntity = new ReflectedEntity<>(Example.class);
        assertEquals(1, exampleReflectedEntity.getAttributes().size());
    }

    @Test
    void should_throw_if_no_getter(){
        assertThrows(FJPAException.class, ()->{
            new ReflectedEntity<>(WithoutGetter.class);
        });
    }

    @Test
    void should_throw_if_no_setter(){
        assertThrows(FJPAException.class, ()->{
            new ReflectedEntity<>(WithoutSetter.class);
        });
    }
}