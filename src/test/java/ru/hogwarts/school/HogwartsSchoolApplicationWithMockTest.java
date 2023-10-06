package ru.hogwarts.school;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.mapper.FacultyMapper;
import ru.hogwarts.school.mapper.StudentMapper;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Optional;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.hogwarts.school.constants.HogwartsSchoolApplicationTestsConstants.*;
import static ru.hogwarts.school.constants.HogwartsSchoolApplicationWithMockTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
public class HogwartsSchoolApplicationWithMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private FacultyService facultyService;
    @SpyBean
    private AvatarService avatarService;
    @SpyBean
    private StudentService studentService;
    @SpyBean
    private FacultyMapper facultyMapper;
    @SpyBean
    private StudentMapper studentMapper;

    @InjectMocks
    private FacultyController facultyController;


    @Test
    void testFacultyControllerCreateFaculty() throws Exception {

        JSONObject facultyObject = convertFacultyDtoInToJson.apply(FACULTY_DTO_IN_1);
        Faculty faculty = convertFacultyDtoOutToFaculty.apply(FACULTY_DTO_OUT_1);

        when(facultyRepository.save(eq(faculty))).thenReturn(faculty);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/faculty")
                                .content(facultyObject.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(FACULTY_ID_1))
                .andExpect(jsonPath("$.name").value(FACULTY_NAME_1))
                .andExpect(jsonPath("$.color").value(FACULTY_COLOR_1));
    }

    @Test
    void testFacultyControllerGetFaculties() throws Exception {

        when(facultyRepository.findAll()).thenReturn(FACULTY_LIST);
        when(facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(any(String.class), any(String.class))).thenReturn(FACULTY_LIST_COLOR);
        String expectedAllFaculties = FACULTY_LIST.stream()
                .map(Faculty::toString)
                .toList().toString().replaceAll(" ", "");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/faculty")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertEquals(expectedAllFaculties, result.getResponse().getContentAsString());
                });

        String expectedFacultiesByColor = FACULTY_LIST_COLOR.stream()
                .map(Faculty::toString)
                .toList().toString().replaceAll(" ", "");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/faculty?colorOrName=" + FACULTY_COLOR_1)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertEquals(expectedFacultiesByColor, result.getResponse().getContentAsString());
                });
    }

    @Test
    void testFacultyControllerGetFaculty() throws Exception {

        when(facultyRepository.findById(eq(FACULTY_ID_2))).thenReturn(Optional.of(FACULTY_2));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/faculty/" + FACULTY_ID_2)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(FACULTY_ID_2))
                .andExpect(jsonPath("$.name").value(FACULTY_NAME_2))
                .andExpect(jsonPath("$.color").value(FACULTY_COLOR_2));
    }

    @Test
    void testFacultyControllerGetStudents() throws Exception {

        String expected = STUDENT_LIST_FROM_FACULTY.stream()
                .map(Student::toString)
                .toList().toString().replaceAll(" ", "");

        when(studentRepository.findByFaculty_Id(FACULTY_ID_1)).thenReturn(STUDENT_LIST_FROM_FACULTY);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/faculty/" + FACULTY_ID_1 + "/students")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertEquals(expected, result.getResponse().getContentAsString());
                });
    }

    @Test
    void testFacultyControllerEditFaculty() {

    }


    private final Function<FacultyDtoIn, JSONObject> convertFacultyDtoInToJson = (x) -> {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", x.getName());
            jsonObject.put("color", x.getColor());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    };

    private final Function<FacultyDtoOut, Faculty> convertFacultyDtoOutToFaculty = (x) -> {
        Faculty faculty = new Faculty();

        faculty.setId(x.getId());
        faculty.setName(x.getName());
        faculty.setColor(x.getColor());

        return faculty;
    };
}
