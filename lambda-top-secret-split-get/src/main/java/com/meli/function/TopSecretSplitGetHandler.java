package com.meli.function;

import com.meli.models.EmptyRequest;
import com.meli.models.Satellite;
import com.meli.models.SatellitesResponse;
import com.meli.services.database.DatabaseService;
import com.meli.services.database.DynamoDatabaseServiceImpl;
import com.meli.utils.SatelliteInfoUtil;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;

import java.util.List;

@Introspected
public class TopSecretSplitGetHandler extends MicronautRequestHandler<EmptyRequest, SatellitesResponse> {

    private final DatabaseService databaseService = DynamoDatabaseServiceImpl.getInstance();

    @Override
    public SatellitesResponse execute(EmptyRequest request) {
       List<Satellite> satellites = databaseService.getSatellites();
       return SatelliteInfoUtil.getInfo(satellites);
    }
}
