package org.example.check;

import org.example.logger.LogTransformation;
import org.springframework.stereotype.Component;

@Component
@LogTransformation("user_log.txt")
public class CheckFIO {
    public String check(String name) {
        if (name.isEmpty())
            return "";
        String capitalized = name.substring(0, 1).toUpperCase() + name.substring(1);
        return capitalized;
    }
}
