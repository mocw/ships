/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

/**
 *
 * @author wojmo
 */
public class UserData {
    private static Scanner sc;
    private static Scanner in = new Scanner(System.in);
    private static String login;
    private static String password;
    private static Writer output;
    private static String path = "C:\\Users\\wojmo\\Documents\\NetBeansProjects\\ships\\ships\\ships\\users\\users.txt";
    private static File f = new File(path);
    private static UserData instance;


    private UserData() {}
    
    public static UserData getInstance(){
        if(instance == null){
            instance = new UserData();
        }
        return instance;
    }
    
    private static void openFile(){
        try {
             sc =new Scanner (f);
          }
      catch(Exception e){
      System.out.println("couldn't find file");
      }
    }
    
    public static boolean checkUserAndPassword(){
        openFile();
        String temp;
        String[] info;
        while(sc.hasNext()){
            temp = sc.nextLine();
            info = temp.split(" ");
            
            if(info[0].equals(login) && info[1].equals(password)){
                return true;
            }
        }
       return false;
    }
    
    public static void displayDataPanel(){
        System.out.print("Uzytkownik: ");
        UserData.login = in.nextLine();
        System.out.print("Hasło: ");
        UserData.password = in.nextLine();
    }
    
    public static boolean checkUser(){
        openFile();
        String temp;
        String[] info;
        while(sc.hasNext()){
            temp = sc.nextLine();
            info = temp.split(" ");
            
            if(info[0].equals(login)){
                return true;
            }
        }
       return false;
    }
    
    public static void createAccount() {
       File f = new File(path);
       if(checkUser()){
           System.out.println("Uzytkownik juz istnieje!");
           return;
       }
       try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append(UserData.login + " " + UserData.password);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
