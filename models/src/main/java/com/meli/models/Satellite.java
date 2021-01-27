package com.meli.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.meli.converter.ListStringConverter;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Introspected
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "satellites-info")
public class Satellite {

    @NonNull
    @NotBlank
    @DynamoDBHashKey(attributeName="name")
    private String name;

    @NonNull
    @NotBlank
    @DynamoDBAttribute(attributeName = "distance")
    private String distance;

    @NonNull
    @NotEmpty
    @DynamoDBAttribute(attributeName = "message")
    @DynamoDBTypeConverted(converter = ListStringConverter.class)
    private ArrayList<String> message;

}
