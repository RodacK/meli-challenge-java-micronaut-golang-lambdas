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
import org.junit.jupiter.api.Test;

import java.util.List;

@Introspected
public class TopSecretSplitGetHandlerTest{

    TopSecretSplitGetHandler function = new TopSecretSplitGetHandler();

    @Test
    public void run(){
        function.execute(new EmptyRequest());
    }
}
