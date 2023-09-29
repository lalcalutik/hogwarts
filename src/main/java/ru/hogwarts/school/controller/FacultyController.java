package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @PostMapping
    public FacultyDtoOut createFaculty(@RequestBody FacultyDtoIn facultyDtoIn) {
        return facultyService.createFaculty(facultyDtoIn);
    }

    @GetMapping
    public Collection<FacultyDtoOut> getFaculties(@RequestParam(required = false) String colorOrName) {
        if (colorOrName != null && !colorOrName.isBlank()) {
            return facultyService.getFacultiesByColorOrName(colorOrName);
        }
        return facultyService.getAllFaculties();
    }

    @GetMapping("{id}")
    public FacultyDtoOut getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @GetMapping("{id}/students")
    public Collection<StudentDtoOut> getStudents(@PathVariable Long id) {
        return facultyService.getStudents(id);
    }

    @PutMapping("{id}")
    public FacultyDtoOut editFaculty(@PathVariable Long id, @RequestBody FacultyDtoIn facultyDtoIn) {
        return facultyService.editFaculty(id, facultyDtoIn);
    }

    @DeleteMapping("{id}")
    public FacultyDtoOut removeFaculty(@PathVariable Long id) {
        return facultyService.removeFaculty(id);
    }
}
