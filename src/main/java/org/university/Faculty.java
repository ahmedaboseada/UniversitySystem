package org.university;

import java.util.*;

public class Faculty implements Operation {

    List<Instructor> instructors;

    List<Student> students;
    List<Course> courses;
    List<Department> departments;
    User user;
    Scanner in = new Scanner(System.in);

    public Faculty() {
        instructors = new ArrayList<Instructor>();
        students = new ArrayList<>();
        departments = new ArrayList<>();
        courses = new ArrayList<>();
        user = new User("admin", "admin");
    }

    @Override
    public void addStudent(Student student) {
        if (students.contains(student)) {
            System.out.printf("%s already registered\n", student.getName());
        } else {
            students.add(student);
            System.out.printf("%s has been added\n", student.getName());
        }
    }

    @Override
    public void removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            System.out.printf("%s has been removed\n", student.getName());
        } else {
            System.out.printf("%s not registered\n", student.getName());
        }
    }

    @Override
    public void addInstructor(Instructor instructor) {
        if (instructors.contains(instructor)) {
            System.out.printf("%s is already registered\n", instructor.getName());
        } else {
            instructors.add(instructor);
            System.out.printf("%s has been added\n", instructor.getName());
        }
    }

    @Override
    public void removeInstructor(Instructor instructor) {
        if (instructors.contains(instructor)) {
            instructors.remove(instructor);
            System.out.printf("%s has been removed\n", instructor.getName());
        } else {
            System.out.printf("%s not registered\n", instructor.getName());
        }
    }

    @Override
    public void addDepartment(Department department) {
        if (departments.contains(department)) {
            System.out.printf("%s is already registered\n", department.getName());
        } else {
            departments.add(department);
            System.out.printf("%s has been added\n", department.getName());
        }
    }

    @Override
    public void removeDepartment(Department department) {
        if (departments.contains(department)) {
            departments.remove(department);
            System.out.printf("%s has been removed\n", department.getName());
        } else {
            System.out.printf("%s not registered\n", department.getName());
        }
    }

    @Override
    public void addCourse(Course course) {
        if (courses.contains(course)) {
            System.out.printf("%s is already registered\n", course.getName());
        } else {
            courses.add(course);
            System.out.printf("%s has been added\n", course.getName());
        }
    }


    @Override
    public void removeCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            System.out.printf("%s has been removed\n", course.getName());
        } else {
            System.out.printf("%s not registered\n", course.getName());
        }
    }

    public boolean authenticate(String username, String password) {
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean Login() {
        String username, password;
        System.out.println("Please, Enter your username");
        username = in.next();
        System.out.println("Please, Enter your password");
        password = in.next();
        authenticate(username, password);
        if (authenticate(username, password) != true) {
            return false;
        }
        return true;
    }


}
