package com.meli.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class ListStringConverter implements DynamoDBTypeConverter<ArrayList<String>,ArrayList<String>> {

    private UnaryOperator<String> emptyToDefault = x -> x.equals("")?"_":x;
    private UnaryOperator<String> defaultToEmpty = x -> x.equals("_")?"":x;

    @Override
    public ArrayList<String> convert(ArrayList<String> lmo) {
        lmo.replaceAll(emptyToDefault);
        return lmo;
    }

    @Override
    public ArrayList<String> unconvert(ArrayList<String> lms) {
        lms.replaceAll(defaultToEmpty);
        return lms;
    }
}
