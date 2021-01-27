package com.meli.function;

import com.meli.constants.Constants;
import com.meli.models.EmptyRequest;
import com.meli.models.Position;
import com.meli.models.Satellite;
import com.meli.models.SatellitesResponse;
import com.meli.services.database.DatabaseService;
import com.meli.services.database.DynamoDatabaseServiceImpl;
import com.meli.utils.Locator;
import com.meli.utils.MessageDecoder;
import com.meli.utils.SatelliteUtils;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;

import java.util.List;

@Introspected
public class TopSecretSplitGetHandler extends MicronautRequestHandler<EmptyRequest, SatellitesResponse> {

    private final DatabaseService databaseService = DynamoDatabaseServiceImpl.getInstance();

    @Override
    public SatellitesResponse execute(EmptyRequest request) {
        String[] satellitesNames = Constants.SATELLITES_NAMES.split(",");
        List<Satellite> satellites = databaseService.getSatellites();

        Satellite kenobi = SatelliteUtils.getSatellite(satellites,satellitesNames[0]);
        Satellite skywalker = SatelliteUtils.getSatellite(satellites,satellitesNames[1]);
        Satellite sato = SatelliteUtils.getSatellite(satellites,satellitesNames[2]);

        String decodedMessage = MessageDecoder.getMessage(kenobi.getMessage().toArray(String[]::new),
                skywalker.getMessage().toArray(String[]::new),sato.getMessage().toArray(String[]::new));

        Position position = Locator.getLocation(Float.parseFloat(kenobi.getDistance()),Float.parseFloat(skywalker.getDistance()),
                Float.parseFloat(sato.getDistance()));

        return SatellitesResponse.builder().position(position).message(decodedMessage).build();
    }
}
