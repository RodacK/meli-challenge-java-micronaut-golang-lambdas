package com.meli.models;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteSaveResponse {
    private Map<String,String> response;
}
