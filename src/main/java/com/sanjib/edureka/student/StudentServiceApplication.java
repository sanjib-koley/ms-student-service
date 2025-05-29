package com.sanjib.edureka.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class StudentServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

}
