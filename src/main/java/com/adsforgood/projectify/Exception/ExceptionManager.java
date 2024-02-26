package com.adsforgood.projectify.Exception;

public class ExceptionManager extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public final static String ALL = "All ";
    public static String ENTITY_SUCCESFULLYSAVED = "Entity succesfully saved";
    public static String ENTITY_SUCCESFULLYDELETED = "Entity succesfully deleted";
    public static String ENTITY_SUCCESFULLYMODIFIED = "Entity succesfully modified";
    public static String ENTITY_WITHSAMEKEY = "Another Entity with the same key was found";
    public static String ENTITY_NOENTITYTOUPDATE = "No Entity was found, with the typed id ";

    public ExceptionManager() {
    }

    public ExceptionManager(String exception) {
        super(exception);
    }

    public static class InvalidValueException extends ExceptionManager{
        private static final long serialVersionUID = 1L;

        public InvalidValueException(String value) {
            super("The value " + value + " is not a valid value");
        }
    }
    public static class InvalidRangeException extends ExceptionManager{
        private static final long serialVersionUID = 1L;

        public InvalidRangeException(String field, int min, int max) {
            super(field + " must be between " +  min + " and " + max);
        }
    }

    public static class NoEntityFoundWithValue extends ExceptionManager{
        private static final long serialVersionUID = 1L;

        public NoEntityFoundWithValue(String entity, String value) {
            super(entity + " not found with the value " + value);
        }
    }

    public static class NotAValidEntity extends ExceptionManager{
        private static final long serialVersionUID = 1L;

        public NotAValidEntity(String entity) {
            super(entity + " is not valid or must be null");
        }
    }

    public static class EmptyFieldException extends ExceptionManager {
        private static final long serialVersionUID = 1L;

        public EmptyFieldException(String field) {super("The value for the field " + field + " can not be null or empty");}
    }

    public static class InvalidFieldException extends ExceptionManager {
        private static final long serialVersionUID = 1L;

        public InvalidFieldException(String field, String value) {super("The value " + value + " for the field " + field + " is invalid or null");}
    }

    public static class InvalidPasswordFormatException extends ExceptionManager {
        private static final long serialVersionUID = 1L;

        public InvalidPasswordFormatException() {
            super("Invalid password format");
        }
    }

    public static class EmptyListException extends ExceptionManager {
        private static final long serialVersionUID = 1L;

        public EmptyListException(String field) {
            super("No elements found on " + field + " list");
        }
    }

    public static class InvalidDateException extends ExceptionManager {
        private static final long serialVersionUID = 1L;

        public InvalidDateException() {
            super("The given date is invalid");
        }
    }



}
