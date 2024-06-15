package com.ricka.princy.fjpa.types;

import lombok.Builder;

import java.lang.reflect.Method;

@Builder
public record Attribute(
    boolean isId,
    String name,
    Method getter,
    Method setter,
    ColumnSqlMetaData sqlMetaData
) {}