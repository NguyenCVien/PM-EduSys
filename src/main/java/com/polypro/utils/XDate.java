
package com.polypro.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XDate {
    public static final SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
    //Chuyen doi String sang Date
    public static Date toDate(String date, String...pattern) {
            try {
                if(pattern.length > 0) {
                    formater.applyPattern(pattern[0]);
                }
                if(date == null) {
                    return XDate.now();
                }
                return formater.parse(date);
            } catch (ParseException ex) {
                //throw new RuntimeException(ex);
            }
        return null;
            
    }

//    public static Date toDate(String date, String pattern) {
//            try {
//                    formater.applyPattern(pattern);
//                    return formater.parse(date);
//            } catch (ParseException ex) {
//                throw new RuntimeException(ex);
//            }
//            
//    }
//    public static String toString(Date date, String...pattern) {
//        if(pattern.length > 0) {
//            formater.applyPattern(pattern[0]);
//        }
//        if (date == null) {
//            date = XDate.now();
//        }
//        return formater.format(date);
//        
//    }
    public static String toString(Date date, String...pattern) {
            formater.applyPattern(pattern[0]);
            return formater.format(date);
        
    }
    
    public static Date now() {
        return new Date();
    }
    
    public static Date addDays(Date date, long days) { //Bo sung so ngay vao thoi gian
        date.setTime(date.getTime() + days*24*60*60*1000);
        return date;
    }
    
    public static Date add(int days) {
        Date now = XDate.now();
        now.setTime(now.getTime()+days*24*60*60*1000);
        return now;
    }
    
}
