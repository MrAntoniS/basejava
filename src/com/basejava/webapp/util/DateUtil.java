package com.basejava.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {

    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String toStringDate(LocalDate date) {
        return date.equals(NOW) ? "" : date.toString();
    }

    public static String outputDates(LocalDate startDate, LocalDate finishDate) {
        if (DateUtil.toStringDate(startDate).equals("") && DateUtil.toStringDate(finishDate).equals("")) {
            return "";
        }
        if (DateUtil.toStringDate(finishDate).equals("")) {
            return " с " + DateUtil.toStringDate(startDate) + " по настоящее время";
        }
        return " с " + DateUtil.toStringDate(startDate) + " по " + DateUtil.toStringDate(finishDate);
    }

    public static LocalDate parse(String date) {
        if (date.equals("")) {
            return DateUtil.NOW;
        }
        return LocalDate.parse(date);
    }
}