package com.lattisi.euclide.items;

public class ItemWithoutMeasureException extends Exception {

    public ItemWithoutMeasureException() {
        super();
    }

    public ItemWithoutMeasureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemWithoutMeasureException(Throwable cause) {
        super(cause);
    }

    protected ItemWithoutMeasureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ItemWithoutMeasureException(String message) {
        super(message);
    }
}
