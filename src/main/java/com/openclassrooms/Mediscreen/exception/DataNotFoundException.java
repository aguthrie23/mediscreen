package com.openclassrooms.Mediscreen.exception;

public class DataNotFoundException extends Exception {
    public DataNotFoundException () {

    }

    public DataNotFoundException (String message) {
        super (message);
    }

    public DataNotFoundException (Throwable cause) {
        super (cause);
    }

    public DataNotFoundException (String message, Throwable cause) {
        super (message, cause);
    }
}
