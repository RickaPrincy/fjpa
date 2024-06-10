package fjpa.reflect.model;

import com.ricka.princy.fjpa.annotations.Column;
import com.ricka.princy.fjpa.annotations.Entity;
import com.ricka.princy.fjpa.annotations.Id;
import lombok.Setter;

@Setter
@Entity
public class WithoutGetter {
    @Id
    @Column
    private String id;
}