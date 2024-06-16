package fjpa.reflect.utils;

import com.ricka.princy.fjpa.utils.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {

    @Test
    void test_utils_toCamelCase() {
        assertEquals("Hello", Utils.toCamelCase("hello"));
        assertEquals("", Utils.toCamelCase(""));
        assertEquals(" ", Utils.toCamelCase(" "));
        assertEquals("H", Utils.toCamelCase("H"));
        assertEquals("H", Utils.toCamelCase("h"));
        assertEquals("HelloHello", Utils.toCamelCase("helloHello"));
    }
}