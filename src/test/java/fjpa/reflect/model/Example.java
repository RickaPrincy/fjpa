package fjpa.reflect.model;

import com.ricka.princy.fjpa.annotations.Column;
import com.ricka.princy.fjpa.annotations.Entity;
import com.ricka.princy.fjpa.annotations.Id;
import lombok.Data;

@Entity(name = "example_table")
@Data
public class Example {
    @Id
    @Column
    private String id;
}