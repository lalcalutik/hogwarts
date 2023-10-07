package ru.hogwarts.school.exception;

public class StudentNotFoundException extends RuntimeException {

    private final long id;

    public StudentNotFoundException(long id) {
        this.id = id;
    }


    @Override
    public String getMessage() {
        return "Student with id = " + id + " not found!";
    }
}
