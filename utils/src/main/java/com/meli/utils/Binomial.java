package com.meli.utils;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Binomial {

    private String firstPart;


    public String getSquareOfSum(String binomial){
        this.firstPart = getFirstPart(binomial);
        return "1"+this.firstPart+"^2+"+get2ab(binomial)+"+"+ Math.pow(getSecondPart(binomial),2);
    }

    public String getSquareOfSubtraction(String binomial){
        this.firstPart = getFirstPart(binomial);
        return "1"+this.firstPart+"^2-"+get2ab(binomial)+"+"+ Math.pow(getSecondPart(binomial),2);
    }

    public String get2ab(String binomial){
        return (getSecondPart(binomial)*2)+this.firstPart;
    }

    public static Float getSecondPart(String binomial){
        return Float.parseFloat(binomial.substring(3,binomial.indexOf(")")));
    }

    public static String getFirstPart(String binomial){
        return binomial.substring(1,2);
    }

    public String getFactorizedBinomial(String binomial){
        return binomial.contains("+")?getSquareOfSum(binomial):binomial.contains("-")?getSquareOfSubtraction(binomial):"1"+binomial;
    }
}
