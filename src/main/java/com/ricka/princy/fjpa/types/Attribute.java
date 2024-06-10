package com.ricka.princy.fjpa.types;

import lombok.Builder;

@Builder
public record Attribute(String name, SqlMetaData sqlMetaData, boolean isId) {
}