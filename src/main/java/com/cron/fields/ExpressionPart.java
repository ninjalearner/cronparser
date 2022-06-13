package com.cron.fields;

public abstract class ExpressionPart {

    protected ExpressionPartName name;
    protected String expression;

    public abstract String toDisplayString();
}
