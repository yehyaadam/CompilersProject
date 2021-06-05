/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author meshm
 */
public class FileManager {
    public String Location;
   
    public FileManager() {
    }

    
     public FileManager( String Location) {
         this.Location = Location;
    }

    
    
    
    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
    
    
    
    public void runProgram(){
        File file = null;
        
        try {
            file = new File(this.Location);
            LexerClass lexerClass = new LexerClass(file);
            List<Token> tokenList = lexerClass.generateTokens();
            System.out.println("\n\n\n");
            printTableNames();
            for (int i = 0; i < tokenList.size(); i++) {
                System.out.println(tokenList.get(i).toString());
            }
            System.err.println("Total Number Of Errors: " + lexerClass.totalNoErrors);
            new OutputTable(lexerClass.tokenList).setVisible(true);
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static String centerString (int width, String s) {
    return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
    
     public static void printTableNames(){
         System.out.println(
         centerString(4,"Line NO") 
                + "  \t" +  centerString(6, "Lexeme") 
                + "  \t" + centerString(30, "Token") 
                + "  "   +  centerString(2,"Lex#inLine") 
                + "  \t  " +  centerString(5, "Matchability"));
     }
    
    
}
