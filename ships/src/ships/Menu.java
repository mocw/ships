/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import java.util.Scanner;

/**
 *
 * @author wojmo
 */
public class Menu {
    private static Scanner sc = new Scanner(System.in);
    
    private static void displayLoginPanel(){
        System.out.println("LOGOWANIE");
        UserData.getInstance();
        UserData.displayDataPanel();
        if(UserData.checkUserAndPassword()){
            Menu.displayMainMenu();
        } else {
            System.out.println("Nie ma takiego użytkownika lub hasło jest nieprawidłowe! Spróbować ponownie? (T/N)");
            char ch = sc.next().charAt(0);
            switch(ch){
                case 'T':
                case 't':
                    Menu.displayLoginPanel();
                    break;
                case 'N':
                case 'n':
                    Menu.displayLoginRegisterChoose();
                    break;
            }
        }
    }
    
    private static void displayMainMenu() {
        System.out.println("Tutaj będzie główne menu!");
    }
    
    private static void displayRegisterPanel() {
         System.out.println("REJESTRACJA");
        UserData.getInstance();
        UserData.displayDataPanel();
        if(UserData.checkUser()){
            System.out.println("Taki użytkownik już istnieje! Jeśli chcesz spróbować ponownie,"
                    + "wciśnij T");
            char wybor = sc.next().charAt(0);
            switch(wybor){
                case 'T':
                case 't':
                    Menu.displayRegisterPanel();
                break;
                default:
                    Menu.displayLoginRegisterChoose();
                    break;
            }
        } else {
            UserData.createAccount();
            Menu.displayLoginPanel();
        }
    }
        
    public static void displayLoginRegisterChoose(){
        System.out.println("Witaj w grze statki. Wybierz co chcesz zrobić:");
        System.out.println("1 - ZALOGUJ SIE");
        System.out.println("2 - REJESTRACJA");
        char wybor = sc.next().charAt(0);
        switch(wybor){
            case '1':
                displayLoginPanel();
                break;                
            case '2':
                displayRegisterPanel();                
                break;
            default:
                System.out.println("Nieprawidlowy wybor!");
                break;
        }
    }
}