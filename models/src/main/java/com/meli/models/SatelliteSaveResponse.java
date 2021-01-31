package com.meli.models;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class SatelliteSaveResponse {
    private Map<String,String> response;
}
