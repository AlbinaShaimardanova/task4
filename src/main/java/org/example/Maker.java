package org.example;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class Maker {
    @Autowired
    String directory;
    @Autowired
    DataReader dataReader;
    @Autowired
    WriterDB writerDB;

    public Maker() {
    }

    public Maker(String directory) {
        this.directory = directory;
    }

    public void make() throws IOException {
        HashMap<String, List<CSVRecord>> model;
        model = dataReader.get(directory);
        writerDB.write(model);
    }
}
