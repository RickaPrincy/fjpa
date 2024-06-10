package fjpa.reflect.model;

import com.ricka.princy.fjpa.annotations.Column;
import com.ricka.princy.fjpa.annotations.Entity;

@Entity
public class WithoutId {
    @Column
    private String nothing;
}
