package org.university;

import java.util.*;

public class Exam {
    Grade grade = new Grade();
    //instructor controls
    private double score;
    private String date;
    private String typeOfExam;

    public Exam(double score, String date, String typeOfExam) {
        this.score = score;
        this.date = date;
        this.typeOfExam = typeOfExam;
        grade.setScore(score);
    }

    public double getScore() {
        return score;
    }


    public String getDate() {
        return date;
    }


    public String getTypeOfExam() {
        return typeOfExam;
    }

    @Override
    public String toString() {
        if (score != 0) return "Exam{" +
                "grade=" + grade +
                ", score=" + score +
                ", date='" + date + '\'' +
                ", typeOfExam='" + typeOfExam + '\'' +
                '}';
        return "Exam{" +
                ", date='" + date + '\'' +
                ", typeOfExam='" + typeOfExam + '\'' +
                '}';
    }
}
