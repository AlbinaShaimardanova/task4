package org.example.check;

import org.example.logger.LogTransformation;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@LogTransformation("date_log.txt")
public class CheckDate {
    public Date check(Date date){
        if (date !=null) return date;
        return date;
    }
}
