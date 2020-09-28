package com.github.stringreformat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.stringreformat.exceptions.MatchNotFoundException;

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
    public void testReformatRevert() throws Throwable {
        final String string = "20120210_125954";
        final String expectedString = "459521_01202102";
        final String regex = "[0-9]{8}_[0-9]{6}";
        final String pattern = "$14$13$12$11$10$9$8$7$6$5$4$3$2$1$0";
        final String reformattedString = RegexFormat.reformat(string, regex, pattern);
        assertEquals(expectedString, reformattedString);
    }

    @Test
    public void testReformatText() throws Throwable {
        final String string = "Reformat this asap.";
        final String expectedString = "Reformat this a.s.a.p.";
        final String regex = "asap";
        final String pattern = "$0.$1.$2.$3";
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

    @Test
    public void testReformatAllTextMultipleWords() throws Throwable {
        final String string = "Reformat this asap.";
        final String expectedString = "Reformat t.h.i.s a.s.a.p.";
        final String regex = "asap|this";
        final String pattern = "$0.$1.$2.$3";
        final String reformattedString = RegexFormat.reformatAll(string, regex, pattern);
        assertEquals(expectedString, reformattedString);
    }

    @Test
    public void testReformatAllTextMultipleWordsSomeOutOfBounds() throws Throwable {
        final String string = "Reformat this asap.";
        final String expectedString = "tamrofeR siht pasa.";
        final String regex = "Reformat|this|asap";
        final String pattern = "$7$6$5$4$3$2$1$0";
        final String reformattedString = RegexFormat.reformatAll(string, regex, pattern);
        assertEquals(expectedString, reformattedString);
    }

    @Test(expected=MatchNotFoundException.class)
    public void testNoMatchException() throws MatchNotFoundException {
        final String string = "Reformat this asap.";
        final String regex = "[0-9]";
        final String pattern = "";
        RegexFormat.reformat(string, regex, pattern);
    }

    @Test(expected=ExceptionInInitializerError.class)
    public void testInitException() throws MatchNotFoundException {
        new RegexFormat();
    }

    @Test
    public void testHasMatchTrue() throws Throwable {
        final String string = "20120210_125954";
        final String regex = "[0-9]{8}_[0-9]{6}";
        assertTrue(RegexFormat.hasMatch(string, regex));
    }

    @Test
    public void testHasMatchFalse() throws MatchNotFoundException {
        final String string = "Reformat this asap.";
        final String regex = "[0-9]";
        assertFalse(RegexFormat.hasMatch(string, regex));
    }
}
