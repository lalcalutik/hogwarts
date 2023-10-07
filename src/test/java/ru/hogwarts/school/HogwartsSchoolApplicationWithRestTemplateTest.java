package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.AvatarController;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoOut;


import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static ru.hogwarts.school.constants.HogwartsSchoolApplicationWithRestTemplateConstants.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HogwartsSchoolApplicationWithRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private AvatarController avatarController;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeAll
    public void beforeAll() {
        restTemplate.postForObject("http://localhost:" + port + "/faculty", FACULTY_DTO_IN_1, FacultyDtoOut.class);
        restTemplate.postForObject("http://localhost:" + port + "/faculty", FACULTY_DTO_IN_2, FacultyDtoOut.class);
        restTemplate.postForObject("http://localhost:" + port + "/faculty", FACULTY_DTO_IN_3, FacultyDtoOut.class);

        restTemplate.postForObject("http://localhost:" + port + "/student", STUDENT_DTO_IN_1, StudentDtoOut.class);
        restTemplate.postForObject("http://localhost:" + port + "/student", STUDENT_DTO_IN_2, StudentDtoOut.class);
    }

    @Test
    void contextLoads() {
        assertNotNull(avatarController);
        assertNotNull(facultyController);
        assertNotNull(studentController);
    }

    @Test
    void testStudentControllerCreateStudent() {
        assertEquals(STUDENT_DTO_OUT_3, restTemplate.postForObject("http://localhost:" + port + "/student", STUDENT_DTO_IN_3, StudentDtoOut.class));
    }

    @Test
    void testStudentControllerGetStudents() {
        String expected = ALL_STUDENTS.toString();
        String actual = restTemplate.getForObject("http://localhost:" + port + "/student", Collection.class).toString();
        assertEquals(expected, actual);

        expected = ALL_STUDENTS_WITH_AGE_1.toString();
        actual = restTemplate.getForObject("http://localhost:" + port + "/student?minAge=" + STUDENT_AGE_1 + "&maxAge=" + STUDENT_AGE_1, Collection.class).toString();
        assertEquals(expected, actual);
    }

    @Test
    void testStudentControllerGetStudent() {
        assertEquals(STUDENT_DTO_OUT_1, restTemplate.getForObject("http://localhost:" + port + "/student/1", StudentDtoOut.class));
    }

    @Test
    void testStudentControlGetStudentFaculty() {
        assertEquals(FACULTY_DTO_OUT_2, restTemplate.getForObject("http://localhost:" + port + "/student/2/faculty", FacultyDtoOut.class));
    }

    @Test
    void testStudentControlEditStudent() {
        restTemplate.put("http://localhost:" + port + "/student/3", STUDENT_DTO_IN_3_EDIT);
        assertEquals(STUDENT_DTO_OUT_3_EDIT, restTemplate.getForObject("http://localhost:" + port + "/student/3", StudentDtoOut.class));

        String expected = ALL_STUDENTS_AFTER_EDIT.toString();
        String actual = restTemplate.getForObject("http://localhost:" + port + "/student", Collection.class).toString();
        assertEquals(expected, actual);
    }

    @Test
    void testStudentControlRemoveStudent() {
        restTemplate.delete("http://localhost:" + port + "/student/2");

        String expected = ALL_STUDENTS_AFTER_REMOVE.toString();
        String actual = restTemplate.getForObject("http://localhost:" + port + "/student", Collection.class).toString();
        assertEquals(expected, actual);
    }

    @Test
    void testFacultyControllerCreateFaculty() {
        assertEquals(FACULTY_DTO_OUT_4, restTemplate.postForObject("http://localhost:" + port + "/faculty", FACULTY_DTO_IN_4, FacultyDtoOut.class));
    }

    @Test
    void testFacultyControllerGetFaculties() {
        String expected = ALL_FACULTIES.toString();
		String actual = restTemplate.getForObject("http://localhost:" + port + "/faculty", Collection.class).toString();
		assertEquals(expected, actual);

        expected = ALL_FACULTIES_WITH_COLOR_1.toString();
        actual = restTemplate.getForObject("http://localhost:" + port + "/faculty?colorOrName=" + FACULTY_COLOR_1, Collection.class).toString();
        assertEquals(expected, actual);
    }

    @Test
    void testFacultyControllerGetFaculty() {
        assertEquals(FACULTY_DTO_OUT_1, restTemplate.getForObject("http://localhost:" + port + "/faculty/1", FacultyDtoOut.class));
    }

    @Test
    void testFacultyControllerGetStudents() {
        String expected = ALL_STUDENTS_IN_FACULTY_1.toString();
        String actual = restTemplate.getForObject("http://localhost:" + port + "/faculty/1/students", Collection.class).toString();
        assertEquals(expected, actual);
    }

    @Test
    void testFacultyControllerEditFaculty() {
        restTemplate.put("http://localhost:" + port + "/faculty/3", FACULTY_DTO_IN_3_EDIT);
        assertEquals(FACULTY_DTO_OUT_3_EDIT, restTemplate.getForObject("http://localhost:" + port + "/faculty/3", FacultyDtoOut.class));

        String expected = ALL_FACULTIES_AFTER_EDIT.toString();
        String actual = restTemplate.getForObject("http://localhost:" + port + "/faculty", Collection.class).toString();
        assertEquals(expected, actual);
    }

    @Test
    void testFacultyControllerRemoveFaculty() {
        restTemplate.delete("http://localhost:" + port + "/faculty/2");

        String expected = ALL_FACULTIES_AFTER_REMOVE.toString();
        String actual = restTemplate.getForObject("http://localhost:" + port + "/faculty", Collection.class).toString();
        assertEquals(expected, actual);
    }
}
