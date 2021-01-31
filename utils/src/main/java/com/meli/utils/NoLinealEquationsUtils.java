package com.meli.utils;

import com.meli.enums.MessagesEnums;
import com.meli.exceptions.QuasarException;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class NoLinealEquationsUtils {
    public static double[]  getCoefficients(String eq1, String eq2){
        String firstEq = eq1.replace("1x^2","").replace("+1y^2","");
        String secondEq = eq2.replace("1x^2","").replace("+1y^2","");

        float resultInX = Float.parseFloat(!firstEq.contains("x")?"0":firstEq.substring(0,firstEq.indexOf("x")))
                -Float.parseFloat(!secondEq.contains("x")?"0":secondEq.substring(0,secondEq.indexOf("x")));
        float resultInY = Float.parseFloat(!firstEq.contains("y")?"0":firstEq.substring(firstEq.indexOf("x")+1,firstEq.indexOf("y")))
                -Float.parseFloat(!secondEq.contains("y")?"0":secondEq.substring(secondEq.indexOf("x")+1,secondEq.indexOf("y")));
        if(resultInX == 0f && resultInY == 0f){
            throw new QuasarException(MessagesEnums.TRILATERATION_ERROR.getAll());
        }
        float resultInConstantPart = Float.parseFloat(firstEq.substring(firstEq.indexOf("=")+1))-Float.parseFloat(secondEq.substring(secondEq.indexOf("=")+1));
        double[] result = new double[3];
        result[0] = resultInX;
        result[1] = resultInY;
        result[2] = resultInConstantPart;
        return result;
    }

    public static String splitSign(String eq){
        return eq.contains("--")?eq.replace("--","+"):eq.contains("-+")?eq.replace("-+","-"):eq.contains("+-")?eq.replace("+-","-"):eq;
    }
}
