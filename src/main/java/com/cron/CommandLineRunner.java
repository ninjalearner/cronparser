package com.cron;

import java.text.ParseException;

public class CommandLineRunner {

    public static void main(String[] args) throws ParseException {
        if (args.length != 1) {
            System.out.println("Please provide the expression");
            return;
        }

        CronParser cronParser = new CronParser();
        cronParser.parseExpression(args[0]); //"* * */2 2 * /usr/bin/find"

        cronParser.toDisplay();
    }
}
