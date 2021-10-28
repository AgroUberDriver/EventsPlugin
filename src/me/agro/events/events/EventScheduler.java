package me.agro.events.events;

import me.agro.events.Main;
import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class EventScheduler {

    private static int hours;
    public static int minutes;
    private Main plugin = Main.getInstance();


    public static String getTime(String event) {


        int hour = 16;

        LocalDateTime d1 = LocalDateTime.now();

        Calendar cal = Calendar.getInstance();

        if (event.equalsIgnoreCase("Spleef")) {

            if (cal.get(Calendar.HOUR_OF_DAY) < 12) {
                hour = 12;
            } else if (cal.get(Calendar.HOUR_OF_DAY) >= 12 && cal.get(Calendar.HOUR_OF_DAY) < 16) {
                hour = 16;
            } else if (cal.get(Calendar.HOUR_OF_DAY) >= 16 && cal.get(Calendar.HOUR_OF_DAY) < 20) {
                hour = 20;
            } else if (cal.get(Calendar.HOUR_OF_DAY) >= 20 && cal.get(Calendar.HOUR_OF_DAY) < 24) {
                hour = 24;
            }


            int CurrentHour = cal.get(Calendar.HOUR_OF_DAY);
            int CurrentMinute = cal.get(Calendar.MINUTE);

            hours = hour - CurrentHour;

            minutes = 60 - CurrentMinute;



        } else if(event.equalsIgnoreCase("LMS")) {
            if (cal.get(Calendar.HOUR_OF_DAY) < 8) {
                hour = 14;
            } else if (cal.get(Calendar.HOUR_OF_DAY) >= 14 && cal.get(Calendar.HOUR_OF_DAY) < 18) {
                hour = 18;
            } else if (cal.get(Calendar.HOUR_OF_DAY) >= 18 && cal.get(Calendar.HOUR_OF_DAY) < 22) {
                hour = 22;
            } else if (cal.get(Calendar.HOUR_OF_DAY) >= 24) {
                hour = 24;
            }


            int CurrentHour = cal.get(Calendar.HOUR_OF_DAY);
            int CurrentMinute = cal.get(Calendar.MINUTE);

            hours = hour - CurrentHour;

            minutes = 60 - CurrentMinute;

        }
        return ("&7Time until next: &f" + hours + " hours " + minutes + " minutes");

    }



    }