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
public class LoginPanel {
    private static Scanner sc;
    private static String login;
    private static String password;
    private static Writer output;
    private static String path = "C:\\Users\\wojmo\\Documents\\NetBeansProjects\\ships\\ships\\ships\\users\\users.txt";
    private static File f = new File(path);
    private static LoginPanel instance;


    private LoginPanel() {}
    
    public static LoginPanel getInstance(){
        if(instance == null){
            instance = new LoginPanel();
        }
        return instance;
    }
    
    public static void setLoginData(String _login, String _password){
        login = _login;
        password = _password;
    }
    
    public static void openFile(){
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
    
    public static void createAccount(String login, String password) {
       File f = new File(path);
       LoginPanel.login = login;
       LoginPanel.password = password;
       if(checkUser()){
           System.out.println("Uzytkownik juz istnieje!");
           return;
       }
       try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append(LoginPanel.login + " " + LoginPanel.password);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
