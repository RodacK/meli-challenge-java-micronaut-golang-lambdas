package com.meli.function;

import com.meli.models.Satellite;
import com.meli.models.SatellitesRequest;
import com.meli.models.SatellitesResponse;
import com.meli.utils.SatelliteInfoUtil;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;

import java.util.List;

@Introspected
public class TopSecretHandler extends MicronautRequestHandler<SatellitesRequest, SatellitesResponse> {

    @Override
    public SatellitesResponse execute(SatellitesRequest request) {
        List<Satellite> satellites = request.getSatellites();
        return SatelliteInfoUtil.getInfo(satellites);
    }
}
