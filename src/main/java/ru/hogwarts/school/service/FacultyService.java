package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.mapper.FacultyMapper;
import ru.hogwarts.school.mapper.StudentMapper;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private final FacultyMapper facultyMapper;
    private final StudentMapper studentMapper;

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository,
                          FacultyMapper facultyMapper,
                          StudentMapper studentMapper) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.facultyMapper = facultyMapper;
        this.studentMapper = studentMapper;
    }


    public FacultyDtoOut createFaculty(FacultyDtoIn facultyDtoIn) {
        return facultyMapper.toDto(facultyRepository.save(facultyMapper.toEntity(facultyDtoIn)));//TODO:exist check
    }

    public FacultyDtoOut getFaculty(Long id) {
        return facultyMapper.toDto(facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id)));
    }

    public Collection<FacultyDtoOut> getAllFaculties() {
        return facultyRepository.findAll().stream()
                .map(facultyMapper::toDto)
                .toList();
    }

    public FacultyDtoOut editFaculty(Long id, FacultyDtoIn facultyDtoIn) {
        Faculty oldFaculty = facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
        oldFaculty.setName(facultyDtoIn.getName());
        oldFaculty.setColor(facultyDtoIn.getColor());
        return facultyMapper.toDto(facultyRepository.save(oldFaculty));
    }

    public FacultyDtoOut removeFaculty(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
        facultyRepository.delete(faculty);
        return facultyMapper.toDto(faculty);
    }

    public Collection<FacultyDtoOut> getFacultiesByColorOrName(String colorOrName) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName).stream()
                .map(facultyMapper::toDto)
                .toList();
    }

    public Collection<StudentDtoOut> getStudents(Long id) {
        return studentRepository.findByFaculty_Id(id).stream()
                .map(studentMapper::toDto)
                .toList();
    }
}
