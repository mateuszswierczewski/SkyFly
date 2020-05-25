package pl.mswierczewski.skyfly.services.common;

public interface ValueExists {

    boolean isValueExists(Object value, String columnName) throws UnsupportedOperationException;
}