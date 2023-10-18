package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper studentMapper;
    private final FacultyMapper facultyMapper;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository, StudentMapper studentMapper, FacultyMapper facultyMapper) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.studentMapper = studentMapper;
        this.facultyMapper = facultyMapper;
    }


    public StudentDtoOut createStudent(StudentDtoIn studentDtoIn) {
        logger.info("Was invoked createStudent method");
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(studentDtoIn)));
    }

    public StudentDtoOut getStudent(Long id) {
        logger.info("Was invoked getStudent method");
        return studentMapper.toDto(studentRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("Student with id = %d not found!", id));
            return new StudentNotFoundException(id);
        }));
    }

    public Collection<StudentDtoOut> getAllStudents() {
        logger.info("Was invoked getAllStudents method");
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .toList();
    }

    public StudentDtoOut editStudent(Long id, StudentDtoIn studentDtoIn) {
        logger.info("Was invoked editStudent method");
        Student oldStudent = studentRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("Student with id = %d not found!", id));
            return new StudentNotFoundException(id);
        });
        oldStudent.setName(studentDtoIn.getName());
        oldStudent.setAge(studentDtoIn.getAge());
        oldStudent.setFaculty(facultyRepository.findById(studentDtoIn.getFacultyId())
                .orElseThrow(() -> {
                    logger.error(String.format("Faculty with id = %d not found!", id));
                    return new FacultyNotFoundException(studentDtoIn.getFacultyId());
                }));
        return studentMapper.toDto(studentRepository.save(oldStudent));
    }

    public StudentDtoOut removeStudent(Long id) {
        logger.info("Was invoked removeStudent method");
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("Student with id = %d not found!", id));
            return new StudentNotFoundException(id);
        });
        studentRepository.delete(student);
        return studentMapper.toDto(student);
    }


    public Collection<StudentDtoOut> getStudentsByAgeBetween(int minAge, int maxAge) {
        logger.info("Was invoked getStudentsByAgeBetween method");
        return studentRepository.findByAgeBetween(minAge, maxAge).stream()
                .map(studentMapper::toDto)
                .toList();
    }

    public FacultyDtoOut getFacultyByStudentId(Long id) {
        logger.info("Was invoked getFacultyByStudentId method");
        return facultyMapper.toDto(studentRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("Student with id = %d not found!", id));
            return new StudentNotFoundException(id);
        }).getFaculty());
    }

    public Long calculateNumberOfAllStudents() {
        logger.info("Was invoked calculateNumberOfAllStudents method");
        return studentRepository.countAllStudents();
    }

    public Double calculateAverageAge() {
        logger.info("Was invoked calculateAverageAge method");
        return studentRepository.averageAge();
    }

    public Collection<Student> getLastFiveStudents() {
        logger.info("Was invoked getLastFiveStudents method");
        return studentRepository.getLastFiveStudents();
    }
}
