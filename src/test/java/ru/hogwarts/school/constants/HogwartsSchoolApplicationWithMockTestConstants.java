package ru.hogwarts.school.constants;

import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;

import java.util.ArrayList;
import java.util.List;

import static ru.hogwarts.school.constants.HogwartsSchoolApplicationWithRestTemplateConstants.*;

public class HogwartsSchoolApplicationWithMockTestConstants {

    public static final Faculty FACULTY_1 = new Faculty(FACULTY_ID_1, FACULTY_NAME_1, FACULTY_COLOR_1);
    public static final Faculty FACULTY_2 = new Faculty(FACULTY_ID_2, FACULTY_NAME_2, FACULTY_COLOR_2);
    public static final Faculty FACULTY_3 = new Faculty(FACULTY_ID_3, FACULTY_NAME_3, FACULTY_COLOR_1);
    public static final Faculty FACULTY_4 = new Faculty(FACULTY_ID_4, FACULTY_NAME_4, FACULTY_COLOR_4);
    public static final Student STUDENT_1 = new Student(STUDENT_ID_1, STUDENT_NAME_1, STUDENT_AGE_1, FACULTY_1);
    public static final Student STUDENT_2 = new Student(STUDENT_ID_2, STUDENT_NAME_2, STUDENT_AGE_2, FACULTY_2);
    public static final Student STUDENT_3 = new Student(STUDENT_ID_3, STUDENT_NAME_3, STUDENT_AGE_1, FACULTY_1);
    public static final List<Faculty> FACULTY_LIST = new ArrayList<>(List.of(
            FACULTY_1,
            FACULTY_2,
            FACULTY_3,
            FACULTY_4
    ));
    public static final List<Faculty> FACULTY_LIST_COLOR = new ArrayList<>(List.of(
            FACULTY_1,
            FACULTY_3
    ));
    public static final List<Student> STUDENT_LIST = new ArrayList<>(List.of(
            STUDENT_1,
            STUDENT_2,
            STUDENT_3
    ));
    public static final List<Student> STUDENT_LIST_AGE = new ArrayList<>(List.of(
            STUDENT_1,
            STUDENT_3
    ));
    public static final List<Student> STUDENT_LIST_FROM_FACULTY = new ArrayList<>(List.of(
            STUDENT_1,
            STUDENT_3
    ));
}
