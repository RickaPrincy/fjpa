package fjpa.reflect.reflect_accessors.model;

import com.ricka.princy.fjpa.annotations.Accessors;
import lombok.Getter;
import lombok.Setter;

public class Example {
    @Getter
    @Setter
    private String id;

    @Accessors(getter = "getCustom", setter = "setCustom")
    private String customField;
    public String getCustom() {
        return customField;
    }
    public String setCustom() {
        return customField;
    }

    // K.O
    private String withoutGetterAndSetter;

    @Setter
    private String withoutGetter;

    @Getter
    private String withoutSetter;
}