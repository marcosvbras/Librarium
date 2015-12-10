/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marcos
 */
public class DateConvert {
    
    public static String convertToYearMonthDay(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
    public static String convertToDayMonthYear(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
