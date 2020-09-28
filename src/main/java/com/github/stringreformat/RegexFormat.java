package com.github.stringreformat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.stringreformat.exceptions.MatchNotFoundException;

/**
 * FormatString offers the possibility to reformat a provided string using a regex and an inject pattern.
 *
 * @author brejjan
 *
 */
public class RegexFormat {

    public RegexFormat() {
        throw new ExceptionInInitializerError("Class should only be used in a static way.");
    }

    /**
     * Reformats first occurrence that the regex matches according to the provided
     * injectPattern.
     *
     * Example:
     * String: "This is a date and time 20200918-235917 and another date 19570320-115247"
     * Regex: "[0-9]{8}-[0-9]{6}"   -->   This matches 20200918-235917
     * Inject pattern: "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14"   -->   Note $8 not used that represent "-"
     * Result in string: "This is a date and time 2020-09-18 23:59:17 and another date 19570320-115247"
     *
     * @param string        :: Provided string to reformat
     * @param regexPattern  :: Regex to find part of string
     * @param injectPattern :: Inject pattern as string
     * @return
     * @throws MatchNotFoundException
     */
    public static String reformat(final String string, final String regexPattern, final String injectPattern)
        throws MatchNotFoundException {
        final Matcher matcher = prepareMatcher(regexPattern, string);

        matcher.find();

        final String reformattedString = replaceMatchedWithInjectPatternValues(string, injectPattern, matcher);

        return reformattedString;
    }

    /**
     * Reformats all occurrence that the regex matches according to the provided
     * injectPattern.
     *
     * Example:
     * String: "This is a date and time 20200918-235917 and another date 19570320-115247"
     * Regex: "[0-9]{8}-[0-9]{6}"    -->  This matches 20200918-235917
     * Inject pattern: "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14"   -->   Note $8 not used that represent "-"
     * Result in string: "This is a date and time 2020-09-18 23:59:17 and another date 1957-03-20 11:52:47"
     *
     * @param string        :: Provided string to reformat
     * @param regexPattern  :: Regex to find part of string
     * @param injectPattern :: Inject pattern as string
     * @return
     * @throws MatchNotFoundException
     */
    public static String reformatAll(final String string, final String regexPattern, final String injectPattern)
        throws MatchNotFoundException {
        String reformattedString = string;
        final Matcher matcher = prepareMatcher(regexPattern, string);

        while (matcher.find()) {
            reformattedString = replaceMatchedWithInjectPatternValues(reformattedString, injectPattern, matcher);
        }

        return reformattedString;
    }

    /**
     * Checks if a given regex has a match in the provided string.
     *
     * @param string    :: Provided string to reformat
     * @param regex     :: Regex to find part of string
     * @return          :: boolean
     */
    public static boolean hasMatch(final String string, final String regexPattern) {
        try {
            prepareMatcher(regexPattern, string);
            return true;
        } catch (final MatchNotFoundException e) {
            return false;
        }
    }

    private static Matcher prepareMatcher(final String regexPattern, final String string)
        throws MatchNotFoundException {
        final Pattern pattern = Pattern.compile(regexPattern);
        final Matcher matcher = pattern.matcher(string);

        if (!matcher.find()) {
            throw new MatchNotFoundException(String.format("Regex pattern '%s' was not found in String '%s'",
                regexPattern, string));
        }
        matcher.reset();
        return matcher;
    }

    private static String replaceMatchedWithInjectPatternValues(final String string, final String injectPattern,
        final Matcher matcher) {
        final String match = matcher.group();
        String reformattedString = string.replaceAll("\\$", "_dollar_sign_").replace(match, injectPattern);
        final String[] chars = match.split("");
        for (int i = chars.length - 1; i >= 0; i--) {
            final String index = "$" + String.valueOf(i);
            final String replaceWith = chars[i];
            reformattedString = reformattedString.replace(index, replaceWith);
        }
        return reformattedString.replaceAll("\\$[0-9]{0,9}", "").replaceAll("_dollar_sign_", "$");
    }

}
