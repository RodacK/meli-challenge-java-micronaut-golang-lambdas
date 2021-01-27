package com.meli.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessageDecoderTest {

    @Test
    public void test1() {
        String[] a = new String[]{"este","","","mensaje",""};
        String[] b = new String[]{"","es","","","secreto"};
        String[] c = new String[]{"este","","un",""};
        Assertions.assertEquals("este es un mensaje secreto",MessageDecoder.getMessage(a,b,c));
    }

    @Test
    public void test2() {
        String[] a = new String[]{"este","","un","",""};
        String[] b = new String[]{"","este","","un",""};
        String[] c = new String[]{"","es","","mensaje"};
        Assertions.assertEquals("este es un mensaje",MessageDecoder.getMessage(a,b,c));
    }
    @Test
    public void test3() {
        String[] a = new String[]{"este","","un","",""};
        String[] b = new String[]{"","este","","un",""};
        String[] c = new String[]{"es","","mensaje",""};
        Assertions.assertEquals("este es un mensaje",MessageDecoder.getMessage(a,b,c));
    }
    @Test
    public void test4() {
        String[] a = new String[]{"","este","","un","mensaje"};
        String[] b = new String[]{"este","es","un",""};
        String[] c = new String[]{"hola","",""};
        Assertions.assertEquals("hola este es un mensaje",MessageDecoder.getMessage(a,b,c));
    }
    @Test
    public void test5() {
        String[] a = new String[]{"este","","un","",""};
        String[] b = new String[]{"","este","","un",""};
        String[] c = new String[]{"","es","un","mensaje"};
        Assertions.assertEquals("este es un mensaje",MessageDecoder.getMessage(a,b,c));
    }
    @Test
    public void test6() {
        String[] a = new String[]{"","","","un","mensaje"};
        String[] b = new String[]{"este","es","un",""};
        String[] c = new String[]{"hola","","",""};
        Assertions.assertEquals("hola este es un mensaje",MessageDecoder.getMessage(a,b,c));
    }
    @Test
    public void test7() {
        String[] a = new String[]{"","este","es","un","mensaje"};
        String[] b = new String[]{"este","","un","mensaje"};
        String[] c = new String[]{"","","es","","mensaje"};
        Assertions.assertEquals("este es un mensaje",MessageDecoder.getMessage(a,b,c));
    }
}
