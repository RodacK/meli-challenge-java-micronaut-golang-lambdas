package com.meli.models;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Introspected
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SatellitesResponse {

    @NonNull
    private Position position;

    @NonNull
    @NotBlank
    private String message;

}
