package com.meli.function;


import com.meli.models.Satellite;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TopSecretSplitSaveHandlerTest {
    TopSecretSplitSaveHandler function = new TopSecretSplitSaveHandler();

    @Test
    public void run(){
        ArrayList list = new ArrayList<>();
        list.add("");
        list.add("hola");
        list.add("");
        Satellite satellite = Satellite.builder().distance("123.3").name("ayuwoki").message(list).build();
        function.execute(satellite);
    }

}
