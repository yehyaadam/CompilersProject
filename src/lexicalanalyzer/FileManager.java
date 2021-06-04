/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.io.File;
import java.util.List;

/**
 *
 * @author meshm
 */
public class FileManager {
    public static String Location;

    public FileManager() {
    }

    
     public FileManager( String Location) {
         FileManager.Location = Location;
    }

    
    
    
    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        FileManager.Location = Location;
    }
    
    
    
    public static void runProgram(){
        File file = null;
        
        try {
            file = new File(FileManager.Location);
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
