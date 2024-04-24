package org.university;

import java.util.*;

public class Grade {
    private double score;
    private String letterGrade;//A,B,C
    private List Grade;
    private double gpa;
    Scanner in = new Scanner(System.in);

    public void setGpa() {
        if (score >= 90) {
            gpa = 4;
        } else if (score < 90 && score >= 85) {
            gpa = 3.67;
        } else if (score < 85 && score >= 80) {
            gpa = 3.33;
        } else if (score < 80 && score >= 75) {
            gpa = 3;
        } else if (score < 75 && score >= 70) {
            gpa = 2.67;
        } else if (score < 70 && score >= 65) {
            gpa = 2.33;
        } else if (score < 65 && score >= 60) {
            gpa = 2;
        } else if (score < 60 && score >= 56) {
            gpa = 1.67;
        } else if (score < 56 && score >= 53) {
            gpa = 1.33;
        } else if (score < 53 && score >= 50) {
            gpa = 1;
        } else if (score < 50) {
            gpa = 0;
        }
    }

    public double getGpa() {
        return gpa;
    }

    public double getScore() {
        return score;
    } // e1 e2 e3 e10

    public void setScoreOfInstructor() {
        System.out.println("Enter score of the current course");
        double score = in.nextDouble();
        if (score <= this.score && score >= 0) { // e1 = 20 // e1=21
            this.score = score;
        } else {
            System.out.println("Score should be the same as exam grade or less");
            setScoreOfInstructor();
        }
    }

    public void setScore(double score) {
        if (score > 0 && score <= 100) {
            this.score = score;
        } else {
            System.out.println("Score should be from 1 to 60");
        }
    }

    public String getLetterGrade() {
        return letterGrade;
    }

//    public static double calculateAverageScore(List<Grade> grades) {
//        double sum = 0.0;
//        for (Grade grade : grades) {
//            sum += grade.getScore();
//        }
//        return sum / grades.size();
//    }

    public void calculateLetterGrade(double score) {
        if (score >= 90) {
            letterGrade = "A";
        } else if (score < 90 && score >= 85) {
            letterGrade = "A-";
        } else if (score < 85 && score >= 80) {
            letterGrade = "B+";
        } else if (score < 80 && score >= 75) {
            letterGrade = "B";
        } else if (score < 75 && score >= 70) {
            letterGrade = "B-";
        } else if (score < 70 && score >= 65) {
            letterGrade = "C+";
        } else if (score < 65 && score >= 60) {
            letterGrade = "C";
        } else if (score < 60 && score >= 56) {
            letterGrade = "C-";
        } else if (score < 56 && score >= 53) {
            letterGrade = "D+";
        } else if (score < 53 && score >= 50) {
            letterGrade = "D";
        } else if (score < 50) {
            letterGrade = "F";
        }
    }

    @Override
    public String toString() {
        if (letterGrade != null && gpa != 0.0) return "Grade{" +
                "letterGrade='" + letterGrade + '\'' +
                ", gpa=" + gpa +
                '}';
        return "grade is for students only!";
    }
}
