package com.meli.services.database;


import com.meli.models.Satellite;

import java.util.List;

public interface DatabaseService {
    List<Satellite> getSatellites();
    void save(Satellite satellite);
}
