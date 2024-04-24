package org.university;

import java.io.*;
import java.util.*;


public class User {
    private String username;
    private String password;
    private static int numofusers;
    private int counter;
    Scanner in = new Scanner(System.in);
    private static final String FILE_NAME = "users.txt";
    protected String id;


    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public User() {
        numofusers++;
        counter = numofusers;
    }

    public void setPassword() {
        System.out.println("Please, Enter password");
        this.password = in.next();
    }

    public void setPasswordUniversity(String password) {
        this.password = password;
    }

    public void setUsername() {
        System.out.println("Please, Enter username");
        this.username = in.next();
    }

    public void setUsernameUniversity(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void newUserData() {
        setUsername();
        setPassword();
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    public void register() throws IOException {
        String username, password;
        Scanner in = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        boolean hasAUser = false;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[2].equals(id)) {
                this.username = parts[0];
                this.password = parts[1];
                hasAUser = true;
                return;
            } else hasAUser = false;
        }
        if (hasAUser == false) {
            boolean usernameExists;
            do {
                System.out.println("Enter username");
                username = in.next();
            BufferedReader reader2 = new BufferedReader(new FileReader(FILE_NAME));
            String line2;
                usernameExists = false;
                while ((line2 = reader2.readLine()) != null) {
                    String[] parts = line2.split(",");
                    if (parts[0].equals(username)) {
                        System.out.println("Username already exists");
                        usernameExists = true;
                        break;
                    }
                }
                reader.close();
            } while (usernameExists);

            this.username = username;

            System.out.println("Enter password");
            System.out.println("should be 8 characters or more");
            password = in.next();
            while (!setPassword(password)) {
                System.out.println("Invalid password. Please try again.");
                password = in.next();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            writer.write(this.username + "," + this.password + "," + id);
            writer.newLine();
            writer.close();
        }
    }


    private boolean setPassword(String password) {
        while (password.length() < 8) {
            System.out.println("Enter password");
            System.out.println("Password length should be 8 or more");
            return false;
        }
        this.password = password;
        return true;
    }

}
