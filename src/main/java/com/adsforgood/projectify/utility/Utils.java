package com.adsforgood.projectify.utility;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Utils {

    @Autowired
    private static Calendar calendar;

    public static int MIN_PERCENTAGE = 1;
    public static int MAX_PERCENTAGE = 100;

    public static int MIN_DAY = 01;
    public static int MAX_DAY = 31;

    public static int MIN_MONTH = 01;
    public static int MAX_MONTH = 31;

    public static boolean isNumeric(String word) {
        boolean ret = false;
        Pattern pat = Pattern.compile("[^0-9',.\\s]");
        Matcher mat = pat.matcher(word);
        if (!mat.find()) {
            ret = true;
        }
        return ret;
    }

    public static boolean isOnlyLetters(String word) {
        boolean ret = false;
        Pattern pat = Pattern.compile("[^A-Za-z',.\\s]");
        Matcher mat = pat.matcher(word);
        if (!mat.find()) {
            ret = true;
        }
        return ret;
    }

    public static boolean isAString(String word){
        boolean ret = false;
        if (!word.trim().equals("")){
            ret = true;
        }
        return ret;
    }

    public static boolean isAnObject(Object object){
        boolean ret = false;
        if (object != null){
            ret = true;
        }
        return ret;
    }

    public static boolean isAValidEmail(String email){
        boolean allowLocal = true;
        return EmailValidator.getInstance(allowLocal).isValid(email);
    }

    public static boolean isValidPassword(String password){
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean validateRangeField(int value, int min, int max){
        if (value >= min && value <= max){
            return true;
        }
        return false;
    }

    public static String[] parseStringDate(String date){
        return date.split("/");
    }

    public static boolean isAValidStringDate(String date){
        Calendar calendar3 =  Calendar.getInstance();
        String[] dateArray = parseStringDate(date);
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        boolean format = date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
        return format &&
                validateRangeField(day, MIN_DAY, MAX_DAY) &&
                validateRangeField(month, MIN_MONTH, MAX_MONTH) &&
                year <= calendar3.get(Calendar.YEAR);
    }
    public static Calendar stringDateToDate(String date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        cal.setTime(sdf.parse(date));
        return cal;
    }

    public static int convertDateToISO8601(Calendar calendar){
        calendar.setMinimalDaysInFirstWeek(4);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

}
