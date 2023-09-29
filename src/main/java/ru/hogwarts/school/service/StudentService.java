package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.mapper.FacultyMapper;
import ru.hogwarts.school.mapper.StudentMapper;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper studentMapper;
    private final FacultyMapper facultyMapper;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository, StudentMapper studentMapper, FacultyMapper facultyMapper) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.studentMapper = studentMapper;
        this.facultyMapper = facultyMapper;
    }


    public StudentDtoOut createStudent(StudentDtoIn studentDtoIn) {
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(studentDtoIn)));//TODO:exist check
    }

    public StudentDtoOut getStudent(Long id) {
        return studentMapper.toDto(studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id)));
    }

    public Collection<StudentDtoOut> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .toList();
    }

    public StudentDtoOut editStudent(Long id, StudentDtoIn studentDtoIn) {
        Student oldStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        oldStudent.setName(studentDtoIn.getName());
        oldStudent.setAge(studentDtoIn.getAge());
        oldStudent.setFaculty(facultyRepository.findById(studentDtoIn.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException(studentDtoIn.getFacultyId())));
        return studentMapper.toDto(studentRepository.save(oldStudent));
    }

    public StudentDtoOut removeStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return studentMapper.toDto(student);
    }

/*    public Collection<StudentDtoOut> getStudentsByAge(int age) {
        return studentRepository.findByAge(age).stream()
                .map(studentMapper::toDto)
                .toList();
    }*/

    public Collection<StudentDtoOut> getStudentsByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge).stream()
                .map(studentMapper::toDto)
                .toList();
    }

    public FacultyDtoOut getFacultyByStudentId(Long id) {
        return facultyMapper.toDto(studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id)).getFaculty());
    }
}
