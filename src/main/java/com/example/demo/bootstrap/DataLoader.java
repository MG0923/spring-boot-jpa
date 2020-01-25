package com.example.demo.bootstrap;

import com.example.demo.Student;
import com.example.demo.StudentRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final StudentRepository repository;


    public DataLoader(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Student student_a = new Student();
        student_a.setFirstName("Amit");
        student_a.setLastName("Sharma");
        student_a.setYear("Junior");
        repository.save(student_a);

        Student student_b = new Student();
        student_b.setFirstName("Abhishek");
        student_b.setLastName("Gupta");
        student_b.setYear("Senior");
        repository.save(student_b);

        Student student_c = new Student();
        student_c.setFirstName("Jason");
        student_c.setLastName("Roy");
        student_c.setYear("Freshman");
        repository.save(student_c);
    }
}
