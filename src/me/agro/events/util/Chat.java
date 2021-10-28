package me.agro.events.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Chat {

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static List<String> color(List<String> strings) {
        List<String> colors = new ArrayList<>();
        for (String s : strings)
            colors.add(color(s));
        return colors;
    }

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(color(message));
    }

    public static boolean isAlpha(String str) {
        return str.matches("[a-zA-Z]+");
    }

    public static boolean isAlphaWithNumber(String str) {
        return str.matches("[a-zA-Z0-9]+");
    }

    public static String trimList(List<String> list) {
        if (list.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        Iterator<String> i = list.iterator();
        if (i.hasNext()) {
            sb.append(i.next());
            while (i.hasNext())
                sb.append(',').append(i.next());
        }
        return sb.toString();
    }

    public static String[] trimList(String[] array, int startFrom) {
        List<String> trimd = Arrays.<String>asList(array).subList(startFrom, array.length);
        return trimd.<String>toArray(new String[trimd.size()]);
    }

    public static String intToString(int intt) {
        return String.valueOf(intt);
    }

}

