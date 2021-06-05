/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Yehya Adam
 */
public class LexerClass {

    int line = 1, wordNo = 0;
    public static int totalNoErrors = 0;
    BufferedReader reader;
    char current;
    String word = "";
    public static ArrayList<String> errors = new ArrayList<>();
    ArrayList<Token> tokenList = new ArrayList<>();
    public static final String KEY_WORDS[][] = new String[][]{
        {"Divisio", "Class"},
        {"InferedFrom", "Inheritance"},
        {"WhetherDo", "Condition"},
        {"Else", "Condition"},
        {"Ire", "Integer"},
        {"Sire", "SInteger"},
        {"Clo", "Character"},
        {"SetOfClo", "String"},
        {"FBU", "Float"},
        {"SFBU", "SFloat"},
        {"NoneValue", "Void"},
        {"TerminateThisNow", "Break"},
        {"RingWhen", "Loop"},
        {"BackedValue", "Return"},
        {"STT", "Struct"},
        {"Check", "Switch"},
        {"CaseOf", "Switch"},
        {"Beginning", "Start Statement"},
        {"End", "End Statement"},
        {"Using", "Inclusion"},};

    public static final String Operators[][]
            = {
                {"+", "Arithmetic Operation"},
                {"-", "Arithmetic Operation"},
                {"*", "Arithmetic Operation"},
                {"/", "Arithmetic Operation"},
                {"&&", "Logic operator"},
                {"||", "Logic operator"},
                {"~", "Logic operator"},
                {"==", "relational operator"},
                {"<", "relational operator"},
                {">", "relational operator"},
                {"!=", "relational operator"},
                {">=", "relational operator"},
                {"<=", "relational operator"},
                {"=", "Assignment operator"},
                {".", "Access Operator"},
                {"{", "Left curly Brace"},
                {"}", "Right curly Braces"},
                {"[", "Left Braces"},
                {"]", "Right Braces"},
                {"(", "Left Parenth"},
                {")", "Right Parenth"},
                {"'", "Quotation Mark"},
                {"\"", "Quotation Mark"},
                {"/#", "Comment"},
                {"#/", "Comment"},
                {"/-", "Single line Comment"},
                {"@", "Token Delimiter"},
                {";", "Line Delimiter"},
                {",", "Comma"},};

    public LexerClass() {
    }

    public LexerClass(File file) {

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        current = readNextChar();
    }

    ArrayList<Token> generateTokens() {
        Token token = readNextToken();
        while (token != null) {
            tokenList.add(token);
            token = readNextToken();
        }
        return tokenList;

    }

    Token readNextToken() {
        int state = 0;
        word = "";
        while (true) {
            if (current == (char) (-1)) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            switch (state) {
                case 0: {
                    if (current == (char) (-1)) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                    // Ignore white space & Tabs & New line
                    if (current == ' ' || current == '\n' || current == '\t' || current == '\r') {
                        if (current == '\n') {
                            {
                                line++;
                                wordNo = 0;
                            }
                        }
                        current = readNextChar();
                    } // S1 identifier 
                    else if (isLetter(current) || current == '_') {
                        state = 1;
                        break;
                    } // S2 relational operators
                    else if (current == '<' || current == '>' || current == '=') {
                        state = 2;
                        break;
                    } //S4 Assignment operator || relational operators
                    else if (current == '!') {
                        state = 4;
                        break;
                    }
                    //S6 Arithmetic Operation
                    else if (current == '+' || current == '-' ||
                            current == '*' || current == '~' || 
                            current == '"' || current == '\'' ||
                            current == '@' || current == ',' ||
                            current == '{' || current == '}' ||
                            current == '[' || current == ']' ||
                            current == '(' || current == ')' ||
                            current == ';'){
                        state = 6;
                        break;
                    } //S7 Logic operators
                    else if (current == '&') {
                        state = 7;
                        break;
                    } //S9 Logic operators
                    else if (current == '|') {
                        state = 9;
                        break;
                    }//S10 Arithmetic Operation || Comment
                    else if (current == '/') {
                        state = 10;
                        break;
                    }// S11 Constant
                    else if (isNumber(current)) {
                        state = 11;
                        break;
                    }// S12 Comment
                    else if (current == '#') {
                        state = 12;
                        break;
                    }
                }
                break;
                //State 1
                case 1: // Ire --> integer , Divisio --> class 
                {
                    word = word + current;
                    current = readNextChar();
                    while (true) {
                        if (isLetter(current) || current == '_' || isNumber(current)) {
                            word = word + current;
                            current = readNextChar();
                        } else {
                            if (ifFoundKeyWord(word).equals("")) {
                                return new Token(line, "Identifier", word, ++wordNo);
                            } else {
                                if (word.equals("Using")) {
                                    String anotherFilePath = "";

                                    while (true) {
                                        //System.out.println(current);
                                        if (current == '\n') {
                                            state = 0;
                                            break;
                                        } else if (isLetter(current) || current == '.') {
                                            anotherFilePath = anotherFilePath + current;
                                        }
                                        current = readNextChar();
                                    }

                                    LexicalAnalyzer.run(anotherFilePath);
                                }
                                return new Token(line, ifFoundKeyWord(word), word, ++wordNo);
                            }
                        }
                    }

                }

                //State 2
                case 2: {
                    word = word + current;
                    current = readNextChar();
                    if (current == '=') {
                        word = word + current;
                        state = 3;
                        break;
                    }
                    return new Token(line, ifFoundOperators(word), word, ++wordNo);
                }
                // State 3
                case 3: {
                    current = readNextChar();
                    return new Token(line, ifFoundOperators(word), word, ++wordNo);
                }
                //State 4
                case 4: {
                    word = word + current;
                    current = readNextChar();
                    if (current == '=') {
                        word = word + current;
                        current = readNextChar();
                        return new Token(line, ifFoundOperators(word), word, ++wordNo);
                    } else {
                        state = 99;
                    }
                }
                break;

                case 6: {
                    word = word + current;
                    current = readNextChar();
                    return new Token(line, ifFoundOperators(word), word, ++wordNo);
                }
                //State 7
                case 7: {
                    word = word + current;
                    current = readNextChar();
                    if (current == '&') {
                        word = word + current;
                        return new Token(line, ifFoundOperators(word), word, ++wordNo);
                    } else {
                        state = 99;
                    }
                }
                break;
                //State 9
                case 9: {
                    word = word + current;
                    current = readNextChar();
                    if (current == '|') {
                        word = word + current;
                        return new Token(line, ifFoundOperators(word), word, ++wordNo);
                    } else {
                        state = 99;
                    }
                }
                break;
                //State 10
                case 10: {
                    word = word + current;

                    current = readNextChar();
                    if (current == '-') {
                        word = word + current;
                        while (true) {
                            current = readNextChar();
                            if (current == '\n') {
                                state = 0;
                                break;
                            }
                        }
                        return new Token(line, ifFoundOperators(word), word, ++wordNo);
                    } else if (current == '#') {
                        word = word + current;
                        state = 0;
                        return new Token(line, ifFoundOperators(word), word, ++wordNo);

                    } else {
                        return new Token(line, ifFoundOperators(word), word, ++wordNo);
                    }
                }
                //S21 Constant Numbers
                case 11: {
                    word = word + current;
                    current = readNextChar();
                    if (isNumber(current)) {
                        word = word + current;
                        current = readNextChar();
                        while (true) {
                            if (isNumber(current)) {
                                word += current;
                                current = readNextChar();
                            } else {
                                return new Token(line, "Constant", word, ++wordNo);
                            }
                        }
                    } else if (isLetter(current)) {
                        state = 99;
                        break;
                    } else {
                        return new Token(line, "Constant", word, ++wordNo);
                    }
                }

                //Multiple line comment
                case 12: {
                    word = word + current;
                    current = readNextChar();
                    while (true) {
                        if (current != '/') {
                            current = readNextChar();
                            continue;
                        } else if (current == '/') {
                            word = word + current;
                            current = readNextChar();
                            return new Token(line, ifFoundOperators(word), word, ++wordNo);
                        }
                    }
                }

                // Error State
                case 99: {
                    totalNoErrors++;
                    word = word + current;
                    current = readNextChar();
                    while (true) {
                        if (isLetter(current) || current == '_' || isNumber(current)) {
                            word = word + current;
                            current = readNextChar();
                        } else {
                            errors.add(word);
                            System.out.println(errors.get(0));
                            
                            return new Token(line, "Invalid", word, ++wordNo, "Not Matched");
                        }
                    }
                }

                default: {
                    current = readNextChar();
                    state = 0;
                }

            }
        }
    }

    char readNextChar() {
        try {
            return (char) reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (char) (-1);
    }

    boolean isNumber(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }

        return false;
    }

    boolean isLetter(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }

        return false;

    }

    String ifFoundKeyWord(String Word) {
        String Result = "";
        for (int i = 0; i < KEY_WORDS.length; i++) {
            if (Word.equals(KEY_WORDS[i][0])) {
                Result = KEY_WORDS[i][1];
            }
        }
        return Result;
    }

    String ifFoundOperators(String word) {
        String Result = "";
        for (int i = 0; i < Operators.length; i++) {
            if (word.equals(Operators[i][0])) {
                Result = Operators[i][1];
            }
        }
        return Result;
    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

}
