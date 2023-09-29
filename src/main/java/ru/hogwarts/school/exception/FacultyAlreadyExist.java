package ru.hogwarts.school.exception;

public class FacultyAlreadyExist extends RuntimeException {

    @Override
    public String getMessage() {
        return "Faculty already exist";
    }
}
