/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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
    private static String usersPath = "C:\\Users\\wojmo\\Documents\\NetBeansProjects\\ships\\ships\\ships\\users\\users.txt";
    private static String statsPath = "C:\\Users\\wojmo\\Documents\\NetBeansProjects\\ships\\ships\\ships\\users\\userStats.txt";
    private static File usersFile = new File(usersPath);
    private static File statsFile = new File(statsPath);
    private static UserData instance;

    private UserData() {}
    
    public static UserData getInstance(){
        if(instance == null){
            instance = new UserData();
        }
        return instance;
    }
    
    private static void openUSersFile(){
        try {
             sc =new Scanner (usersFile);
          }
      catch(Exception e){
      System.out.println("couldn't find file");
      }
    }
    
    private static void openStatsFile(){
        try {
             sc =new Scanner (statsFile);
          }
      catch(Exception e){
      System.out.println("couldn't find file");
      }
    }
    
    public static boolean checkUserAndPassword(){
        openUSersFile();
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
        openUSersFile();
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
       File f = new File(usersPath);
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
    
    public static void displayProfile(){
        System.out.println("Profil użytkownika " + UserData.login);
        System.out.println("1. Zmień nick");
        System.out.println("2. Zmień hasło");
        System.out.println("3. Statystyki");
        char ch = in.next().charAt(0);
        switch(ch){
            case '1':
                UserData.changeNick();
                break;
            case '2':
                UserData.changePassword();
                break;
            case '3':
                UserData.displayStats();
                break;
            default:
                System.out.println("Nieprawidłowy wybór!");
                displayProfile();
                break;
        }
    }
    
    private static void changeNick() {
        String nickname;
        System.out.println("Podaj nowy nick:");
        nickname = in.next();
         openUSersFile();             
          try {
        BufferedReader file = new BufferedReader(new FileReader(usersPath));
        StringBuffer inputBuffer = new StringBuffer();
        String line;

        while ((line = file.readLine()) != null) {
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        file.close();
        String inputStr = inputBuffer.toString();
        inputStr = inputStr.replace(UserData.login, nickname);
        FileOutputStream fileOut = new FileOutputStream(usersPath);
        fileOut.write(inputStr.getBytes());
        fileOut.close();
        }
     catch (Exception e) {
        System.out.println("Problem z odczytem pliku.");
        }          
    }
    
    private static void changePassword() {
       String passwd;
        System.out.println("Podaj nowe haslo:");
        passwd = in.next();
         openUSersFile();             
          try {
        BufferedReader file = new BufferedReader(new FileReader(usersPath));
        StringBuffer inputBuffer = new StringBuffer();
        String line;

        while ((line = file.readLine()) != null) {
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        file.close();
        String inputStr = inputBuffer.toString();
        inputStr = inputStr.replace(UserData.password, passwd);
        FileOutputStream fileOut = new FileOutputStream(usersPath);
        fileOut.write(inputStr.getBytes());
        fileOut.close();
        }
     catch (Exception e) {
        System.out.println("Problem z odczytem pliku.");
        }          
  }
    
   public static void saveStats(Player player){
       openStatsFile();
       File f = new File(statsPath);
       try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append(UserData.login + " " + player.getShotHit() + " " + player.getShotMissed() + " "
                    + player.getDate());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
   }
   
   private static void displayStats(){
       openStatsFile();
        String temp;
        String[] info;
        boolean statsExists = false;
        try{
         while(sc.hasNext()){
            temp = sc.nextLine();
            info = temp.split(" ");
            
            if(info[0].equals(login)){
                statsExists = true;
                System.out.println(" Data: " + info[3] + " " + info[4] + "Strzały trafione: " + info[1] + 
                        ", strzały chybione: " + info[2]);
            }
        }
        if(!statsExists) {
            System.out.println("Nie posiadasz jeszcze statystyk!");
        }
        displayProfile();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Info index out of bound!");
            displayProfile();
        }
   }
}
