package com.github.stringreformat;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestFormatString {

    @Test
    public void testReformatDates() throws Throwable {
        final String string = "20120210_125954";
        final String expectedString = "2012-02-10 12:59:54";
        final String regex = "[0-9]{8}_[0-9]{6}";
        final String pattern = "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14";
        final String reformattedString = RegexFormat.reformat(string, regex, pattern);
        assertEquals(expectedString, reformattedString);
    }

    @Test
    public void testReformatMultipleDates() throws Throwable {
        final String string = "20120210_125954, 19561122_223344";
        final String expectedString = "2012-02-10 12:59:54, 19561122_223344";
        final String regex = "[0-9]{8}_[0-9]{6}";
        final String pattern = "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14";
        final String reformattedString = RegexFormat.reformat(string, regex, pattern);
        assertEquals(expectedString, reformattedString);
    }

    @Test
    public void testReformatDateInText() throws Throwable {
        final String string = "This is some text [20120210_125954] that contains a date!";
        final String expectedString = "This is some text [2012-02-10 12:59:54] that contains a date!";
        final String regex = "[0-9]{8}_[0-9]{6}";
        final String pattern = "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14";
        final String reformattedString = RegexFormat.reformat(string, regex, pattern);
        assertEquals(expectedString, reformattedString);
    }

    @Test
    public void testReformatAllDates() throws Throwable {
        final String string = "20120210_125954";
        final String expectedString = "2012-02-10 12:59:54";
        final String regex = "[0-9]{8}_[0-9]{6}";
        final String pattern = "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14";
        final String reformattedString = RegexFormat.reformatAll(string, regex, pattern);
        assertEquals(expectedString, reformattedString);
    }

    @Test
    public void testReformatAllMultipleDates() throws Throwable {
        final String string = "20120210_125954, 19561122_223344";
        final String expectedString = "2012-02-10 12:59:54, 1956-11-22 22:33:44";
        final String regex = "[0-9]{8}_[0-9]{6}";
        final String pattern = "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14";
        final String reformattedString = RegexFormat.reformatAll(string, regex, pattern);
        assertEquals(expectedString, reformattedString);
    }

    @Test
    public void testReformatAllDateInText() throws Throwable {
        final String string = "This is some text [20120210_125954] that contains a date!";
        final String expectedString = "This is some text [2012-02-10 12:59:54] that contains a date!";
        final String regex = "[0-9]{8}_[0-9]{6}";
        final String pattern = "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14";
        final String reformattedString = RegexFormat.reformatAll(string, regex, pattern);
        assertEquals(expectedString, reformattedString);
    }
}
