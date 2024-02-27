package com.adsforgood.projectify.Exception;

import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.utility.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExceptionManagerTest {

    @Test
    @DisplayName("Must throw an exception when is not a valid value")
    void mustCreateAnEmptyConstructor(){
        ExceptionManager exceptionManager = new ExceptionManager();
        assertNotNull(exceptionManager);
    }
    @Test
    @DisplayName("Must throw an exception when is not a valid value")
    void invalidValueExpceptionTest(){
        String email = "1234";
        String password = null;
        boolean emailValidator = Utils.isAValidEmail(email);
        ExceptionManager exception = Assert.assertThrows(ExceptionManager.InvalidValueException.class, () -> {
            if(Utils.isAString(email) && !emailValidator && !Utils.isValidPassword(password)){
                throw new ExceptionManager.InvalidValueException(email);

            }
        });
        String message = "The value " + email + " is not a valid value";
        String expectedMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(message));
    }

    @Test
    @DisplayName("Must throw an exception when the range is not valid")
    void invalidRangeExceptionTest(){
        int field = 50;
        int min = 1;
        int max = 40;
        ExceptionManager exception = Assert.assertThrows(ExceptionManager.InvalidRangeException.class, () -> {
            if (!Utils.isNumeric(String.valueOf(min)) && !Utils.isNumeric(String.valueOf(max))){

            }
            throw new ExceptionManager.InvalidRangeException(String.valueOf(field), min, max);
        });
        String message = field + " must be between " +  min + " and " + max;
        String expectedMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(message));
    }

    @Test
    @DisplayName("Must throw an exception when no entity found with value")
    void noEntityFoundWithValueTest(){
        String entity = "User";
        User user = User.builder().firstName("fgsagsa").build();
        ExceptionManager exception = Assert.assertThrows(ExceptionManager.NoEntityFoundWithValue.class, () -> {
            if(Utils.isAnObject(user) && Utils.isOnlyLetters(user.getFirstName())){
                throw new ExceptionManager.NoEntityFoundWithValue(entity,user.getFirstName());
            }
        });
        String message = entity + " not found with the value " + user.getFirstName();
        String expectedMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(message));
    }

    @Test
    @DisplayName("Must throw an exception when is not a valid entity")
    void notAValidEntityTest(){
        Object object = null;
        ExceptionManager exception = Assert.assertThrows(ExceptionManager.NotAValidEntity.class, () -> {
            if (!Utils.isAnObject(object)){
                throw new ExceptionManager.NotAValidEntity("Test");
            }
        });
        String message = "Test" + " is not valid or must be null";
        String expectedMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(message));
    }

    @Test
    @DisplayName("Must throw an exception when is an empty field")
    void emptyFieldExceptionTest(){
        String value = "Test";
        ExceptionManager exception = Assert.assertThrows(ExceptionManager.EmptyFieldException.class, () -> {
            throw new ExceptionManager.EmptyFieldException(value);
        });
        String message = "The value for the field " + value + " can not be null or empty";
        String expectedMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(message));
    }

    @Test
    @DisplayName("Must throw an exception when is an invalid field")
    void invalidFieldExceptionExpceptionTest() throws ParseException {
        String field = "date";
        String date = "27/03/2024";
        Calendar cal = Utils.stringDateToDate(date);
        int week = Utils.convertDateToISO8601(cal);
        String value = null;
        ExceptionManager exception = Assert.assertThrows(ExceptionManager.InvalidFieldException.class, () -> {
            if (Utils.isAValidStringDate(date) && week != 9){
                throw new ExceptionManager.InvalidFieldException(field,value);
            }
        });
        String message = "The value " + value + " for the field " + field + " is invalid or null";
        String expectedMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(message));
    }

    @Test
    @DisplayName("Must throw an exception when is an invalid password format")
    void invalidPasswordFormatExceptionTest(){
        String password = "1324";
        ExceptionManager exception = Assert.assertThrows(ExceptionManager.InvalidPasswordFormatException.class, () -> {
            if (!Utils.isValidPassword(password)){
                throw new ExceptionManager.InvalidPasswordFormatException();
            }
        });
        String message = "Invalid password format";
        String expectedMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(message));
    }

    @Test
    @DisplayName("Must throw an exception when is an empty list")
    void emptyListExceptionTest(){
        String field = "strings";
        List<String> strings = Collections.EMPTY_LIST;
        ExceptionManager exception = Assert.assertThrows(ExceptionManager.EmptyListException.class, () -> {
            if (strings.isEmpty()){
                throw new ExceptionManager.EmptyListException(field);
            }
        });
        String message = "No elements found on " + field + " list";
        String expectedMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(message));
    }

    @Test
    @DisplayName("Must throw an exception when is an invalid Date")
    void invalidDateExceptionExpceptionTest(){
        String date = "48/12/2024";
        ExceptionManager exception = Assert.assertThrows(ExceptionManager.InvalidDateException.class, () -> {
            if (Utils.isAString(date) && !Utils.isAValidStringDate(date)){
                throw new ExceptionManager.InvalidDateException();
            }
        });
        String message = "The given date is invalid";
        String expectedMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(message));
    }

}
