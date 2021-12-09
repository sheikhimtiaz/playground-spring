package com.imtiaz.springcrud.service;

import com.imtiaz.springcrud.entity.User;

import java.io.*;
import java.lang.NumberFormatException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CrudService {


    public static User addUser(User user) throws IOException {

        BufferedWriter bw = new BufferedWriter( new FileWriter("users.txt",true) );

        bw.write(user.id+","+user.name+","+user.email);
        bw.flush();
        bw.newLine();
        bw.close();
        return user;
    }

    public static User getUserById(int id) throws IOException{
        BufferedReader br = new BufferedReader( new FileReader("users.txt") );

        String record;

        while( ( record = br.readLine() ) != null ) {

            StringTokenizer st = new StringTokenizer(record,",");
            int number = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            String email = st.nextToken();
            System.out.println("|	"+number+"	"+name+" 		"+email+"      |");
            if(number == id){
                return new User(number, name, email);
            }

        }
        br.close();
        return null;
    }

    public static String updateUser(User user) throws IOException{

        File inputFile = new File("users.txt");
        File tempFile = new File("tempUsers.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        boolean exists = false;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            StringTokenizer st = new StringTokenizer(currentLine,",");
            int number = Integer.parseInt(st.nextToken());
            if(number == user.id) {
                exists = true;
                String name = user.name;
                String email = user.email;
                writer.write(number+","+name+","+email);
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
        if(exists == false){
            addUser(user);
            return "User has been added successfully";
        } else return "User has been updated successfully.";
    }

    public static String deleteUser(int id) throws IOException {
        File inputFile = new File("users.txt");
        File tempFile = new File("tempUsers.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        boolean exixts = false;
        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            StringTokenizer st = new StringTokenizer(currentLine,",");
            int number = Integer.parseInt(st.nextToken());
            if(number == id) {
                exixts = true;
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
        if(exixts){
            return "User has been deleted successfully.";
        } else return  "User does not exist in the database.";
    }

}
