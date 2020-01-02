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
public class Ships {

    private static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginPanel.getInstance();
        LoginPanel.createAccount("testikowo", "haselko");
    }
    
}
