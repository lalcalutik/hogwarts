package ru.hogwarts.school.constants;

import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HogwartsSchoolApplicationWithRestTemplateConstants {

    public static final Long FACULTY_ID_1 = 1L;
    public static final Long FACULTY_ID_2 = 2L;
    public static final Long FACULTY_ID_3 = 3L;
    public static final Long FACULTY_ID_4 = 4L;
    public static final Long STUDENT_ID_1 = 1L;
    public static final Long STUDENT_ID_2 = 2L;
    public static final Long STUDENT_ID_3 = 3L;
    public static final String FACULTY_NAME_1 = "TestFacultyName1";
    public static final String FACULTY_NAME_2 = "TestFacultyName2";
    public static final String FACULTY_NAME_3 = "TestFacultyName3";
    public static final String FACULTY_NAME_4 = "TestFacultyName4";
    public static final String FACULTY_NAME_EDIT = "TestFacultyNameEDIT";
    public static final String STUDENT_NAME_1 = "TestStudentName1";
    public static final String STUDENT_NAME_2 = "TestStudentName2";
    public static final String STUDENT_NAME_3 = "TestStudentName3";
    public static final String STUDENT_NAME_EDIT = "TestStudentNameEdit";
    public static final String FACULTY_COLOR_1 = "TestColor1";
    public static final String FACULTY_COLOR_2 = "TestColor2";
    public static final String FACULTY_COLOR_4 = "TestColor4";
    public static final String FACULTY_COLOR_EDIT = "TestColorEDIT";
    public static final int STUDENT_AGE_1 = 12;
    public static final int STUDENT_AGE_2 = 11;
    public static final int STUDENT_AGE_EDIT = 15;

    public static final FacultyDtoIn FACULTY_DTO_IN_1 = new FacultyDtoIn(FACULTY_NAME_1, FACULTY_COLOR_1);
    public static final FacultyDtoIn FACULTY_DTO_IN_2 = new FacultyDtoIn(FACULTY_NAME_2, FACULTY_COLOR_2);
    public static final FacultyDtoIn FACULTY_DTO_IN_3 = new FacultyDtoIn(FACULTY_NAME_3, FACULTY_COLOR_1);
    public static final FacultyDtoIn FACULTY_DTO_IN_4 = new FacultyDtoIn(FACULTY_NAME_4, FACULTY_COLOR_4);
    public static final FacultyDtoIn FACULTY_DTO_IN_3_EDIT = new FacultyDtoIn(FACULTY_NAME_EDIT, FACULTY_COLOR_EDIT);
    public static final StudentDtoIn STUDENT_DTO_IN_1 = new StudentDtoIn(STUDENT_NAME_1, STUDENT_AGE_1, FACULTY_ID_1);
    public static final StudentDtoIn STUDENT_DTO_IN_2 = new StudentDtoIn(STUDENT_NAME_2, STUDENT_AGE_2, FACULTY_ID_2);
    public static final StudentDtoIn STUDENT_DTO_IN_3 = new StudentDtoIn(STUDENT_NAME_3, STUDENT_AGE_1, FACULTY_ID_1);
    public static final StudentDtoIn STUDENT_DTO_IN_3_EDIT = new StudentDtoIn(STUDENT_NAME_EDIT, STUDENT_AGE_EDIT, FACULTY_ID_4);
    public static final FacultyDtoOut FACULTY_DTO_OUT_1 = new FacultyDtoOut(FACULTY_ID_1, FACULTY_NAME_1, FACULTY_COLOR_1);
    public static final FacultyDtoOut FACULTY_DTO_OUT_2 = new FacultyDtoOut(FACULTY_ID_2, FACULTY_NAME_2, FACULTY_COLOR_2);
    public static final FacultyDtoOut FACULTY_DTO_OUT_3 = new FacultyDtoOut(FACULTY_ID_3, FACULTY_NAME_3, FACULTY_COLOR_1);
    public static final FacultyDtoOut FACULTY_DTO_OUT_4 = new FacultyDtoOut(FACULTY_ID_4, FACULTY_NAME_4, FACULTY_COLOR_4);
    public static final FacultyDtoOut FACULTY_DTO_OUT_3_EDIT = new FacultyDtoOut(FACULTY_ID_3, FACULTY_NAME_EDIT, FACULTY_COLOR_EDIT);
    public static final StudentDtoOut STUDENT_DTO_OUT_1 = new StudentDtoOut(STUDENT_ID_1, STUDENT_NAME_1, STUDENT_AGE_1, FACULTY_DTO_OUT_1);
    public static final StudentDtoOut STUDENT_DTO_OUT_2 = new StudentDtoOut(STUDENT_ID_2, STUDENT_NAME_2, STUDENT_AGE_2, FACULTY_DTO_OUT_2);
    public static final StudentDtoOut STUDENT_DTO_OUT_3 = new StudentDtoOut(STUDENT_ID_3, STUDENT_NAME_3, STUDENT_AGE_1, FACULTY_DTO_OUT_1);
    public static final StudentDtoOut STUDENT_DTO_OUT_3_EDIT = new StudentDtoOut(STUDENT_ID_3, STUDENT_NAME_EDIT, STUDENT_AGE_EDIT, FACULTY_DTO_OUT_4);
    public static final Collection<FacultyDtoOut> ALL_FACULTIES = new ArrayList<>(List.of(
            FACULTY_DTO_OUT_1,
            FACULTY_DTO_OUT_2,
            FACULTY_DTO_OUT_3,
            FACULTY_DTO_OUT_4
    ));
    public static final Collection<FacultyDtoOut> ALL_FACULTIES_WITH_COLOR_1 = new ArrayList<>(List.of(
            FACULTY_DTO_OUT_1,
            FACULTY_DTO_OUT_3
    ));
    public static final Collection<FacultyDtoOut> ALL_FACULTIES_AFTER_EDIT = new ArrayList<>(List.of(
            FACULTY_DTO_OUT_1,
            FACULTY_DTO_OUT_4,
            FACULTY_DTO_OUT_3_EDIT
    ));

    public static final Collection<FacultyDtoOut> ALL_FACULTIES_AFTER_REMOVE = new ArrayList<>(List.of(
            FACULTY_DTO_OUT_1,
            FACULTY_DTO_OUT_3,
            FACULTY_DTO_OUT_4
    ));
    public static final Collection<StudentDtoOut> ALL_STUDENTS = new ArrayList<>(List.of(
            STUDENT_DTO_OUT_1,
            STUDENT_DTO_OUT_2,
            STUDENT_DTO_OUT_3
    ));
    public static final Collection<StudentDtoOut> ALL_STUDENTS_WITH_AGE_1 = new ArrayList<>(List.of(
            STUDENT_DTO_OUT_1,
            STUDENT_DTO_OUT_3
    ));
    public static final Collection<StudentDtoOut> ALL_STUDENTS_AFTER_EDIT = new ArrayList<>(List.of(
            STUDENT_DTO_OUT_1,
            STUDENT_DTO_OUT_3_EDIT
            ));
    public static final Collection<StudentDtoOut> ALL_STUDENTS_AFTER_REMOVE = new ArrayList<>(List.of(
            STUDENT_DTO_OUT_1,
            STUDENT_DTO_OUT_3
    ));
    public static final Collection<StudentDtoOut> ALL_STUDENTS_IN_FACULTY_1 = ALL_STUDENTS_AFTER_REMOVE;
}
