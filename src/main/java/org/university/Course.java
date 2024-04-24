package org.university;

import java.util.*;

public class Course {
    private String name;
    private String letterGrade;
    private String department;
    private String description;
    private int credits;
    private String instructorName;
    private List<Exam> exams;

    Grade grade;

    public Course(String name, String department, String description, int credits, String instructorName) {
        this.name = name;
        this.department = department;
        this.description = description;
        if (credits <= 4 && credits > 0) {
            this.credits = credits;
        } else {
            System.out.println("credit of the course cannot excess 4 hours");
        }
        grade = new Grade();
        exams = new ArrayList<>();
        this.instructorName = instructorName;
    }

    public int getCredits() {
        return credits;
    }

    public String getName() {
        return name;
    }

    public void setExamsGrades() {
        for (int i = 0; i < exams.size(); i++) {
            System.out.println(exams.get(i).getTypeOfExam());
            System.out.println(getName());
            exams.get(i).grade.setScoreOfInstructor();//e1 e2 e3 e4 // index 0 : e1 --> enter e1 score
            exams.get(i).grade.setGpa();
            exams.get(i).grade.calculateLetterGrade(exams.get(i).grade.getGpa());
        }

    }

    public void calculateScore() {
        double sum = 0.0;
        for (int i = 0; i < exams.size(); i++) {
            if (sum <= 100)
                sum += exams.get(i).grade.getScore(); //e1+e2+e3+e4 //
        }
        grade.setScore(sum);
        grade.calculateLetterGrade(sum);
        grade.setGpa();
    }

    public void addExam(Exam exam) {
        if (exam.grade.getScore() <= 100) {
            exams.add(exam);
        } else {
            System.out.println("Your maximum grade allowed is 100"); //100
        }
    }


    @Override
    public String toString() {
        if (!(exams.isEmpty())) return
                "Course{" +
                        "name='" + name + '\'' +
                        ", department='" + department + '\'' +
                        ", description='" + description + '\'' +
                        ", credits=" + credits +
                        ", exams=" + exams +
                        '}';
        return "Course{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", description='" + description + '\'' +
                ", credits=" + credits;
    }

    public String getDepartment() {
        return department;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void editExamScore(String examName, double newScore) {
        for (Exam exam : exams) {
            if (exam.getTypeOfExam().equals(examName)) {
                exam.grade.setScore(newScore);
                break;
            }
        }
    }
}