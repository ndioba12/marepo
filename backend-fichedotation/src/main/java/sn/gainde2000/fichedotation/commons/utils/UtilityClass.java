/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :02/02/2022
 */

package sn.gainde2000.fichedotation.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UtilityClass {
    private static final String WORD_SEPARATOR = " ";

    private UtilityClass() {
    }

    public static Date removeTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date addTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date statistiqueDateDebut() {
        Calendar calendarDebut = Calendar.getInstance();
        calendarDebut.set(Calendar.YEAR, calendarDebut.get(Calendar.YEAR));
        calendarDebut.set(Calendar.MONTH, 0);
        calendarDebut.set(Calendar.DATE, 1);
        calendarDebut.set(Calendar.HOUR_OF_DAY, 0);
        calendarDebut.set(Calendar.MINUTE, 0);
        calendarDebut.set(Calendar.SECOND, 0);
        calendarDebut.set(Calendar.MILLISECOND, 0);
        return calendarDebut.getTime();
    }

    public static Date statistiqueDateFin() {
        Calendar calendarDebut = Calendar.getInstance();
        calendarDebut.set(Calendar.YEAR, calendarDebut.get(Calendar.YEAR));
        calendarDebut.set(Calendar.MONTH, 11);
        calendarDebut.set(Calendar.DATE, 31);
        calendarDebut.set(Calendar.HOUR_OF_DAY, 23);
        calendarDebut.set(Calendar.MINUTE, 59);
        calendarDebut.set(Calendar.SECOND, 59);
        calendarDebut.set(Calendar.MILLISECOND, 999);
        return calendarDebut.getTime();
    }

    public static String titleCaseConversion(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        return Arrays
                .stream(text.split(WORD_SEPARATOR))
                .map(word -> word.isEmpty()
                        ? word
                        : Character.toTitleCase(word.charAt(0)) + word
                        .substring(1)
                        .toLowerCase())
                .collect(Collectors.joining(WORD_SEPARATOR));
    }

    public static LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static String millisToDayHrMinSec(Double millis) {
        final long milliseconds = millis.longValue();
        final long dy = TimeUnit.MILLISECONDS.toDays(milliseconds);
        final long hr = TimeUnit.MILLISECONDS.toHours(milliseconds)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
        final long min = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));
        return String.format("%d Jours - %d:%d:%d", dy, hr, min, sec);
    }

    public static Long removeMilliseconds(Double millis) {
        final long milliseconds = millis.longValue();
        final long dy = TimeUnit.MILLISECONDS.toDays(milliseconds);
        final long hr = TimeUnit.MILLISECONDS.toHours(milliseconds)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
        final long min = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));
        return (TimeUnit.DAYS.toMillis(dy)) + (TimeUnit.HOURS.toMillis(hr)) + (TimeUnit.MINUTES.toMillis(min)) + (TimeUnit.SECONDS.toMillis(sec));
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (StringUtils.isEmpty(email)) return false;
        email = email.trim();
        if (!email.contains("@")) return false;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }
}
