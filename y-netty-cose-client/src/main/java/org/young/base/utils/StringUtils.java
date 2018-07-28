package org.young.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Young
 * Date: 2018/7/27 0027
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class StringUtils {

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
