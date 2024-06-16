package fjpa.reflect;

import com.ricka.princy.fjpa.reflect.ReflectAttribute;
import com.ricka.princy.fjpa.reflect.ReflectModel;
import com.ricka.princy.fjpa.types.Attribute;
import com.ricka.princy.fjpa.types.SqlMetaData;
import fjpa.reflect.model.Dummy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fjpa.reflect.model.Example;
import org.junit.jupiter.api.Test;

public class ReflectModelTest {
    @Test
    void should_get_correct_data_on_dummy_model(){
        var model = ReflectModel.getModel(Dummy.class, getIdAttribute(Dummy.class));
        assertEquals(Dummy.class, model.clazz());
        assertEquals("Dummy", model.name());
        assertEquals("id", model.id().name());
        assertEquals(new SqlMetaData("dummy"), model.sqlMetaData());
    }

    @Test
    void should_get_correct_data_on_example_model(){
        var model = ReflectModel.getModel(Example.class, getIdAttribute(Example.class));
        assertEquals(Example.class, model.clazz());
        assertEquals("Example", model.name());
        assertEquals("id", model.id().name());
        assertEquals(new SqlMetaData("example_table"), model.sqlMetaData());
    }

    static <T> Attribute<T> getIdAttribute(Class<T> clazz){
        return ReflectAttribute.getAttributes(clazz).getFirst();
    }
}