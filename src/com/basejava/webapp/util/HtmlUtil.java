package com.basejava.webapp.util;

import com.basejava.webapp.model.Experience;

public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatDates(Experience experience) {
        return DateUtil.format(experience.getStartDate()) + " - " + DateUtil.format(experience.getFinishDate());
    }
}
