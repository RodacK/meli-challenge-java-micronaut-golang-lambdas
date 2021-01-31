package com.meli.utils;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class CircleStandardEquation {
    private String firstBinomial = "(x-%s)^2";
    private String secondBinomial = "(y-%s)^2";
    private float constantPart;

    public CircleStandardEquation(float x,float y,float numericPart){
        this.firstBinomial = (x==0f)?"x^2":NoLinealEquationsUtils.splitSign(String.format(firstBinomial,x));
        this.secondBinomial = (y==0f)?"y^2":NoLinealEquationsUtils.splitSign(String.format(secondBinomial,y));
        //System.out.println("Binomios iniciales: "+firstBinomial+"...."+secondBinomial);
        this.constantPart = (float) Math.pow(numericPart,2);
    }

    public String getExpandedEquation(){
        Binomial binomial= new Binomial();
        String firstPart = binomial.getFactorizedBinomial(firstBinomial);
        String secondPart = binomial.getFactorizedBinomial(secondBinomial);
        Float constantsInX = firstPart.lastIndexOf("+")==-1?0f:getNumericPart(firstPart);
        Float constantsInY = secondPart.lastIndexOf("+")==-1?0f:getNumericPart(secondPart);
        constantPart = constantPart -constantsInX-constantsInY;
        String result = (firstPart.lastIndexOf("+")==-1?firstPart:deleteNumericPart(firstPart))+"+"+(secondPart.lastIndexOf("+")==-1?secondPart:deleteNumericPart(secondPart))+"="+ constantPart;
        //System.out.println("ecuacion expandida: "+result);
        return result;
    }

    public static Float getNumericPart(String binomial){
        return Float.parseFloat(binomial.substring(binomial.lastIndexOf("+")));
    }

    public static String deleteNumericPart(String binomial){
        return binomial.substring(0,binomial.lastIndexOf("+"));
    }

}
