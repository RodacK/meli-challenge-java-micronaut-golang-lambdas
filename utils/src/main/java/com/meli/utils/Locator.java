package com.meli.utils;

import com.meli.models.Position;

public class Locator {

    public static Position getLocation(double... distances){



        return Position.builder().x(2).y(5).build();

    }

    public static void main(String[] args) {
        getLocation(123,453,876);
    }
}
