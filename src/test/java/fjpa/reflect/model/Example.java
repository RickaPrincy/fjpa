package fjpa.reflect.model;

import com.ricka.princy.fjpa.annotations.Accessors;
import com.ricka.princy.fjpa.annotations.Column;
import com.ricka.princy.fjpa.annotations.Entity;
import com.ricka.princy.fjpa.annotations.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity(name = "example_table")
@AllArgsConstructor
@NoArgsConstructor
public class Example {
    @Id
    @Column
    private String id;

    @Column(name = "created_at", required = false)
    @Accessors(getter = "getCreated", setter = "setCreated")
    private Instant createdAt;

    private BigDecimal notColumn;

    public Instant getCreated(){
        return this.createdAt;
    }

    public void setCreated(Instant createdAt){
        this.createdAt = createdAt;
    }
}