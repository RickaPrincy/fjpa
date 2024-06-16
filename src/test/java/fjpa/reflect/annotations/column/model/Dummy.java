package fjpa.reflect.annotations.column.model;

import com.ricka.princy.fjpa.annotations.Column;

public class Dummy {
    @Column
    private String id;

    @Column(name = "name_column", required = false)
    private String name;

    private String notColumn;
}