package ru.hogwarts.school.dto;

import java.util.Objects;

public class FacultyDtoOut {

    private Long id;
    private String name;
    private String color;

    public FacultyDtoOut(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public FacultyDtoOut() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyDtoOut that = (FacultyDtoOut) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }

    @Override
    public String toString() {
        return '{' +
                "id=" + id +
                ", name=" + name +
                ", color=" + color +
                '}';
    }
}
