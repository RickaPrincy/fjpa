package com.ricka.princy.fjpa.utils;

public class Utils {
    public static String toCamelCase(String text){
        if(text.isEmpty()){
            return "";
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}