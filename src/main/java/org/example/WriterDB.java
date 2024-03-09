package org.example;

import org.apache.commons.csv.CSVRecord;
import org.example.check.CheckApp;
import org.example.check.CheckFIO;
import org.example.dto.Logins;
import org.example.dto.Users;
import org.example.repo.LoginRepo;
import org.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@Component
public class WriterDB {
    private final UserRepo userRepo;
    private final CheckFIO checkFIO;
    private final LoginRepo loginRepo;
    private final CheckApp checkApp;
    private final SimpleDateFormat dformat = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    public WriterDB(UserRepo userRepo, CheckFIO checkFIO, LoginRepo loginRepo, CheckApp checkApp) {
        this.userRepo = userRepo;
        this.checkFIO = checkFIO;
        this.loginRepo = loginRepo;
        this.checkApp = checkApp;
    }

    public void write(HashMap<String, List<CSVRecord>> map) {
        for (var unitMap : map.entrySet()) {
            //System.out.println(unitMap);
            String key = unitMap.getKey();
            List<CSVRecord> csvRecords = unitMap.getValue();

            for (CSVRecord csvRecord : csvRecords) {
                String username = csvRecord.get(0);
                String surname = csvRecord.get(1);
                String name = csvRecord.get(2);
                String patronomyc = csvRecord.get(3);
                //System.out.println(username);
                //UserRepo userRepo
                Users user = userRepo.findByUsername(username);
                //System.out.println("userRepo.findByUsername(" + username + "): " + user);
                if (user == null) {
                    user = new Users();
                    //user.getId();
                    user.setUsername(username);
                    user.setFio(checkFIO.check(surname) + " " + checkFIO.check(name) + " " + checkFIO.check(patronomyc));
                }

                Logins login = new Logins();
                try {
                    login.setAccess_date(dformat.parse(csvRecord.get(4)));
                } catch (ParseException e) {
                }
                login.setApplication(checkApp.check(csvRecord.get(5)));

                userRepo.save(user);
                login.setUser_id(user.getId());
                loginRepo.save(login);
            }
        }
    }
}
