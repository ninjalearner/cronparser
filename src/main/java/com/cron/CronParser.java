package com.cron;

import com.cron.fields.*;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CronParser {

    private List<ExpressionPart> expressionParts;

    public List<ExpressionPart> getExpressionParts() {
        return expressionParts;
    }

    private void setExpressionParts(List<ExpressionPart> expressionParts) {
        this.expressionParts = expressionParts;
    }

    public void parseExpression(String expression) throws ParseException {
        if (StringUtils.isEmpty(expression)) {
            throw new IllegalArgumentException("Expression is empty");
        }

        String[] splitExpression = expression.split(" ");

        if (splitExpression.length != 6) {
            throw new ParseException("Expression is not valid", 0);
        } else {
            setExpressionParts(getExpressionParts(splitExpression));
        }
    }

    public List<ExpressionPart> getExpressionParts(String[] splitExpression) throws ParseException {
        List<ExpressionPart> expressionParts = new ArrayList<>();
        for (int i = 0; i < splitExpression.length; i++) {
            switch (i) {
                case 0:
                    expressionParts.add(new MinuteField(splitExpression[i]).parseExpression());
                    break;
                case 1:
                    expressionParts.add(new HourField(splitExpression[i]).parseExpression());
                    break;
                case 2:
                    expressionParts.add(new DayOfMonthField(splitExpression[i]).parseExpression());
                    break;
                case 3:
                    expressionParts.add(new MonthField(splitExpression[i]).parseExpression());
                    break;
                case 4:
                    expressionParts.add(new DayOfWeekField(splitExpression[i]).parseExpression());
                    break;
                case 5:
                    expressionParts.add(new CommandField(splitExpression[i]));
                    break;
            }
        }
        return expressionParts;
    }

    public void toDisplay() {
        expressionParts.stream().map(ExpressionPart::toDisplayString).forEach(System.out::println);
    }
}
