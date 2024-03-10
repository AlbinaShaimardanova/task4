package org.example.maincomponents;

import org.apache.commons.csv.CSVRecord;
import org.example.check.CheckApp;
import org.example.check.CheckDate;
import org.example.check.CheckFIO;
import org.example.dto.Logins;
import org.example.dto.Users;
import org.example.logger.Logger;
import org.example.repo.LoginRepo;
import org.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
    private final CheckDate checkDate;

    private final Logger logger;
    private final SimpleDateFormat dformat = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    public WriterDB(UserRepo userRepo, CheckFIO checkFIO, LoginRepo loginRepo, CheckApp checkApp, CheckDate checkDate, Logger logger) {
        this.userRepo = userRepo;
        this.checkFIO = checkFIO;
        this.loginRepo = loginRepo;
        this.checkApp = checkApp;
        this.checkDate = checkDate;
        this.logger = logger;
    }

    public void write(HashMap<String, List<CSVRecord>> map) {
        for (var unitMap : map.entrySet()) {
            String fileName = unitMap.getKey();
            List<CSVRecord> csvRecords = unitMap.getValue();
            Boolean bGood = false;

            for (CSVRecord csvRecord : csvRecords) {
                String username = csvRecord.get(0);
                String surname = csvRecord.get(1);
                String name = csvRecord.get(2);
                String patronomyc = csvRecord.get(3);


                Users user = userRepo.findByUsername(username);
                if (user == null) {
                    user = new Users();
                    user.setUsername(username);
                    user.setFio(checkFIO.check(surname) + " " + checkFIO.check(name) + " " + checkFIO.check(patronomyc));
                }

                Logins login = new Logins();
                try {
                    login.setAccess_date(dformat.parse(csvRecord.get(4)));
                    bGood = checkDate.check(login.getAccess_date());
                } catch (ParseException e) {
                }
                login.setApplication(checkApp.check(csvRecord.get(5)));

                if (bGood == true) {
                    userRepo.save(user);
                    login.setUser_id(user.getId());
                    loginRepo.save(login);

                }else{
                    try {
                        logger.write(fileName, user);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
