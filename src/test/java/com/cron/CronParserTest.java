package com.cron;


import com.cron.fields.ExpressionPart;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

public class CronParserTest {


    @Test
    public void testParser_ValidExpression() {
        CronParser parser = new CronParser();
        try {
            parser.parseExpression("*/15 0 1,15 * 1-5 /usr/bin/find");
            List<ExpressionPart> expressionParts = parser.getExpressionParts();
            Assert.assertTrue(expressionParts.size() == 6);

            Assert.assertEquals("minute        0 15 30 45", expressionParts.get(0).toDisplayString());
            Assert.assertEquals("hour          0", expressionParts.get(1).toDisplayString());
            Assert.assertEquals("day of month  1 15", expressionParts.get(2).toDisplayString());
            Assert.assertEquals("month         1 2 3 4 5 6 7 8 9 10 11 12", expressionParts.get(3).toDisplayString());
            Assert.assertEquals("day of week   1 2 3 4 5", expressionParts.get(4).toDisplayString());
            Assert.assertEquals("command       /usr/bin/find", expressionParts.get(5).toDisplayString());
        } catch (Exception e) {
            Assert.fail("Valid expression parsing failed...");
        }
    }


    @Test
    public void testParser_EmptyExpression() {
        CronParser parser = new CronParser();
        try {
            parser.parseExpression("");
            Assert.fail("Throw exception for empty expression failed...");
        } catch (Exception e) {
            Assert.assertNotNull(e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testParser_InvalidExpression() {
        CronParser parser = new CronParser();
        try {
            parser.parseExpression("*/15 0 1,15  1-5 /usr/bin/find");
            Assert.fail("Throw exception for invalid expression failed...");
        } catch (Exception e) {
            Assert.assertNotNull(e);
            Assert.assertTrue(e instanceof ParseException);
        }
    }
}
