package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }


    @PostMapping
    public StudentDtoOut createStudent(@RequestBody StudentDtoIn studentDtoIn) {
        return studentService.createStudent(studentDtoIn);
    }

    @GetMapping
    public Collection<StudentDtoOut> getStudents(@RequestParam(required = false) Integer minAge,
                                                 @RequestParam(required = false) Integer maxAge) {
        if (minAge != null && maxAge != null) {
            return studentService.getStudentsByAgeBetween(minAge, maxAge);
        }
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public StudentDtoOut getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("{id}/faculty")
    public FacultyDtoOut getStudentFaculty(@PathVariable Long id) {
        return studentService.getFacultyByStudentId(id);
    }

    @PutMapping("{id}")
    public StudentDtoOut editStudent(@PathVariable Long id, @RequestBody StudentDtoIn studentDtoIn) {
        return studentService.editStudent(id, studentDtoIn);
    }

    @DeleteMapping("{id}")
    public StudentDtoOut removeStudent(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }
}
