package fjpa.reflect.reflect_entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ricka.princy.fjpa.exceptions.MissingAnnotationException;
import com.ricka.princy.fjpa.reflect.ReflectEntity;
import fjpa.reflect.model.Dummy;
import fjpa.reflect.reflect_entity.model.Example;
import fjpa.reflect.reflect_entity.model.NotEntity;
import org.junit.jupiter.api.Test;

public class EntityTest {
    @Test
    void should_know_default_table_name(){
        assertEquals("dummy", ReflectEntity.getTableName(Dummy.class));
    }

    @Test
    void should_know_entity_name_value(){
        assertEquals("example_table", ReflectEntity.getTableName(Example.class));
    }

    @Test
    void should_throw_error_when_class_is_not_entity(){
        assertThrows(MissingAnnotationException.class, () -> {
            ReflectEntity.getTableName(NotEntity.class);
        });
    }
}