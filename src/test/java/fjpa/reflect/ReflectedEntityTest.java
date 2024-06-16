package fjpa.reflect;

import com.ricka.princy.fjpa.exceptions.FJPAException;
import com.ricka.princy.fjpa.reflect.ReflectAttribute;
import com.ricka.princy.fjpa.reflect.ReflectedEntity;
import com.ricka.princy.fjpa.types.Attribute;
import com.ricka.princy.fjpa.types.Model;
import com.ricka.princy.fjpa.types.SqlMetaData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fjpa.reflect.model.Dummy;
import fjpa.reflect.model.Example;
import fjpa.reflect.model.WithoutNoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;

public class ReflectedEntityTest {
    @Test
    void should_return_correct_data_on_dummy_model(){
        var reflectEntity = new ReflectedEntity<>(Dummy.class);
        assertEquals(2, reflectEntity.getAttributes().size());

        var expectedModel = new Model<>(
            Dummy.class,
            "Dummy",
            getIdAttribute(Dummy.class),
            new SqlMetaData("dummy")
        );
        assertEquals(expectedModel, reflectEntity.getModel());
    }

    @Test
    void should_return_correct_data_on_example_model(){
        var reflectEntity = new ReflectedEntity<>(Example.class);
        assertEquals(2, reflectEntity.getAttributes().size());

        var expectedModel = new Model<>(
            Example.class,
            "Example",
            getIdAttribute(Example.class),
            new SqlMetaData("example_table")
        );
        assertEquals(expectedModel, reflectEntity.getModel());
    }

    @Test
    void should_throws_if_no_args_constructor_on_created_instance(){
        assertThrows(FJPAException.class, ()-> new ReflectedEntity<>(WithoutNoArgsConstructor.class));
    }

    @Test
    void can_create_instance_on_dummy(){
        var reflectedDummy = new ReflectedEntity<>(Dummy.class);
        var argsValue = new HashMap<Attribute<Dummy>, Object>();
        reflectedDummy.getAttributes().forEach(atr -> {
            if (atr.name().equals("id")) {
                argsValue.put(atr, "idValue");
            } else {
                argsValue.put(atr, "nameValue");
            }
        });

        var createdInstance = reflectedDummy.createInstance(argsValue);
        assertEquals(new Dummy("idValue", "nameValue"), createdInstance);
    }

    @Test
    void can_create_instance_on_example(){
        var instantNow = Instant.now();
        var reflectedDummy = new ReflectedEntity<>(Example.class);
        var argsValue = new HashMap<Attribute<Example>, Object>();
        reflectedDummy.getAttributes().forEach(atr -> {
            if (atr.name().equals("id")) {
                argsValue.put(atr, "idValue");
            } else {
                argsValue.put(atr, instantNow);
            }
        });

        var createdInstance = reflectedDummy.createInstance(argsValue);
        assertEquals(new Example("idValue", instantNow, null), createdInstance);
    }
    static <T> Attribute<T> getIdAttribute(Class<T> clazz){
        return ReflectAttribute.getAttributes(clazz).getFirst();
    }
}