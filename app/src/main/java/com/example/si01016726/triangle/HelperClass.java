package com.example.si01016726.triangle;

/**
 * Created by Thor-PC on 7. 09. 2016.
 */
public class HelperClass {
    public static final String UNIT_MM = "mm";
    public static final String UNIT_CM = "cm";
    public static final String UNIT_M = "m";

    public static double CalculateHypotenuse(double sideValue1, double sideValue2, String unit) {
        double result=0.0;

        if ((sideValue1>0)&&(sideValue2>0)) {
            result= Math.sqrt(Math.pow(sideValue1,2)+Math.pow(sideValue2,2));
        }

        if (unit==UNIT_CM) {
            result=result/10;
        }
        else if (unit==UNIT_M) {
            result=result/1000;
        }

        return result;
    }

    public static double CalculateSide(double hypotenuse, double side, String unit) {
        double result=0.0;

        if ((hypotenuse>0)&&(side>0)) {
            result= Math.sqrt(Math.pow(hypotenuse,2)-Math.pow(side,2));
        }

        if (unit==UNIT_CM) {
            result=result/10;
        }
        else if (unit==UNIT_M) {
            result=result/1000;
        }

        return result;
    }

}
