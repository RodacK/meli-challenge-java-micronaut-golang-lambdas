package com.meli.function;

import com.meli.constants.Constants;
import com.meli.enums.MessagesEnums;
import com.meli.exceptions.QuasarException;
import com.meli.models.Satellite;
import com.meli.models.SatelliteSaveResponse;
import com.meli.services.database.DatabaseService;
import com.meli.services.database.DynamoDatabaseServiceImpl;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;

import java.util.Map;

@Introspected
public class TopSecretSplitSaveHandler extends MicronautRequestHandler<Satellite, SatelliteSaveResponse> {
    private final DatabaseService databaseService = DynamoDatabaseServiceImpl.getInstance();

    @Override
    public SatelliteSaveResponse execute(Satellite request) {
        if(request.getName().isBlank() || !Constants.SATELLITES_NAMES.contains(request.getName())){
            throw new QuasarException(MessagesEnums.INVALID_SATELLITE_NAME.getAll());
        }
        if(request.getMessage().isEmpty()){
            throw new QuasarException(MessagesEnums.INVALID_SATELLITE_MESSAGE.getAll());
        }
        databaseService.save(request);
        SatelliteSaveResponse response = new SatelliteSaveResponse();
        Map<String,String> message = Map.of("code", MessagesEnums.SAVE_SATELLITE_SUCCESSFUL.getCode(),
                "message",String.format(MessagesEnums.SAVE_SATELLITE_SUCCESSFUL.getMessage(),request.getName()));
        response.setResponse(message);
        return response;
    }
}
