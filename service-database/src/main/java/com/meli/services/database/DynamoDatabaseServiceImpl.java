package com.meli.services.database;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.meli.enums.MessagesEnums;
import com.meli.exceptions.QuasarException;
import com.meli.models.Satellite;

import java.util.List;
import java.util.Objects;

public class DynamoDatabaseServiceImpl implements DatabaseService {

    private static DynamoDatabaseServiceImpl instance;

    private DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().build());

    private DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
            .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE)
            .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.EVENTUAL)
            .withTableNameOverride(null)
            .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.EAGER_LOADING)
            .build();


    public  static DynamoDatabaseServiceImpl getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DynamoDatabaseServiceImpl();
        }
        return instance;
    }

    /**
     * debido a que son solo tres datos sirve el scan, pero cuando tengamos un unico identificador
     * para lo tres satelites (por ejemplo un uuid o un sessionId), podemos usar facilmente
     * una query para optimizar costos
     * @return
     */
    @Override
    public List<Satellite> getSatellites() {
        try {
            DynamoDBScanExpression expression = new DynamoDBScanExpression();
            expression.setConsistentRead(false);
            expression.setLimit(3);
            expression.withLimit(3);
            ScanResultPage<Satellite> resultPage = mapper.scanPage(Satellite.class,expression,mapperConfig);
            return resultPage.getResults();
        }catch (IllegalArgumentException | AmazonServiceException e){
            throw new QuasarException(MessagesEnums.GET_SATELLITE_ERROR.getAll(),e);
        }catch (AmazonClientException e){
            String extra = " Error al intentarse comunicar con dynamo";
            throw new QuasarException(MessagesEnums.GET_SATELLITE_ERROR.getAll().concat(extra),e);
        }
    }

    @Override
    public void save(Satellite satellite) {
        try {
            mapper.save(satellite);
        }catch (IllegalArgumentException | AmazonServiceException e){
            throw new QuasarException(MessagesEnums.SAVE_SATELLITE_ERROR.getAll(),e,satellite.getName());
        }catch (AmazonClientException e){
            String extra = " Error al intentarse comunicar con dynamo";
            throw new QuasarException(MessagesEnums.SAVE_SATELLITE_ERROR.getAll().concat(extra),e);
        }
    }
}
