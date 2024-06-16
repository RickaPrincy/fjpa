package fjpa.reflect.annotations.column;

import com.ricka.princy.fjpa.reflect.annotations.ReflectColumn;
import fjpa.reflect.annotations.column.model.Dummy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColumnTest {
    @Test
    void should_return_only_columns_fields(){
        var fields = ReflectColumn.getColumnFields(Dummy.class);
        assertEquals(2, fields.size());
        assertEquals("id", fields.getFirst().getName());
        assertEquals("name", fields.get(1).getName());
    }

    @Test
    void should_return_correct_sql_metadata(){
        var idField = ReflectColumn.getColumnFields(Dummy.class).getFirst();
        var idSqlMetaData = ReflectColumn.getColumnSqlMetaData(idField);
        assertEquals("id", idSqlMetaData.getName());
        assertTrue(idSqlMetaData.isRequired());

        var nameField = ReflectColumn.getColumnFields(Dummy.class).get(1);
        var nameSqlMetaData = ReflectColumn.getColumnSqlMetaData(nameField);
        assertEquals("name_column", nameSqlMetaData.getName());
        assertFalse(nameSqlMetaData.isRequired());
    }
}