package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/students")
    public String insert(@RequestBody Student student){

        studentRepository.save(student);

        return "Create";
    }

    @PutMapping("/students/{studentId}")
    public String update(@PathVariable Integer studentId,
                         @RequestBody Student student){

        Student s = studentRepository.findById(studentId).orElse(null);
        if (s!=null){
            s.setName(student.getName());
            studentRepository.save(s);
            return "Update";
        }else {
            return "數據不存在，無法更新";
        }

        /*student.setId(studentId);
        studentRepository.save(student);

        return "Update";*/
    }

    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable Integer studentId){

        studentRepository.deleteById(studentId);

        return "Delete";
    }

    @GetMapping("/students/{studentId}")
    public Student read(@PathVariable Integer studentId){

        Student student = studentRepository.findById(studentId).orElse(null);

        return student;
    }
}
