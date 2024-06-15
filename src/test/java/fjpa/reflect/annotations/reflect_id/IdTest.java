package fjpa.reflect.annotations.reflect_id;

import com.ricka.princy.fjpa.exceptions.FJPAException;
import com.ricka.princy.fjpa.exceptions.MissingAnnotationException;
import com.ricka.princy.fjpa.reflect.annotations.ReflectId;
import fjpa.reflect.annotations.reflect_id.model.Dummy;
import fjpa.reflect.annotations.reflect_id.model.MultipleId;
import fjpa.reflect.annotations.reflect_id.model.WithoutId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class IdTest {
    @Test
    void should_throws_error_if_no_id_is_found(){
        assertThrows(MissingAnnotationException.class, ()-> ReflectId.validateId(WithoutId.class));
    }

    @Test
    void should_throws_if_multiple_id_is_found(){
        assertThrows(FJPAException.class, ()-> ReflectId.validateId(MultipleId.class));
    }

    @Test
    void should_should_throw_if_one_id(){
        assertDoesNotThrow(()-> ReflectId.validateId(Dummy.class));
    }
}