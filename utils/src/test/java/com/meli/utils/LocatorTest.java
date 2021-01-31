package com.meli.utils;

import com.meli.exceptions.QuasarException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocatorTest {
    @Test
    public void test1() {
        Assertions.assertEquals("Position(x=-6073.380642361111, y=-3031.6903211805557)",Locator.getLocation(234,12.23f,32).toString());
    }
    @Test
    public void test2() {
        Assertions.assertEquals("Position(x=-5.994122094578213, y=2.002938893106249)",Locator.getLocation(0,0.23f,32).toString());
    }
    @Test
    public void test3() {
        Throwable exeption = Assertions.assertThrows(QuasarException.class,()-> Locator.getLocation(-3,12.23f,32));
        Assertions.assertEquals("422-107-No se puede completar el proceso, verifique que las distancias no sea negativas",exeption.getMessage());
    }
    @Test
    public void test4() {
        Assertions.assertEquals("Position(x=-6.0, y=2.0)",Locator.getLocation(0,0,0).toString());
    }
}
