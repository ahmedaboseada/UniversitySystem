package org.university;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Employee extends Person {
    Scanner in = new Scanner(System.in);
    protected User user;
    private String rank;
    private double salary;
    private final double additionalSalary = 0.1;
    private final double initialSalary = 2000;
    private final int maxVacationDaysInMonth = 4;
    private int vacationDays;
    private String department;

    private static int numberOfEmployees;

    public Employee(String name, String email, String phone, String ID, String rank, double salary, int vacationDays, String department) {
        super(name, email, phone, ID);
        this.rank = rank;
        this.salary = salary;
        this.vacationDays = vacationDays;
        this.department = department;
        numberOfEmployees++;
    }

    protected double yearlyIncentives;

    public String getRank() {
        return rank;
    }

    public double getSalary() {
        return salary;
    }

    public int getVacationDays() {
        return vacationDays;
    }


    public void setSalary(double salary) {
        if (salary >= initialSalary) {
            this.salary = salary;
        } else System.out.printf("Initial salary is %s", initialSalary);
    }

    public void setVacationDays(int vacationDays) {
        if (vacationDays <= maxVacationDaysInMonth) {
            this.vacationDays = vacationDays;
        } else System.out.printf("Max vacation days for employee is %s", maxVacationDaysInMonth);
    }

    public void salaryAfterIncentive() {
        setSalary(salary + (salary * additionalSalary));
    }

    public double getYearlyIncentives() {
        return yearlyIncentives;
    }

    @Override
    public boolean authenticate(String username, String password){
        final String FILE_NAME = "users.txt";
        String line;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            while((line= reader.readLine())!=null) {
                String[] parts=line.split(",");
                if (username.equals(parts[0]) && password.equals(parts[1])) {
                    if(parts[0].equals(user.getUsername())&&parts[1].equals(user.getPassword()))
                    return true;
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
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

    public String getDepartment() {
        return department;
    }
}
