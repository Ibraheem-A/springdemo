package com.example.springdemo.student;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudentByNameOrId(String studentNameOrId) {
        if (StringUtils.isNumeric(studentNameOrId)){
            Long studentId = Long.valueOf(studentNameOrId);
            deleteStudentById(studentId);
        } else {
            deleteStudentById(studentNameOrId);
        }
    }

    public void deleteStudentById(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);
        if (!studentExists){
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    private void deleteStudentById(String studentName) {
        boolean studentExists = studentRepository.existsByName(studentName);
        System.out.println(studentExists);
        if (!studentExists) {
            throw new IllegalStateException("student with name " + studentName + " does not exist");
        }
        Optional<Student> studentOptional = studentRepository.findStudentByName(studentName);
        studentOptional.ifPresent(studentRepository::delete);
    }

    @Transactional
    public void updateStudent(Long studentId, String studentEmail, String studentName) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("student with id " + studentId + " does not exist"));

        if (studentEmail != null && studentEmail.length() != 0 && !studentEmail.equals(student.getEmail())) {
            student.setEmail(studentEmail);
        }
        if (studentName != null && studentName.length() != 0 && !studentName.equals(student.getName())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setName(studentName);
        }
    }
}
