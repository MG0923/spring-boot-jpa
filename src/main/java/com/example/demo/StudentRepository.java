package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query(value = "select * from Student where year=?1 ", nativeQuery = true)
    List<Student> getAllStudentByYear(String year);

}
