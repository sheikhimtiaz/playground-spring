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

}
