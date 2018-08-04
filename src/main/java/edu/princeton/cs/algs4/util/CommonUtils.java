package edu.princeton.cs.algs4.util;

/**
 * @author zhiyingyang
 * @version 2018-08-04 21:11
 */
public class CommonUtils {
    public static String[] splitStrInChars(String testCasesInStr) {
        String[] result = new String[testCasesInStr.length()];
        char[] testCasesInChars = testCasesInStr.toCharArray();
        for (int i = 0; i < testCasesInStr.length(); i++) {
            result[i] = String.valueOf(testCasesInChars[i]);
        }
        return result;
    }
}
