package com.cron.fields;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinuteField extends CronField {

    public MinuteField(String expression) {
        this.name = ExpressionPartName.MINUTE;
        this.expression = expression;
        this.startRange = 0;
        this.endRange = 59;
    }


    @Override
    protected List<Integer> generateAllValues() {
        return IntStream.rangeClosed(startRange, endRange).boxed().collect(Collectors.toList());
    }

    @Override
    protected List<Integer> generateSplitValues() {
        String[] expressions = expression.split("/");
        int value = Integer.parseInt(expressions[1]);
        return IntStream.rangeClosed(startRange, endRange).filter(num -> num % value == 0).boxed().collect(Collectors.toList());
    }

    @Override
    protected List<Integer> generateRangeValues() {
        String[] expressions = expression.split("-");
        return IntStream.rangeClosed(Integer.parseInt(expressions[0]), Integer.parseInt(expressions[1])).boxed().collect(Collectors.toList());
    }

    @Override
    protected List<Integer> generateDifferentValues() {
        return Arrays.stream(expression.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }
}
