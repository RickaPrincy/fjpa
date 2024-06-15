package com.ricka.princy.fjpa.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class SqlMetaData{
    private final String name;
}