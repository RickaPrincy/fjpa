package com.ricka.princy.fjpa.types;

import lombok.Builder;

@Builder
public record Model(Attribute id, String name, SqlMetaData sqlMetaData) {}