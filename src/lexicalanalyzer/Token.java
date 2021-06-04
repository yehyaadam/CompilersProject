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
    String Match;
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

    public Token(int LineNo , String lexeme , String token , int WordNo , String Match) {
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
        return  "lineNo: " + lineNo + "\t" + "lexeme: " + lexeme + "\t" +"token: " + token ;
    }
}
//    String formateOutPut( int line , String l,String t){
//        String outPut=l;
//        for(int i=l.length() ; i<16 ; i++){
//            outPut+=' ';
//        }
//        return outPut+token;
//    }
//
//}