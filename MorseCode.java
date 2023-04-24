/*
 * CSC1 2300
 * Project 3 - Morse Code
 * Java program that encodes English and decodes Morse code
 * by Ana Belen Ortiz
 * 26/3/23
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class MorseCode {
    private HashMap<Character, String> english2Morse;
    private HashMap<String, Character> morse2English;
    
    public MorseCode(String morseTableFileName) throws FileNotFoundException { // Exception thrown
        english2Morse = new HashMap<Character, String>();
        morse2English = new HashMap<String, Character>();

        File file = new File(morseTableFileName); // Creating a file object with the specified file name 
        try {
            Scanner scanner = new Scanner(file); // Creating a scanner object to read the file 
            while (scanner.hasNext()) { // Looping through each line of the file
                String line = scanner.next(); // Reading the next line of the file
                if (line.equals(" ")) // If line is a blank space, skip and continue
                    continue;
                String Morse = scanner.next();
                morse2English.put(Morse, line.charAt(0)); // Map morse code string to corresponding English char and store it in morse2English map
                english2Morse.put(line.charAt(0), Morse); // Map English char to corresponding Morse code string and store it in english2Morse map 
            }
            scanner.close(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Prints the stack trace of the exception 
        }
    }
        
    // Getters
    public String getMorse(char letter) {
        return english2Morse.get(letter);
    }

    public char getEnglish(String MorseLetter) {
        return morse2English.get(MorseLetter);
    }
}