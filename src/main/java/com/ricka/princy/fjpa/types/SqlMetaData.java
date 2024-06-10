package com.ricka.princy.fjpa.types;

import lombok.Builder;

@Builder
public record SqlMetaData(String name, boolean isRequired) {
}
