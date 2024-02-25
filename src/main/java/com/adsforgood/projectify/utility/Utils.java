package com.adsforgood.projectify.utility;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Utils {

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
        Pattern pat = Pattern.compile("[^A-Za-z0-9',.\\s]");
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

    public static void main(String[] args) {
        System.out.println(Utils.isAString("lkoasmfas"));
    }
}
