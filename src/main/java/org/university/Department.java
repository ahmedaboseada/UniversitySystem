package org.university;

import java.util.*;

public class Department {
    private String name;
    List<Course> courses;
    List<Student> students;
    List<Instructor> instructors;

    public Department(String name) {
        this.name = name;
        courses = new ArrayList<>();
        students = new ArrayList<>();
        instructors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", courses=" + courses +
                ", students=" + students +
                ", instructors=" + instructors +
                '}';
    }
}
