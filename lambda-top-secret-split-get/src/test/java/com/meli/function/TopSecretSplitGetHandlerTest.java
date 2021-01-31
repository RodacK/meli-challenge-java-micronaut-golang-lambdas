package com.meli.function;

import com.meli.models.EmptyRequest;
import com.meli.models.Satellite;
import com.meli.models.SatellitesResponse;
import com.meli.services.database.DynamoDatabaseServiceImpl;
import io.micronaut.core.annotation.Introspected;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@Introspected
public class TopSecretSplitGetHandlerTest{

    TopSecretSplitGetHandler function = new TopSecretSplitGetHandler();
    DynamoDatabaseServiceImpl databaseService = Mockito.mock(DynamoDatabaseServiceImpl.class);

    @Test
    public void run(){
        ArrayList<String> message1 = new ArrayList<>(List.of("este","","","mensaje",""));
        ArrayList<String> message2 = new ArrayList<>(List.of("","es","","","secreto"));
        ArrayList<String> message3 = new ArrayList<>(List.of("este","","un",""));
        Satellite satellite1 = Satellite.builder().distance("532.2").message(message1).name("skywalker").build();
        Satellite satellite2 = Satellite.builder().distance("154").message(message2).name("sato").build();
        Satellite satellite3 = Satellite.builder().distance("32.2").message(message3).name("kenovi").build();
        List<Satellite> satellites = List.of(satellite1,satellite2,satellite3);

        Mockito.when(databaseService.getSatellites()).then(invocation -> satellites);

        SatellitesResponse response = function.execute(new EmptyRequest());
        Assertions.assertEquals("este es un mensaje secreto",response.getMessage());
    }
}
