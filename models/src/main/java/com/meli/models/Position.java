package com.meli.models;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class Position {

    @NonNull
    @NotBlank
    private double x;

    @NonNull
    @NotBlank
    private double y;
}
