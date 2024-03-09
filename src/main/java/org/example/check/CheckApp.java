package org.example.check;

import org.example.logger.LogTransformation;
import org.springframework.stereotype.Component;

@Component
@LogTransformation("app_log.txt")
public class CheckApp {
    public String check(String app) {
        if (!"-web-mobile-".contains(app))
            return "other:" + app;
        return app;
    }
}
