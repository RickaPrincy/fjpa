package fjpa.reflect;

import com.ricka.princy.fjpa.exceptions.FJPAException;
import com.ricka.princy.fjpa.reflect.ReflectedEntity;
import fjpa.reflect.model.Dummy;
import fjpa.reflect.model.WithoutId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class IdTest {
    @Test
    void should_throws_error_if_no_id_is_found(){
        assertThrows(FJPAException.class, ()->{
            new ReflectedEntity<>(WithoutId.class);
        });
    }

    @Test
    void should_not_throws_error_if_id_was_founded(){
        assertDoesNotThrow(()->{
            new ReflectedEntity<>(Dummy.class);
        });
    }
}