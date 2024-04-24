package org.university;

public interface Operation {
    public void addStudent(Student student);

    public void removeStudent(Student student);

    public void addInstructor(Instructor instructor);

    public void removeInstructor(Instructor instructor);

    public void addDepartment(Department department);

    public void removeDepartment(Department department);

    public void addCourse(Course course);

    public void removeCourse(Course course);
}
