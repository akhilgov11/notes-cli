package com.example;

import java.io.*;
import java.util.*;

public class NotesStore {
    private static final String DATA_FILE = "data/notes.csv";

    public static synchronized long add(String text) throws IOException {
        File file = new File(DATA_FILE);
        file.getParentFile().mkdirs();

        long id = 1;
        if (file.exists()) {
            List<String> lines = readAll(file);
            if (!lines.isEmpty()) {
                String last = lines.get(lines.size() - 1);
                id = Long.parseLong(last.split(";")[0]) + 1;
            }
        }

        try (FileWriter w = new FileWriter(file, true)) {
            w.write(id + ";" + text + "\n");
        }
        return id;
    }

    public static List<String> list() throws IOException {
        File file = new File(DATA_FILE);
        if (!file.exists()) return Collections.emptyList();
        return readAll(file);
    }

    public static boolean remove(long id) throws IOException {
        File file = new File(DATA_FILE);
        if (!file.exists()) return false;

        List<String> lines = readAll(file);
        boolean found = false;

        try (FileWriter w = new FileWriter(file, false)) {
            for (String l : lines) {
                if (l.startsWith(id + ";")) {
                    found = true;
                } else {
                    w.write(l + "\n");
                }
            }
        }
        return found;
    }

    public static int count() throws IOException {
        return list().size();
    }

    private static List<String> readAll(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = r.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
