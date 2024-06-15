package fjpa.reflect.reflect_id;

import com.ricka.princy.fjpa.exceptions.FJPAException;
import com.ricka.princy.fjpa.exceptions.MissingAnnotationException;
import com.ricka.princy.fjpa.reflect.ReflectId;
import fjpa.reflect.reflect_id.model.Dummy;
import fjpa.reflect.reflect_id.model.MultipleId;
import fjpa.reflect.reflect_id.model.WithoutId;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IdTest {
    private static <T> List<Field> getFields(Class<T> clazz){
        return Arrays.stream(clazz.getDeclaredFields()).toList();
    }

    @Test
    void should_throws_error_if_no_id_is_found(){
        assertThrows(MissingAnnotationException.class, ()->{
            ReflectId.getId(getFields(WithoutId.class));
        });
    }

    @Test
    void should_throws_if_multiple_id_is_found(){
        assertThrows(FJPAException.class, ()->{
            ReflectId.getId(getFields(MultipleId.class));
        });
    }

    void should_return_the_correct_field_with_id(){
        var field = ReflectId.getId(getFields(Dummy.class));
        assertEquals("id", field.getName());
    }
}