package com.example.service;

import com.example.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    private final List<User> users = new ArrayList<>();

    @PostConstruct
    public void loadCsvData() throws IOException {
        ClassPathResource resource = new ClassPathResource("emp_record.csv");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean header = true;

            while ((line = reader.readLine()) != null) {
                // Skip header
                if (header) {
                    header = false;
                    continue;
                }

                String[] fields = line.split(",");

                users.add(new User(
                        Integer.parseInt(fields[0].substring(1)),
                        fields[1],
                        Integer.parseInt(fields[2]),
                        fields[3]
                ));
            }
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public ByteArrayInputStream generateCsv() {
        StringBuilder sb = new StringBuilder();
        sb.append("id,name,salary,dept\n");

        for (User user : users) {
            sb.append(user.getId()).append(",")
                    .append(user.getName()).append(",")
                    .append(user.getSalary()).append(",")
                    .append(user.getDept()).append("\n");
        }

        return new ByteArrayInputStream(
                sb.toString().getBytes(StandardCharsets.UTF_8)
        );
    }
}
