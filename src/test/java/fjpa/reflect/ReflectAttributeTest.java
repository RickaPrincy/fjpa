package fjpa.reflect;

import com.ricka.princy.fjpa.reflect.ReflectAttribute;

import com.ricka.princy.fjpa.types.ColumnSqlMetaData;
import fjpa.reflect.model.Dummy;
import fjpa.reflect.model.Example;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectAttributeTest {
    @Test
    void should_return_columns_as_attributes_with_correct_data_on_dummy_model(){
        var attributes = ReflectAttribute.getAttributes(Dummy.class);
        assertEquals(2, attributes.size());

        var idAttribute = attributes.getFirst();
        assertEquals(String.class, idAttribute.clazz());
        assertEquals("id", idAttribute.name());
        assertTrue(idAttribute.isId());
        assertEquals("getId", idAttribute.getter().getName());
        assertEquals("setId", idAttribute.setter().getName());
        assertEquals(new ColumnSqlMetaData("id", true), idAttribute.sqlMetaData());

        var nameAttribute = attributes.get(1);
        assertEquals(String.class, nameAttribute.clazz());
        assertEquals("name", nameAttribute.name());
        assertFalse(nameAttribute.isId());
        assertEquals("getName", nameAttribute.getter().getName());
        assertEquals("setName", nameAttribute.setter().getName());
        assertEquals(new ColumnSqlMetaData("name", true), nameAttribute.sqlMetaData());
    }

    @Test
    void should_return_columns_as_attributes_with_correct_data_on_example_model(){
        var attributes = ReflectAttribute.getAttributes(Example.class);
        assertEquals(2, attributes.size());

        var idAttribute = attributes.getFirst();
        assertEquals("id", idAttribute.name());
        assertTrue(idAttribute.isId());
        assertEquals("getId", idAttribute.getter().getName());
        assertEquals("setId", idAttribute.setter().getName());
        assertEquals(new ColumnSqlMetaData("id", true), idAttribute.sqlMetaData());

        var createdAtAttribute = attributes.get(1);
        assertEquals("createdAt", createdAtAttribute.name());
        assertFalse(createdAtAttribute.isId());
        assertEquals("getCreated", createdAtAttribute.getter().getName());
        assertEquals("setCreated", createdAtAttribute.setter().getName());
        assertEquals(new ColumnSqlMetaData("created_at", false), createdAtAttribute.sqlMetaData());
    }

    @Test
    void should_be_able_to_invoke_getter_and_setter_on_dummy(){
        var dummyInstance = new Dummy("1", "myDummy");
        var dummyAttributes = ReflectAttribute.getAttributes(Dummy.class);
        var idAttribute = dummyAttributes.getFirst();
        var nameAttribute = dummyAttributes.get(1);

        assertEquals(2, dummyAttributes.size());

        assertEquals("1", idAttribute.invokeGetter(dummyInstance));
        idAttribute.invokeSetter(dummyInstance, "2");
        assertEquals("2", idAttribute.invokeGetter(dummyInstance));

        assertEquals("myDummy", nameAttribute.invokeGetter(dummyInstance));
        nameAttribute.invokeSetter(dummyInstance, "myNewDummyName");
        assertEquals("myNewDummyName", nameAttribute.invokeGetter(dummyInstance));
    }

    @Test
    void should_be_able_to_invoke_getter_and_setter_on_example(){
        var instantNow = Instant.now();
        var exampleInstance = new Example("1", instantNow, BigDecimal.TEN);
        var exampleAttributes = ReflectAttribute.getAttributes(Example.class);
        var idAttribute = exampleAttributes.getFirst();
        var createdAtAttribute = exampleAttributes.get(1);

        assertEquals(2, exampleAttributes.size());

        assertEquals(String.class,idAttribute.clazz());
        assertEquals("1", idAttribute.invokeGetter(exampleInstance));
        idAttribute.invokeSetter(exampleInstance, "2");
        assertEquals("2", idAttribute.invokeGetter(exampleInstance));

        var instant2024 = Instant.parse("2024-01-01T01:01:01.00Z");
        assertEquals(Instant.class, createdAtAttribute.clazz());
        assertEquals(instantNow, createdAtAttribute.invokeGetter(exampleInstance));
        createdAtAttribute.invokeSetter(exampleInstance,instant2024);
        assertEquals(instant2024, createdAtAttribute.invokeGetter(exampleInstance));
    }
}