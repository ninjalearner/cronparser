package com.cron.fields;

public enum ExpressionPartName {
    MINUTE("minute"),
    HOUR("hour"),
    DAY_OF_MONTH("day of month"),
    MONTH("month"),
    DAY_OF_WEEK("day of week"),

    COMMAND("command");


    private String name;

    ExpressionPartName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
