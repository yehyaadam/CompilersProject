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
        String Location = "input.txt";
         
        //URL Input_url = ClassLoader.getSystemResource("input.txt");
        //System.out.println(Input_url);
        
        
        File file = null;

        
        
        
        
        try {
            file = new File(Location);
            LexerClass lexerClass = new LexerClass(file);
            List<Token> tokenList = lexerClass.generateTokens();
            for (int i = 0; i < tokenList.size(); i++) {
                System.out.println(tokenList.get(i).toString());
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }

        
    }
    
}
