package org.example.maincomponents;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.List;

@Component
public class DataReader {
    public HashMap<String, List<CSVRecord>> get(String pathname) throws IOException {

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setDelimiter(";")
                .build();

        var map = new HashMap<String, List<CSVRecord>>();

        File currentDir = new File(pathname);
        File[] files = currentDir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                Reader in = new FileReader(file);
                var rec = csvFormat.parse(in).getRecords();
                map.put(file.getName(), rec);
            }
        }
        return map;
    }
}

