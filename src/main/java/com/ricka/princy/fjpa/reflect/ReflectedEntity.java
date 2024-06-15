package com.ricka.princy.fjpa.reflect;

import com.ricka.princy.fjpa.types.Attribute;
import com.ricka.princy.fjpa.types.SqlMetaData;
import lombok.Builder;

import java.util.List;

@Builder
public record ReflectedEntity<T>(List<Attribute> attributes, SqlMetaData sqlMetaData) {
}