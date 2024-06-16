package com.ricka.princy.fjpa.types;

public record Model<T>(
    Class<T> clazz,
    String name,
    Attribute<T> id,
    SqlMetaData sqlMetaData
) {}