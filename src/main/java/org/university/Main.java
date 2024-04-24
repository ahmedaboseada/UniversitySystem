package org.university;

import java.io.*;
import java.util.Scanner;

public class Main {
    static Faculty faculty = new Faculty();


    public static void registering() throws IOException {
        Department math = new Department("Math");
        Department cs = new Department("Computer Science");
        Department phy = new Department("Physics");
        Department chem = new Department("Chemistry");
        Department[] departments = {math, cs, chem, phy};
        for (int i = 0; i < departments.length; i++) {
            faculty.addDepartment(departments[i]);
        }

    }

    public static void menu(Object object) throws IOException {
        Scanner in = new Scanner(System.in);
        char operation = '0';
        if (object instanceof Instructor) {
            Instructor instructor = (Instructor) object;
            int numOfCourses = 0;
            char login = '0';
            while (login != 'E') {
                int i = 0;
                do {

                    if (instructor.Login(instructor.user.getUsername())) { // Check for successful login
                        // Login successful, execute other statements here
                        // ...
                        while (operation != 'E') {
                            System.out.printf("Welcome %s!\n", instructor.getName());
                            System.out.println("Type A to create course");
                            System.out.println("Type B to create exams");
                            System.out.println("Type C to set grade for student");
                            System.out.println("Type D to see your courses");
                            System.out.println("Type E to Exit//back to log in menu");
                            operation = Character.toUpperCase(in.next().charAt(0));
                            switch (operation) {
                                case 'A' -> {
                                    if (numOfCourses != 1) {
                                        System.out.println("Write name of the course");
                                        in.nextLine();
                                        String name = in.nextLine();
                                        String department = instructor.getDepartment();
                                        System.out.println("Enter the credits of the course");
                                        int credits = in.nextInt();
                                        System.out.println("Write the description of the course ");
                                        in.nextLine();
                                        String description = in.nextLine();
                                        String instructorName = instructor.getName();
                                        faculty.addCourse(instructor.createCourse(name, department, description, credits, instructorName));
                                        numOfCourses++;
                                    } else {
                                        System.out.println("You can't teach more courses");
                                        break;
                                    }
                                }
                                case 'B' -> {
                                    int index;
                                    for (int j = 0; j < instructor.getCourses().size(); j++)
                                        System.out.printf("to make an exam for %s type %d\n", instructor.getCourses().get(j).getName(), j);
                                    index = in.nextInt(); //0
                                    if (index < instructor.getCourses().size()) { //0,1 -> 2
                                        System.out.println("Enter exam score from 1 to 60");
                                        System.out.println("!Exams' total score must equal 100 and can't excess it");
                                        double score = in.nextDouble();
                                        System.out.println("Enter the date of exam");
                                        String date = in.next();
                                        System.out.println("Enter type of exam");
                                        in.nextLine();
                                        String typeOfExam = in.nextLine();
                                        instructor.createExam(instructor.getCourses().get(index), score, date, typeOfExam);
                                        break;
                                    }
                                }
                                case 'C' -> { //set student grade
                                    for (int j = 0; j < faculty.students.size(); j++) {
                                        for (Course course : instructor.getCourses())
                                            if (faculty.students.get(j).enrolledCourses.contains(course)) {
                                                System.out.println("Please choose the student to set his/her grades");
                                                System.out.printf("%d- %s\n", j + 1, faculty.students.get(j).getName());
                                                int index = in.nextInt() - 1;
                                                instructor.setGradeOfStudent(faculty.students.get(index), instructor);
                                            } else System.out.println("No students registered your course");
                                    }
                                }
                                case 'D' -> {
                                    System.out.println("Your registered courses:");
                                    for (int k = 0; k < instructor.getCourses().size(); k++)
                                        System.out.printf("%d- %s\n", k + 1, instructor.getCourses().get(k).getName());
                                }
                                case 'E' -> {
                                    i = 4;
                                }
                            }
                        }
                    } else {
                        System.out.println("Enter A to log in again or R to reset password");
                        login = Character.toUpperCase(in.next().charAt(0));
                        switch (login) {
                            case 'A' -> i++; // Increment attempt counter only for login retries
                            case 'R' -> instructor.resetPassword();
                            default -> System.out.println("Invalid choice. Please enter A or R.");
                        }
                    }


                } while (i < 3);  // Limit login attempts to 3

                if (i == 3) {
                    System.out.println("Login attempts exceeded.");
                    login = 'E'; // Exit the outer loop
                } else if (i == 4) {
                    System.out.printf("Goodbye, %s!\n", instructor.getName());
                    login = 'E';
                }
            }


        } else if (object instanceof Student) {
            Student student = (Student) object;
            char login = '0';
            while (login != 'E') {
                int i = 0;
                do {
                    if (student.Login(student.user.getUsername())) { // Check for successful login
                        // Login successful, execute other statements here
                        // ...
                        while (operation != 'E') {
                            System.out.printf("Welcome %s!\n", student.getName());
                            System.out.println("Type A to register a course");
                            System.out.println("Type B to drop a course");
                            System.out.println("Type C to show registered courses");
                            System.out.println("Type D to see grades");
                            System.out.println("Type E to Exit//back to log in menu");
                            operation = Character.toUpperCase(in.next().charAt(0));
                            switch (operation) {
                                case 'A' -> {
                                    for (int j = 0; j < faculty.courses.size(); j++) {//
                                        int instructorIndex = 0;
                                        if (student.major.equals(faculty.courses.get(j).getDepartment())) {
                                            for (int k = 0; k < faculty.courses.size(); k++) {
                                                if (faculty.courses.get(j).getInstructorName().equals(faculty.instructors.get(k).getName()))
                                                    instructorIndex = k;
                                            }
                                            System.out.printf("To register %s of %s.%s type %d\n", faculty.courses.get(j).getName(), faculty.instructors.get(instructorIndex).getRank(), faculty.instructors.get(instructorIndex).getName(), j); //course name - instructors rank - instructors name
                                        }
                                    }
                                    int index = in.nextInt();
                                    student.addCourse(faculty.courses.get(index));
                                }
                                case 'B' -> {
                                    int courseIndex = 0;
                                    for (int j = 0; j < student.enrolledCourses.size(); j++) {
                                        System.out.printf("To remove %s course type %d\n", student.enrolledCourses.get(j).getName(), j);
                                        courseIndex = j;
                                    }
                                    int index = in.nextInt();
                                    student.dropCourse(student.enrolledCourses.get(courseIndex));
                                }
                                case 'C' -> {
                                    System.out.println("Your registered courses:");
                                    for (int k = 0; k < student.enrolledCourses.size(); k++)
                                        System.out.printf("%d- %s\n", k + 1, student.enrolledCourses.get(k).getName());
                                }
                                case 'D' -> {
                                    System.out.println("Your semester grade:");
                                    student.getMajor();
                                    System.out.println(student.getGPA());
                                }
                                case 'E' -> {
                                    i = 4;
                                }
                            }
                        }


                    } else {
                        System.out.println("Enter A to log in again or R to reset password");
                        login = Character.toUpperCase(in.next().charAt(0));
                        switch (login) {
                            case 'A' -> i++; // Increment attempt counter only for login retries
                            case 'R' -> student.resetPassword();
                            default -> System.out.println("Invalid choice. Please enter A or R.");
                        }
                    }

                } while (i < 3);  // Limit login attempts to 3

                if (i == 3) {
                    System.out.println("Login attempts exceeded.");
                    login = 'E'; // Exit the outer loop
                } else if (i == 4) {
                    System.out.printf("Goodbye, %s!\n", student.getName());
                    login = 'E';
                }
            }


        } else if (object instanceof Faculty) {
            Faculty faculty1 = (Faculty) object;
            char login = '0';
            while (login != 'E') {
                int i = 0;
                do {
                    if (faculty.Login()) { // Check for successful login
                        // Login successful, execute other statements here
                        // ...
                        while (operation != 'E') {
                            System.out.println("Welcome Admin!");
                            System.out.println("Type A to control instructors");
                            System.out.println("Type B to control students");
                            System.out.println("Type C to show faculty courses");
                            System.out.println("Type D to show faculty departments");
                            System.out.println("Type F to calculate Students' gpa");
                            System.out.println("Type E to Exit//back to log in menu");
                            operation = Character.toUpperCase(in.next().charAt(0));
                            switch (operation) {
                                case 'A' -> {
                                    char op2 = '0';
                                    System.out.println("Type A to add a Instructor");
                                    System.out.println("Type B to remove a Instructor");
                                    System.out.println("Type C to show registered Instructors");
                                    op2 = Character.toUpperCase(in.next().charAt(0));
                                    switch (op2) {
                                        case 'A' -> {
                                            in.nextLine();
                                            System.out.println("Enter Instructor's name");
                                            String name = in.nextLine();
                                            System.out.println("Enter Instructor's email");
                                            String email = in.nextLine();
                                            System.out.println("Enter Instructor's phone number");
                                            String phone = in.next();
                                            System.out.println("Enter Instructor's National ID");
                                            String id = in.next();
                                            System.out.println("Enter Instructor's rank");
                                            String rank = in.next();
                                            System.out.println("Enter Instructor's salary");
                                            double salary = in.nextDouble();
                                            System.out.println("Enter Instructor's vacations days");
                                            int vacationdays = in.nextInt();
                                            String department = "null";
                                            System.out.println("Your registered courses:");
                                            for (int k = 0; k < faculty.departments.size(); k++)
                                                System.out.printf("%d- %s\n", k + 1, faculty.departments.get(k).getName());
                                            int index = in.nextInt() - 1;
                                            if (index <= 3)
                                                department = faculty.departments.get(index).getName();
                                            else System.out.println("You have to choose from 0 to 3");
                                            faculty.addInstructor(new Instructor(name, email, phone, id, rank, salary, vacationdays, department));
                                            break;
                                        }
                                        case 'B' -> {
                                            System.out.println("Registered Instructors:");
                                            for (int k = 0; k < faculty.instructors.size(); k++)
                                                System.out.printf("%d- %s\n", k + 1, faculty.instructors.get(k).getName());
                                            System.out.println("Enter number of doctor");
                                            int index = in.nextInt() - 1;
                                            faculty.removeInstructor(faculty.instructors.get(index));
                                            break;
                                        }
                                        case 'C' -> {
                                            System.out.println("Registered Instructors:");
                                            for (int k = 0; k < faculty.instructors.size(); k++)
                                                System.out.printf("%d- %s\n", k + 1, faculty.instructors.get(k).getName());
                                            break;
                                        }
                                        default -> operation = '0';
                                    }//end nested switch
                                    break;
                                }
                                case 'B' -> {
                                    char op2 = '0';
                                    System.out.println("Type A to add a Student");
                                    System.out.println("Type B to remove a Student");
                                    System.out.println("Type C to show registered Student");
                                    op2 = Character.toUpperCase(in.next().charAt(0));
                                    switch (op2) {
                                        case 'A' -> {
                                            in.nextLine();
                                            System.out.println("Enter Student's name");
                                            String name = in.nextLine();
                                            System.out.println("Enter Student's email");
                                            String email = in.nextLine();
                                            System.out.println("Enter Student's phone number");
                                            String phone = in.next();
                                            System.out.println("Enter Student's National ID");
                                            String id = in.next();
                                            System.out.println("Enter the current semester of student");
                                            int currentsemester = in.nextInt();
                                            String department = "null";
                                            for (int k = 0; k < faculty.departments.size(); k++)
                                                System.out.printf("%d- %s\n", k + 1, faculty.departments.get(k).getName());
                                            int index = in.nextInt() - 1;
                                            if (index <= 3)
                                                department = faculty.departments.get(index).getName();
                                            else System.out.println("You have to choose from 0 to 3");
                                            faculty.addStudent(new Student(department, currentsemester, name, email, phone, id));
                                            break;
                                        }
                                        case 'B' -> {
                                            System.out.println("Registered Students:");
                                            for (int k = 0; k < faculty.students.size(); k++)
                                                System.out.printf("%d- %s\n", k + 1, faculty.students.get(k).getName());
                                            System.out.println("Enter number of student");
                                            int index = in.nextInt() - 1;
                                            faculty.removeStudent(faculty.students.get(index));
                                            break;
                                        }
                                        case 'C' -> {
                                            System.out.println("Registered Students:");
                                            for (int k = 0; k < faculty.students.size(); k++)
                                                System.out.printf("%d- %s\n", k + 1, faculty.students.get(k).getName());
                                            break;
                                        }
                                        default -> operation = '0';
                                    }
                                    break;
                                }
                                case 'C' -> { //show courses
                                    System.out.println("Faculty courses:");
                                    for (int k = 0; k < faculty.courses.size(); k++)
                                        System.out.printf("%d- %s\n", k + 1, faculty.courses.get(k).getName());
                                }
                                case 'D' -> {//show departments
                                    System.out.println("Faculty courses:");
                                    for (int k = 0; k < faculty.departments.size(); k++)
                                        System.out.printf("%d- %s\n", k + 1, faculty.departments.get(k).getName());
                                }
                                case 'F' -> {//calculate gpa
                                    for (int j = 0; j < faculty.students.size(); j++) {
                                        faculty.students.get(j).calculateGpa();
                                    }
                                }
                                case 'E' -> {
                                    i = 4;
                                }
                            }
                        }


                    } else {
                        System.out.println("Enter A to log in again.");
                        login = Character.toUpperCase(in.next().charAt(0));
                        switch (login) {
                            case 'A' -> i++; // Increment attempt counter only for login retries
                            default -> System.out.println("Invalid choice. Please enter A.");
                        }
                    }

                } while (i < 3);  // Limit login attempts to 3

                if (i == 3) {
                    System.out.println("Login attempts exceeded.");
                    login = 'E'; // Exit the outer loop
                } else if (i == 4) {
                    System.out.println("Goodbye!");
                    login = 'E';
                }
            }


        }
    }

    public static void facultyMenu() throws IOException {
        Scanner in = new Scanner(System.in);
        char operation = '0';
        while (operation != 'E') {
            System.out.println("Type A to log in as an Instructor");
            System.out.println("Type B to log in as a Student");
            System.out.println("Type C to log in as an Admin");
            System.out.println("Type E to exit");
            operation = Character.toUpperCase(in.next().charAt(0));
            switch (operation) {
                case 'A' -> {
//                    System.out.println("Please enter your national ID");
//                    String id = in.next();
//                    for (Instructor instructor : faculty.instructors) {
//                        if (instructor.getID().equals(id)) {
//                            menu(instructor);
//                            break;
//                        }
//                    }
                    final String FILE_NAME = "users.txt";
                    BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
                    System.out.println("Enter username");
                    String username = in.next();
                    String line;
                    int f = 0;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        for (Instructor instructor : faculty.instructors) {
                            if (username.equals(parts[0]) && username.equals(instructor.user.getUsername())) {
                                menu(instructor);
                                f = 1;
                            }
                            break;

                        }
                        f = 0;
                    }
                    if (f == 0)
                        System.out.println("Username doesn't exist");
                    break;
                }
                case 'B' -> {
//                    System.out.println("Please enter your national ID");
//                    String id = in.next();
//                    for (Student student : faculty.students) {
//                        if (student.getID().equals(id)) {
//                            menu(student);
//                            break;
//                        }
//                    }
                    final String FILE_NAME = "users.txt";
                    BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
                    System.out.println("Enter username");
                    String username = in.next();
                    String line;
                    int f = 0;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        for (Student student : faculty.students) {
                            if (username.equals(parts[0]) && username.equals(student.user.getUsername())) {
                                menu(student);
                                f = 1;
//                                break;

                            }
                        }
                    }
                    if (f == 0)
                        System.out.println("Username doesn't exist");
                    break;
                }
                case 'C' -> {
                    menu(faculty);
                }
                case 'E' -> {
                    System.out.println("Goodbye!");
                    operation = 'E';
                }

            }
            /*
             * 1-login as an instructor
             *
             * 2-login as a student
             *
             * 3-login as an admin
             * */
        }
    }


    public static void main(String[] args) throws IOException {

//        registering();
        objects();
        facultyMenu();
    }

    public static void objects() throws IOException {
        File departments = new File("departments.txt");
        departments.createNewFile();
        BufferedReader dReader = new BufferedReader(new FileReader("departments.txt"));
        String dLine;
        String[] data3 = new String[8];
        dLine = dReader.readLine();
        int t = 0;
        while (dLine != null) {

            data3[t] = dLine;
            System.out.println(dLine);
            t++;
            dLine = dReader.readLine();
        }
        for (int i = 0; i < 8; i++)
            System.out.println(data3[i]);
        for (int i = 0; i < data3.length; i++) {
            Department department = new Department(data3[i]);
            faculty.departments.add(department);
        }
        Scanner in = new Scanner(System.in);
        File students = new File("students.txt");
        File instructors = new File("instructors.txt");
        students.createNewFile();
        instructors.createNewFile();
        // checking file for information
        {   //checking for students stored data
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String[] data2 = new String[6];
                for (int i = 0; i < 6; i++) {
                    data2[i] = data[i];
                }
                Student s = new Student(data2[0], Integer.parseInt(data2[1]), data2[2], data2[3], data2[4], data2[5]);
                faculty.students.add(s);
            }
        }
        {   //checking for instructors stored data
            BufferedReader reader = new BufferedReader(new FileReader("instructors.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String[] data2 = new String[8];
                for (int i = 0; i < 8; i++) {
                    data2[i] = data[i];
                }


                Instructor instructor = new Instructor(data[0], data[1], data[2], data[3], data[4], Double.parseDouble(data[5]), Integer.parseInt(data[6]), data[7]);
//string name-email-phone-id-rank//double salary,int vacationdays,string department
                faculty.instructors.add(instructor);
            }
        }
        System.out.println("Welcome!");
        int choice;
        do {
            System.out.println("choose 1 to register a new Student");
            System.out.println("choose 2 to register a new Instructor");
            System.out.println("choose 3 to exit menu");
            choice = in.nextInt();
            if (choice == 1) {
                String[] fullName = new String[5];
                String[] ntypes = {"First name", "Second name", "Third name", "Fourth name", "Last name"};
                System.out.println("Enter full-name");
                for (int i = 0; i < 5; i++) {
                    System.out.println(ntypes[i]);
                    fullName[i] = in.next();
                }
                String name = String.format("%s %s %s %s %s", fullName[0], fullName[1], fullName[2], fullName[3], fullName[4]);
                System.out.println("Current semester");
                String semester = in.next();
                while (Integer.parseInt(semester) < 0 && Integer.parseInt(semester) > 12) {
                    System.out.println("Maximum semester is 12");
                    System.out.println("Please, Enter current semester again!");
                    semester = String.valueOf(in.nextInt());
                }
                String major = null;
                if (Integer.parseInt(semester) >= 3) {
                    System.out.println("Choose a major");

                    for (int k = 0; k < faculty.departments.size(); k++) {
                        System.out.printf("%d- %s\n", k + 1, faculty.departments.get(k).getName());
                    }
                    int choice2 = in.nextInt();
                    major = faculty.departments.get(choice2 - 1).getName();
                } else major = "No specialty";

//                System.out.println("Enter name");

                System.out.println("Enter E-mail");
                String email = in.next();
                {
                    String line2;
                    BufferedReader reader2 = new BufferedReader(new FileReader("students.txt"));
                    while ((line2 = reader2.readLine()) != null) {
                        String[] parts = line2.split(",");
                        if (parts[3].matches(email)) {
                            while (parts[3].matches(email)) {
                                System.out.println("Email already exists");
                                System.out.println("Please, Enter it again");
                                email = in.next();
                            }
                        }
                    }
                }
                System.out.println("enter phone");
                String phone = in.next();
                {
                    String line2;
                    BufferedReader reader2 = new BufferedReader(new FileReader("students.txt"));
                    while ((line2 = reader2.readLine()) != null) {
                        String[] parts = line2.split(",");
                        if (parts[4].matches(phone)) {
                            while (parts[4].matches(phone)) {
                                System.out.println("Phone number already exists");
                                System.out.println("Please, Enter it again");
                                phone = in.next();
                            }
                        }
                    }
                }
                System.out.println("enter national id");
                String id = in.next();
                {
                    String line2;
                    BufferedReader reader2 = new BufferedReader(new FileReader("students.txt"));
                    while ((line2 = reader2.readLine()) != null) {
                        String[] parts = line2.split(",");
                        if (parts[5].matches(id)) {
                            while (parts[5].matches(id)) {
                                System.out.println("National ID already exists");
                                System.out.println("Please, Enter it again");
                                id = in.next();
                            }
                        }

                    }
                }
                String[] data = {major, semester, name, email, phone, id};
                Student s = new Student(data[0], Integer.parseInt(semester), data[2], data[3], data[4], data[5]);
                faculty.students.add(s);
                System.out.printf("Student %s has been registered\n", name);
                BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true));
                for (int i = 0; i < data.length; i++) {
                    writer.write(data[i]);
                    if (i < data.length - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
                writer.close();

//            Student s = new Student(); //s.major - int semester - s.name-s.email s.phone s.national id


            } else if (choice == 2) {
                //string name-email-phone-id-rank//double salary,int vacationDays,string department
                String[] fullName = new String[5];
                String[] ntypes = {"First name", "Second name", "Third name", "Fourth name", "Last name"};
                System.out.println("Enter full-name");
                for (int i = 0; i < 5; i++) {
                    System.out.println(ntypes[i]);
                    fullName[i] = in.next();
                }
                String name = String.format("%s %s %s %s %s", fullName[0], fullName[1], fullName[2], fullName[3], fullName[4]);
                System.out.println("Enter E-mail");
                String email = in.next();
                {
                    String line;
                    String line2;
                    BufferedReader reader2 = new BufferedReader(new FileReader("instructors.txt"));
                    BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
                    while (((line2 = reader2.readLine()) != null) && ((line = reader.readLine()) != null)) {
                        String[] parts = line2.split(",");
                        String[] parts2 = line.split(",");
                        if (parts[1].matches(email) || parts2[3].matches(email)) {
                            while (parts[1].matches(email) || parts2[3].matches(email)) {
                                System.out.println("Email already exists");
                                System.out.println("Please, Enter it again");
                                email = in.next();
                            }
                        }
                    }
                }


                System.out.println("enter phone");
                String phone = in.next();
                {
                    String line2;
                    String line;
                    BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
                    BufferedReader reader2 = new BufferedReader(new FileReader("instructors.txt"));
                    while (((line2 = reader.readLine()) != null) && ((line = reader2.readLine()) != null)) {
                        String[] parts = line2.split(",");
                        String[] parts2 = line.split(",");
                        if (parts[4].matches(phone) || parts2[2].matches(phone)) {
                            while (parts[4].matches(phone) || parts2[2].matches(phone)) {
                                System.out.println("Phone number already exists");
                                System.out.println("Please, Enter it again");
                                phone = in.next();
                            }
                        }
                    }
                }
                System.out.println("Enter national id");
                String id = in.next();
                {
                    String line2;
                    String line = "";
                    BufferedReader reader2 = new BufferedReader(new FileReader("students.txt"));
                    BufferedReader reader = new BufferedReader(new FileReader("instructors.txt"));
                    while (((line2 = reader2.readLine()) != null) && (line = reader.readLine()) != null) {
                        String[] parts2 = line2.split(",");
                        String[] parts = line.split(",");
                        if (parts2[5].matches(id) || parts[3].matches(id)) {
                            while (parts2[5].matches(id) || parts[3].matches(id)) {
                                System.out.println("National ID already exists");
                                System.out.println("Please, Enter it again");
                                id = in.next();
                            }
                        }

                    }
                }

                System.out.println("Enter instructor's rank");
                String rank = in.next();
                System.out.printf("Enter %s's salary\n", rank);
                System.out.println("Minimum salary is 2k");
                double salary = in.nextDouble();
                System.out.println("Enter number of vacation days");
                System.out.println("Maximum is 4 days");
                int vacationDays = in.nextInt();
                String major = null;

                for (int k = 0; k < faculty.departments.size(); k++) {
                    System.out.printf("%d- %s\n", k + 1, faculty.departments.get(k).getName());
                }
                int choice2 = in.nextInt();
                major = faculty.departments.get(choice2 - 1).getName();
                //rank-salary-vacationDays-major
                //string name-email-phone-id-rank//double salary,int vacationDays,string department
                String[] data = {name, email, phone, id, rank, String.valueOf(salary), String.valueOf(vacationDays), major};
                Instructor instructor = new Instructor(data[0], data[1], data[2], data[3], data[4], Double.parseDouble(data[5]), Integer.parseInt(data[6]), data[7]);
                faculty.instructors.add(instructor);
                System.out.printf("Instructor %s has been registered\n", name);
                BufferedWriter writer = new BufferedWriter(new FileWriter("instructors.txt", true));
                for (int i = 0; i < data.length; i++) {
                    writer.write(data[i]);
                    if (i < data.length - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
                writer.close();
            }
        } while (choice != 3);
    }
}
