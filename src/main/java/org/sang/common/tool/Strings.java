package org.sang.common.tool;

public final class Strings {
    private Strings() {
    }

    public static boolean isBlank(String s) {
        return s == null || s.isEmpty();
    }

    public static String blankToNull(String s) {
        return isBlank(s) ? null : s;
    }

    public static String join(String[] parts, String joiner) {
        StringBuilder ss = new StringBuilder();
        String[] arr$ = parts;
        int len$ = parts.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String s = arr$[i$];
            if (ss.length() > 0) {
                ss.append(joiner);
            }

            ss.append(s == null ? "" : s);
        }

        return ss.toString();
    }
}
