package com.ceny.utils;

/**
 * Created by chensongkui on 2017/3/27.
 */
public class StringUtil {
    public static int getLevel(String path){
        if(path.equals("/")){
            return 0;
        }
        int sum = 0;
        for(int i=0;i<path.length();i++){
            if(path.charAt(i) == '/'){
                sum++;
            }
        }
        return sum;
    }
}
