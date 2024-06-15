package fjpa.reflect.annotations.reflect_entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ricka.princy.fjpa.exceptions.MissingAnnotationException;
import com.ricka.princy.fjpa.reflect.annotations.ReflectEntity;
import fjpa.reflect.annotations.reflect_entity.model.Example;
import fjpa.reflect.annotations.reflect_entity.model.NotEntity;
import fjpa.reflect.model.Dummy;
import org.junit.jupiter.api.Test;

public class EntityTest {
    @Test
    void should_know_default_table_name(){
        assertEquals("dummy", ReflectEntity.getTableMetaData(Dummy.class).getName());
    }

    @Test
    void should_know_entity_name_value(){
        assertEquals("example_table", ReflectEntity.getTableMetaData(Example.class).getName());
    }

    @Test
    void should_throw_error_when_class_is_not_entity(){
        assertThrows(MissingAnnotationException.class, () -> {
            ReflectEntity.getTableMetaData(NotEntity.class);
        });
    }
}