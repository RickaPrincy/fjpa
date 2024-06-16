package com.ricka.princy.fjpa.types;

import lombok.Getter;

@Getter
public class ColumnSqlMetaData extends SqlMetaData {
    private final boolean isRequired;
    public ColumnSqlMetaData(String name, boolean isRequired) {
        super(name);
        this.isRequired = isRequired;
    }
}