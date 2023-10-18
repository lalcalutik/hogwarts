package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

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
        logger.info("Was invoked createFaculty method");
        return facultyMapper.toDto(facultyRepository.save(facultyMapper.toEntity(facultyDtoIn)));
    }

    public FacultyDtoOut getFaculty(Long id) {
        logger.info("Was invoked getFaculty method");
        return facultyMapper.toDto(facultyRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("Faculty with id = %d not found!", id));
            return new FacultyNotFoundException(id);
        }));
    }

    public Collection<FacultyDtoOut> getAllFaculties() {
        logger.info("Was invoked getAllFaculties method");
        return facultyRepository.findAll().stream()
                .map(facultyMapper::toDto)
                .toList();
    }

    public FacultyDtoOut editFaculty(Long id, FacultyDtoIn facultyDtoIn) {
        logger.info("Was invoked editFaculty method");
        Faculty oldFaculty = facultyRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("Faculty with id = %d not found!", id));
            return new FacultyNotFoundException(id);
        });
        oldFaculty.setName(facultyDtoIn.getName());
        oldFaculty.setColor(facultyDtoIn.getColor());
        return facultyMapper.toDto(facultyRepository.save(oldFaculty));
    }

    public FacultyDtoOut removeFaculty(Long id) {
        logger.info("Was invoked removeFaculty method");
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("Faculty with id = %d not found!", id));
            return new FacultyNotFoundException(id);
        });
        facultyRepository.delete(faculty);
        return facultyMapper.toDto(faculty);
    }

    public Collection<FacultyDtoOut> getFacultiesByColorOrName(String colorOrName) {
        logger.info("Was invoked getFacultiesByColorOrName method");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName).stream()
                .map(facultyMapper::toDto)
                .toList();
    }

    public Collection<StudentDtoOut> getStudents(Long id) {
        logger.info("Was invoked getStudents method");
        return studentRepository.findByFaculty_Id(id).stream()
                .map(studentMapper::toDto)
                .toList();
    }
}
