/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Yehya Adam
 */
public class LexicalAnalyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        //URL Input_url = ClassLoader.getSystemResource("input.txt");
        //System.out.println(Input_url);
        FileManager.Location = "input.txt";

        FileManager.runProgram();

        
    }

    
    
}
