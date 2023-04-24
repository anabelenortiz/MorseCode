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
import java.util.Arrays;

public class MorseEncode {
    private String message;
    private MorseCode morseCode;

    public MorseEncode(String morseTableFileName, String message) throws FileNotFoundException {
        this.message = message; 
        this.morseCode = new MorseCode(morseTableFileName); // Create a new MorseCode object using the file name 
    }

    public String encode() {
        String m = message.toUpperCase();
        StringBuilder encodedMessage = new StringBuilder(); // Create a StringBuilder to store the encoded message 
        for (int i = 0; i < m.length(); i++) { // Loops through each character in the message
            char c = m.charAt(i); // gets the current character
            if (c == ' ') { // Checks if character is a space. If yes: add three spaces to represent a word gap
                encodedMessage.append("   ");
            } else {
                String morseCodeLetter = morseCode.getMorse(c); // Otherwise, get the Morse code representation of the character
                if (morseCodeLetter != null) {
                    encodedMessage.append(morseCodeLetter).append(" "); // Add it to the encoded message with a space after it 
                }
            }
        }
        return encodedMessage.toString().trim(); // return the enconded message as a String, trimming any extra whitespace at the end
    }

    public String decode() {
        StringBuilder decodedMessage = new StringBuilder(); // Create a StringBuilder to store the decoded message
        String[] words = message.split("   "); // Split message into words using "   " as the delimiter
        for (String word : words) { // Loop through all words
            String[] letters = word.split(" "); // Split each word into letters using " " as the delimeter
            for (String letter : letters) { // Loops through all letters
                if (letter.isEmpty())
                    continue;
                else {
                    char englishLetter = morseCode.getEnglish(letter); // Get corresponding English letter from the Morse code dictionary and appended to decoded message 
                    decodedMessage.append(englishLetter); 
                }
            }
            decodedMessage.append(" "); // After all letters in a word have been processed, append a space to the decoded message to separate the words
        }
        return decodedMessage.toString().trim(); // Return decoded message as a string with leading spaces removed
    }

    public static void main(String[] args) throws Exception{
        String morseTableFileName = "MorseTable.txt";
        String phraseToEncode = "Cryptography is the practice of securing communication and data by converting it into a form that can only be read by authorized individuals.";

        MorseEncode encoder = new MorseEncode(morseTableFileName, phraseToEncode);
        String encodedMessage = encoder.encode();
        System.out.println("Encoded message: " + encodedMessage);

        MorseEncode decoder = new MorseEncode(morseTableFileName, encodedMessage);
        String decodedMessage = decoder.decode();
        System.out.println("Decoded message: " + decodedMessage);
    }
}