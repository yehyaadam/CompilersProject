/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

/**
 *
 * @author Yehya Adam
 */
public class Token {
    int lineNo;
    int WordNo;
    String Match = "Matched";
    String token;
    String lexeme;

    public Token(String token, String lexeme) {
        this.token = token;
        this.lexeme = lexeme;
    }

    public Token(int lineNo, String token, String lexeme) {
        this.lineNo = lineNo;
        this.token = token;
        this.lexeme = lexeme;
    }

    public Token(int LineNo , String token , String lexeme , int WordNo ) {
        this.lineNo = LineNo;
        this.lexeme = lexeme;
        this.token = token;
        this.WordNo = WordNo;
    }
    
    public Token(int LineNo , String token , String lexeme , int WordNo , String Match ) {
        this.lineNo = LineNo;
        this.lexeme = lexeme;
        this.token = token;
        this.WordNo = WordNo;
        this.Match = Match;
    } 

    public int getLineNo() {
        return lineNo;
    }

    public int getWordNo() {
        return WordNo;
    }

    public String getMatch() {
        return Match;
    }

    public String getToken() {
        return token;
    }

    public String getLexeme() {
        return lexeme;
    }

    public Token() {
    }

    
    
    @Override
    public String toString() {
        return  " | " + centerString(2,String.valueOf(lineNo)) 
                + "| \t |" +  centerString(17, lexeme) 
                + "| \t |" +  centerString(25, token) 
                + "| \t   |"   +  centerString(3,String.valueOf(WordNo)) 
                + "| \t |"   +  centerString(12, Match)
                +"| ";

    }
    
    public String centerString (int width, String s) {
    return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
    
}
