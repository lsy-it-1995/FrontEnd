package com.company.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateTimeExample {

    public static void localDateExample(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDate one = localDate.of(2020, 3, 8);
        System.out.println(one);
        LocalDate two = LocalDate.ofYearDay(2020, 54);
        System.out.println(two);
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.get(ChronoField.DAY_OF_MONTH));
        System.out.println(localDate.plusDays(21));
        System.out.println(localDate.plusMonths(2));
        System.out.println(localDate.minusDays(13));
        System.out.println(localDate.withYear(2000));
        System.out.println(localDate.with(ChronoField.YEAR, 2018));
        System.out.println(localDate.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(localDate.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println(localDate.minus(1, ChronoUnit.YEARS));
        System.out.println(localDate.isLeapYear());
        System.out.println(LocalDate.ofYearDay(2020, 1).isLeapYear());
        System.out.println(localDate.isEqual(one));
        System.out.println(localDate.isBefore(one));
        System.out.println(localDate.isAfter(one));
        System.out.println(localDate.minus(1, ChronoUnit.MONTHS));
        System.out.println(localDate.isSupported(ChronoUnit.HOURS));
        System.out.println(localDate.isSupported(ChronoUnit.DAYS));

    }
    public static void localTimeExample(){
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        System.out.println(LocalTime.of(3,4));
        System.out.println(LocalTime.of(1,2,3));
        System.out.println(LocalTime.of(1,2,3, 4));
        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());
        System.out.println(localTime.get(ChronoField.CLOCK_HOUR_OF_DAY));
        System.out.println(localTime.toSecondOfDay());
        System.out.println(localTime.minusHours(2));
        System.out.println(localTime.minus(2, ChronoUnit.HOURS));
        System.out.println(localTime.with(LocalTime.MIDNIGHT));
        System.out.println(localTime);
        System.out.println(localTime.with(ChronoField.HOUR_OF_DAY, 4));
        System.out.println(localTime.withHour(7));
    }
    public static void main(String[] args) {
//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate);
//        LocalTime localTime = LocalTime.now();
//        System.out.println(localTime);
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime);
//        localDateExample();
        localTimeExample();
    }
}
