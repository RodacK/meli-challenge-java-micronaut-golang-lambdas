package com.meli.models;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Introspected
@NoArgsConstructor
@Setter
@Getter
public class SatellitesRequest {

    @NonNull
    @NotEmpty
    private List<Satellite> satellites;
}
