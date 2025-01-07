package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regular {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\d{6,}");
        Matcher matcher = pattern.matcher("0123456");
        System.out.println("Input String matches regex - " + matcher.matches());
    }
}
