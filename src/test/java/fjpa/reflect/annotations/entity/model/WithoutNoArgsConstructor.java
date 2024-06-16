package fjpa.reflect.annotations.entity.model;

import com.ricka.princy.fjpa.annotations.Entity;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class WithoutNoArgsConstructor {
    private String id;
}