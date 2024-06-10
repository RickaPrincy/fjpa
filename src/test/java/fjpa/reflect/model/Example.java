package fjpa.reflect.model;

import com.ricka.princy.fjpa.annotations.Column;
import com.ricka.princy.fjpa.annotations.Entity;
import com.ricka.princy.fjpa.annotations.Id;

@Entity(name = "example_table")
public class Example {
    @Id
    @Column
    private String id;
}