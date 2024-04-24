package org.university;

import java.io.IOException;
import java.util.*;

public class Instructor extends Employee {
    Scanner in = new Scanner(System.in);
    private List<Course> courses;


    public Instructor(String name, String email, String phone, String ID, String rank, double salary, int vacationDays, String department) throws IOException {
        super(name, email, phone, ID, rank, salary, vacationDays, department);
        this.courses = new ArrayList<>(2);
        user = new User();
        user.id =ID;
        user.register();
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course createCourse(String name, String department, String description, int credits, String instructorName) {
        Course course = new Course(name, department, description, credits, instructorName);
        courses.add(course);
        return course;
    }

    public Exam createExam(Course course, double score, String date, String typeOfExam) {
        Exam exam = new Exam(score, date, typeOfExam);
        course.addExam(exam);
        return exam;
    }

    public String toString() {
        return "Instructor: " + name + "\n" +
                "Courses: " + courses;
    }

    public void setGradeOfStudent(Student student, Instructor instructor) {
        for (Course course : courses) { //for each
            int i = 0;
            if (student.enrolledCourses.contains(instructor.getCourses().get(i))) {
                int index = student.enrolledCourses.indexOf(course);
                student.enrolledCourses.get(index).setExamsGrades(); //e1 e2 e3 e4
                student.enrolledCourses.get(index).calculateScore(); // e1+e2+e3+e4
                i++;
            }
        }
    }


}
