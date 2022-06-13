package com.cron.fields;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CronField extends ExpressionPart{

    protected final char[] SPECIAL_CHARS = new char[] { '/', '-', ',' };

    protected ExpressionPartName name;
    protected String expression;
    protected Integer startRange;
    protected Integer endRange;

    protected List<Integer> values = new ArrayList<>();


    public CronField parseExpression() throws ParseException {
        if (StringUtils.isEmpty(expression)) {
            throw new ParseException(name.toString() + " expression is empty", 0);
        }
        if (expression.equals("*")) {
            //generate 0-59 minutes
            values.addAll(generateAllValues());
        } else if (!StringUtils.containsAny(expression, SPECIAL_CHARS)) {
            //return expression
            values.add(Integer.valueOf(expression));
        } else if (expression.contains("/")) {
            //generate split values
            values.addAll(generateSplitValues());
        } else if (expression.contains("-")) {
            //generate range values
            values.addAll(generateRangeValues());
        } else if (expression.contains(",")) {
            //generate seperate values
            values.addAll(generateDifferentValues());
        }
        return this;
    }

    @Override
    public String toDisplayString() {
        return String.format("%s%s", StringUtils.rightPad(name.toString(), 14), values.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    protected abstract List<Integer> generateAllValues();

    protected abstract List<Integer> generateSplitValues();

    protected abstract List<Integer> generateRangeValues();

    protected abstract List<Integer> generateDifferentValues();
}
