package com.meli.utils;

import com.meli.constants.Constants;
import com.meli.models.Position;
import com.meli.models.Satellite;
import com.meli.models.SatellitesResponse;
import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class SatelliteInfoUtil {

    public static SatellitesResponse getInfo(List<Satellite> satellites){
        String[] satellitesNames = Constants.SATELLITES_NAMES.split(",");
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
