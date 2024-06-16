package fjpa.reflect.model;

import com.ricka.princy.fjpa.annotations.Column;
import com.ricka.princy.fjpa.annotations.Entity;
import com.ricka.princy.fjpa.annotations.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Dummy {
    @Id
    @Column
    private String id;

    @Column
    private String name;
}