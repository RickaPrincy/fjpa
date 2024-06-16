package fjpa.reflect.model;

import com.ricka.princy.fjpa.annotations.Column;
import com.ricka.princy.fjpa.annotations.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class WithoutNoArgsConstructor {
    @Id
    @Column
    private String id;
}