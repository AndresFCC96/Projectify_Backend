package com.adsforgood.projectify.base;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
public class BaseTest {

    private static final String EXCEPTION_THROWN = "Thrown exception";
    private static final String EXPECTED_EXCEPTION = "Expected exception";

    public static <T> void assertThrows(Supplier<T> supplier, Class<? extends Exception> exception, String message) {
        try {
            supplier.get();
            fail();
        } catch (Exception e) {
            assertTrue(exception.isInstance(e), EXPECTED_EXCEPTION + exception.getCanonicalName() + EXCEPTION_THROWN
                    + e.getClass().getCanonicalName());
            assertTrue(e.getMessage().contains(message));
        }
    }

    public static void assertThrows(Thunk thunk, Class<? extends Exception> exception, String message) {
        try {
            thunk.execute();
            fail();
        } catch (Exception e) {
            assertTrue(exception.isInstance(e), EXPECTED_EXCEPTION + exception.getCanonicalName() + EXCEPTION_THROWN
                    + e.getClass().getCanonicalName());
            assertTrue(e.getMessage().contains(message));
        }
    }

    @FunctionalInterface
    public interface Thunk {
        void execute();
    }
}
