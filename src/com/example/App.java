package com.example;

import java.util.*;

public class App {

    public static void main(String[] args) {
        Map<String, String> params = parseArgs(args);
        String cmd = params.get("cmd");

        if (cmd == null) {
            System.out.println("Usage: --cmd=add|list|rm|count");
            return;
        }

        try {
            switch (cmd) {
                case "add":
                    String text = params.get("text");
                    if (text == null) {
                        System.out.println("Missing --text");
                        return;
                    }
                    long id = NotesStore.add(text);
                    System.out.println("Added #" + id);
                    break;

                case "list":
                    var notes = NotesStore.list();
                    if (notes.isEmpty()) {
                        System.out.println("(empty)");
                    } else {
                        notes.forEach(System.out::println);
                    }
                    break;

                case "rm":
                    long rid = Long.parseLong(params.get("id"));
                    if (!NotesStore.remove(rid)) {
                        System.out.println("Not found #" + rid);
                    }
                    break;

                case "count":
                    System.out.println(NotesStore.count());
                    break;

                default:
                    System.out.println("Unknown command");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (String a : args) {
            if (!a.startsWith("--")) continue;
            String[] p = a.substring(2).split("=", 2);
            map.put(p[0], p.length == 2 ? p[1].replace("\"", "") : "");
        }
        return map;
    }
}
