package com.meli.services.database;


import com.meli.models.Satellite;

import java.util.List;

public interface DatabaseService {
    public List<Satellite> getSatellites();
    public void save(Satellite satellite);
}
