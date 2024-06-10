package fjpa.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ricka.princy.fjpa.exceptions.FJPAException;
import com.ricka.princy.fjpa.reflect.ReflectedEntity;
import fjpa.reflect.model.Dummy;
import fjpa.reflect.model.Example;
import fjpa.reflect.model.NotEntity;
import org.junit.jupiter.api.Test;

public class EntityTest {
    @Test
    void should_know_default_table_name(){
        var reflectedEntity = new ReflectedEntity<>(Dummy.class);
        assertEquals("dummy", reflectedEntity.getTableName());
    }

    @Test
    void should_know_entity_name_value(){
        var reflectedEntity = new ReflectedEntity<>(Example.class);
        assertEquals("example_table", reflectedEntity.getTableName());
    }

    @Test
    void should_throw_error_when_class_is_not_annoted_with_entity(){
        assertThrows(FJPAException.class, () -> {
            new ReflectedEntity<>(NotEntity.class);
        });
    }
}