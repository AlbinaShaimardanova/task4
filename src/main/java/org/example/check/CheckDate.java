package org.example.check;

import org.example.dto.Users;
import org.example.logger.LogTransformation;
import org.example.logger.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@LogTransformation("date_log.txt")
public class CheckDate {
    Logger logger;
    public Boolean check(Object date) {
        if (date == "") {
            return false;
        }
        return true;
    }
}
