package com.meli.utils;

import com.meli.enums.MessagesEnums;
import com.meli.exceptions.QuasarException;
import com.meli.models.Satellite;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class SatelliteUtils {
    public static Satellite getSatellite(List<Satellite> satelliteList, String name){
        Optional<Satellite> satellite = satelliteList.stream().filter(hasThisName(name)).findFirst();
        if (satellite.isPresent()){
            return satellite.get();
        }else{
            throw new QuasarException(MessagesEnums.SATELLITE_NOT_FOUND.getAll(),name);
        }
    }

    private static Predicate<Satellite> hasThisName(String name) {
        return person -> person.getName().equals(name);
    }
}
