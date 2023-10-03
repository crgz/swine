package com.github.crgz.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matches {
    public static List<String> get(String regex, String s) {
        List<String> items = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(s);
        while (matcher.find()) {
            items.add(matcher.group(1));
        }
        return items;
    }
}