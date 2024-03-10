package org.example.logger;

import org.example.dto.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class Logger {
    @Value("${log.file_path}")
    private String filePath;
    @Value("${log.file_name}")
    private String fileName;

    public void write(String fname, Users user) throws IOException {
        var buf = new BufferedWriter(new FileWriter(this.filePath + "\\" + this.fileName, true));

        buf.write("Errors: file=" + fname + ": username - " + user.getUsername() + ", fio - " + user.getFio());
        buf.newLine();
        buf.close();
    }
}
