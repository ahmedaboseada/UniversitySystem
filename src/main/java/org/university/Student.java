package org.university;

import java.io.IOException;
import java.util.*;

public class Student extends Person {
    protected String major; //class department
    protected double GPA;
    private String letterGrade; //A,A-
    private int currentSemester;
    protected List<Course> enrolledCourses;
    private static int numberOfStudents;
    private final int maxCreditsInSemester = 18;
    protected final int minCreditsInSemester = 12;
    protected int currentCredits = 0;
    private int academicID;
    protected User user;
    Scanner in = new Scanner(System.in);

    public Student(String major, int currentSemester, String name, String email, String phone, String ID) throws IOException {
        super(name, email, phone, ID);
        this.major = major;
//        setGPA(GPA);
        this.currentSemester = currentSemester;
        this.enrolledCourses = new ArrayList<>();
        user = new User();
        user.id =ID;
        user.register();
        numberOfStudents++; // 1 // 2
        academicID = numberOfStudents; // 1 // 2
    }

    public int getAcademicID() {
        return academicID;
    }

    public void resetPassword() {
        String username;
        System.out.println("Enter your username");
        username = in.next();
        if (username.equals(user.getUsername())) {
            String ID;
            System.out.println("Enter your national ID");
            ID = in.next();
            if (ID.equals(getID())) {
                user.setPassword();
            } else {
                System.out.println("National ID is wrong");
                resetPassword();
            }
        } else {
            System.out.println("User name is wrong");
            resetPassword();
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean Login(String username) {
        String password;
        System.out.println("Please, Enter your password");
        password = in.next();
        authenticate(username, password);
        if (authenticate(username, password) != true) {
            return false;
        }
        return true;
    }

    public void setGPA(double GPA) {
        if (GPA >= 0 && GPA <= 4) {
            this.GPA = GPA;
        } else {
            System.out.println("Gpa should be between 0 and 4");
        }
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void getMajor() { //Students can choose their major
        if (currentSemester >= 3)
            System.out.println(major);
        else {
            System.out.println("You have been not passed the second semester");
        }
    }

    public double getGPA() {
        for (int i = 0; i < enrolledCourses.size(); i++) {
            System.out.printf("%s Gpa: %s\n", enrolledCourses.get(i).getName(), enrolledCourses.get(i).grade.getGpa());
            System.out.printf("grade: %s\n", enrolledCourses.get(i).grade.getLetterGrade());
        }
        System.out.printf("Your semester grade is %s\n", getLetterGrade());
        System.out.println("CGpa:");
        return GPA;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void getEnrolledCourses() {
        if ((enrolledCourses.isEmpty())) {
            System.out.println("You don't have any courses enrolled");
            return;
        }
        System.out.println(enrolledCourses);
    }

    public void dropCourse(Course course) {
        if (enrolledCourses.contains(course)) {
            if (currentCredits >= minCreditsInSemester) {
                enrolledCourses.remove(course);
                currentCredits -= course.getCredits();
                System.out.printf("You removed %s course\n", course.getName());
                System.out.println("Your current credits: " + currentCredits);
            } else {
                System.out.printf("Your can't be less than %d", minCreditsInSemester);
            }
        } else {
            System.out.printf("You didn't enroll this course\n", getName());
            System.out.printf("Current enrolled courses:\n" + enrolledCourses);
        }
    }

    public void addCourse(Course course) {
        if (course.getCredits() + currentCredits <= maxCreditsInSemester) {
            if (enrolledCourses.contains(course)) {
                System.out.println("You already registered this course");
            } else {
                enrolledCourses.add(course);
                currentCredits += course.getCredits();
                System.out.printf("You added %s course\n", course.getName());
                System.out.println("Your current credits: " + currentCredits);
            }
        } else {
            System.out.println("You can't add a course more than your max credits\n");
            System.out.println("Current credits: " + currentCredits);
            System.out.println("\nMax credits in current semester: " + maxCreditsInSemester);
        }
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public void calculateGpa() {
        for (int i = 0; i < enrolledCourses.size(); i++) {
            GPA += enrolledCourses.get(i).grade.getGpa() * enrolledCourses.get(i).getCredits() / currentCredits; // 4/18 + 3/18
        }
        if (GPA <= 4 && GPA >= 3.5) {
            letterGrade = "A";
        } else if (GPA >= 3 && GPA <= 3.499) {
            letterGrade = "B";
        } else if (GPA >= 2.3 && GPA <= 2.999) {
            letterGrade = "C";
        } else if (GPA >= 2 && GPA <= 2.299) {
            letterGrade = "D";
        } else if (GPA < 2) {
            letterGrade = "F";
        }
    }


    @Override
    public String toString() {
        return "Student{" +
                "major='" + major + '\'' +
                ", GPA=" + GPA +
                ", currentSemester=" + currentSemester +
                ", enrolledCourses=" + enrolledCourses +
                ", currentCredits=" + currentCredits +
                ", academicID=" + academicID +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
