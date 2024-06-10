package fjpa.reflect;

import com.ricka.princy.fjpa.reflect.ReflectedEntity;
import com.ricka.princy.fjpa.types.Attribute;
import fjpa.reflect.model.Dummy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.HashMap;

public class CreateInstanceTest {
    @Test
    void should_be_able_to_create_instance_with_create_instance(){
        var reflectedEntity = new ReflectedEntity<>(Dummy.class);
        var argValues = new HashMap<Attribute, Object>();
        var instantNow = Instant.now();

        reflectedEntity.getAttributes().forEach(attribute -> {
            switch (attribute.name()){
                case "id":
                    argValues.put(attribute, "idValue");
                    break;
                case "name":
                    argValues.put(attribute, "nameValue");
                    break;
                case "createdAt":
                    argValues.put(attribute, instantNow);
                    break;
                default:
                    throw new RuntimeException("Unknown attribute");
            }
        });

        var newInstance = reflectedEntity.createInstance(argValues);
        assertEquals(newInstance.getId(), "idValue");
        assertEquals(newInstance.getName(), "nameValue");
        assertEquals(newInstance.getCreatedAt(), instantNow);
    }
}
