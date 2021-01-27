package com.meli.function;

import com.meli.models.Satellite;
import com.meli.models.SatellitesRequest;
import com.meli.models.SatellitesResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopSecretHandlerTest{

    TopSecretHandler function = new TopSecretHandler();

    @Test
    public void run(){
        String[] a = new String[]{"","","","un","mensaje"};
        String[] b = new String[]{"este","es","un",""};
        String[] c = new String[]{"hola","","",""};
        Satellite satelliteA = Satellite.builder().name("kenobi").distance("5").message(new ArrayList<>(Arrays.asList(a))).build();
        Satellite satelliteB = Satellite.builder().name("skywalker").distance("5").message(new ArrayList<>(Arrays.asList(b))).build();
        Satellite satelliteC = Satellite.builder().name("sato").distance("13").message(new ArrayList<>(Arrays.asList(c))).build();
        List<Satellite> satellites = List.of(satelliteA,satelliteB,satelliteC);
        SatellitesRequest request = new SatellitesRequest();
        request.setSatellites(satellites);
        SatellitesResponse response = function.execute(request);
        System.out.println(response.getPosition().getX()+"-"+response.getPosition().getY());
    }
}
