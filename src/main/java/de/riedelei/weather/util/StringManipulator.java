package de.riedelei.weather.util;

public class StringManipulator {

    private final StringBuilder stringBuilder;

    public StringManipulator(String main) {
        stringBuilder = new StringBuilder(main);
    }

    public void addVariableToString(String newString) {
        stringBuilder.append(newString);
    }

    public String getUrl() {
        return stringBuilder.toString();
    }
}
