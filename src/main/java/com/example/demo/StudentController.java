package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")//, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Student> getAllStudent()
    {
        //System.out.println(repository.getAllStudentByYear("Junior"));
        return repository.findAll();
    }
    @GetMapping("{id}")
    public Student getStudent(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(StudentNotFoundException::new);
    }
    @RequestMapping(value = "/byYear",method = RequestMethod.GET)
    public List<Student> getAllStudentByYear(@RequestParam(value="year") String year)
    {
        List<Student> listStudent = repository.getAllStudentByYear(year);
        if(listStudent.isEmpty())
            throw new StudentNotFoundException();
        else
            return listStudent;
    }
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }
    @PutMapping("{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student studentToUpdate = repository.findById(id).orElseThrow(StudentNotFoundException::new);

        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        studentToUpdate.setYear(student.getYear());
        return repository.save(studentToUpdate);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        repository.findById(id).orElseThrow(StudentNotFoundException::new);
        repository.deleteById(id);
    }

}

