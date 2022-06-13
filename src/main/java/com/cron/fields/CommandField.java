package com.cron.fields;

import org.apache.commons.lang3.StringUtils;

public class CommandField extends ExpressionPart {

    public CommandField(String expression) {
        this.name = ExpressionPartName.COMMAND;
        this.expression = expression;
    }

    @Override
    public String toDisplayString() {
        return String.format("%s%s", StringUtils.rightPad(name.toString(), 14), expression);
    }
}
