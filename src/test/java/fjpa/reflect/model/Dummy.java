package fjpa.reflect.model;

import com.ricka.princy.fjpa.annotations.Column;
import com.ricka.princy.fjpa.annotations.Entity;
import com.ricka.princy.fjpa.annotations.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
public class Dummy {
    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private Instant createdAt;

    private BigDecimal notColumn;
}