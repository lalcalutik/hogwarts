package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
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

    @GetMapping("count")
    public Long getNumberOfAllStudents() {
        return studentService.calculateNumberOfAllStudents();
    }

    @GetMapping("average-age")
    public Double getAverageAgeOfStudents() {
        return studentService.calculateAverageAge();
    }

    @GetMapping("five-last")
    public Collection<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }

    @GetMapping("name-start-with-a")
    public Collection<String> getStudentsWhereNameStartsWithA() {
        return studentService.getStudentsWhereNameStartsWithA();
    }

    @GetMapping("average-age2")
    public Double getAverageAgeOfStudents2() {
        return studentService.calculateAverageAge2();
    }

    @GetMapping("threads-task-1")
    public void printStudents() {
        studentService.printStudents();
    }

    @GetMapping("threads-task-2")
    public void printStudentsSync() {
        studentService.printStudentsSync();
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
