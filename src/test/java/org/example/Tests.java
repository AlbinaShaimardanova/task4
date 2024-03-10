package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.check.CheckApp;
import org.example.check.CheckDate;
import org.example.check.CheckFIO;
import org.example.dto.Users;
import org.example.maincomponents.DataReader;
import org.example.maincomponents.WriterDB;
import org.example.repo.LoginRepo;
import org.example.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class Tests {

    @Autowired
    String directory;
    @Autowired
    DataReader dataReader;
    @Autowired
    WriterDB writerDB;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CheckFIO checkFIO;
    @Autowired
    LoginRepo loginRepo;
    @Autowired
    CheckApp checkApp;
    @Autowired
    CheckDate checkDate;

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
            .setDelimiter(";")
            .build();
    private final SimpleDateFormat dformat = new SimpleDateFormat("dd.MM.yyyy");

    @Test
    public void testDataReader() throws IOException {
        var data = new HashMap<String, List<CSVRecord>>();
        String pathname ="src\\test\\resources\\files";
        data = dataReader.get(pathname);

        Assertions.assertEquals(2, data.size());

        var i = 0;
        for (var entry : data.entrySet()) {
            i += entry.getValue().size();
        }

        Assertions.assertEquals(7, i);
    }

    @Test
    public void testWriterDB() throws IOException {
        var map = new HashMap<String, List<CSVRecord>>();
        List<CSVRecord> lst= new ArrayList<>();
        CSVRecord record = CSVParser.parse("Login1;Marcus;Tullius;Cicero;12.12.2023;web", csvFormat).getRecords().get(0);
        lst.add(record);
        map.put("test1.cvs", lst);

        writerDB.write(map);

        Users user = userRepo.findByUsername("Login1");
        assertThat(user).isNotNull();
    }

    @Test
    public void testCheckFIO() throws IOException {
        CSVRecord record = CSVParser.parse("Login5;lucius;Annaeus;Seneca;12.12.2023;web", csvFormat).getRecords().get(0);

        String surname = checkFIO.check(record.get(1));
        Assertions.assertEquals("Lucius", surname);
    }

    @Test
    public void testCheckApp() throws IOException {
        CSVRecord record = CSVParser.parse("Login3;Marcus;Antonius;;01.12.2023;app", csvFormat).getRecords().get(0);

        String app = checkApp.check(record.get(5));
        Assertions.assertEquals("other:app", app);
    }

    @Test
    public void testCheckDateUserNotAddDB() throws IOException {
        var map = new HashMap<String, List<CSVRecord>>();
        List<CSVRecord> lst= new ArrayList<>();
        CSVRecord record = CSVParser.parse("Login6;Gaius;Iulius;Caesar;;mobile", csvFormat).getRecords().get(0);
        lst.add(record);
        map.put("test1.cvs", lst);

        writerDB.write(map);
        Users user = userRepo.findByUsername("Login6");
        assertThat(user).isNull();
    }

    @Test
    public void testCheckDate() throws IOException, ParseException {
        CSVRecord record = CSVParser.parse("Login6;Gaius;Iulius;Caesar;;mobile", csvFormat).getRecords().get(0);
        var rec = record.get(4);
        Boolean bGood = checkDate.check(rec);
        Assertions.assertEquals(false, bGood);
    }
}
