package edu.cscc.csci2469.midterm2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Class CountWords reads a text file and provides the word count for each word in the file. 
 * @author kwelzbacher
 *
 */
public class CountWords
{
    
    /**
     * Variable map as a new TreeMap with String and Integer key values.
     */
    private static TreeMap<String, Integer> map = new TreeMap<>();
    
    /**
     * Method cleanupText converts uppercase characters to lowercase and replaces any non-letter character with a space.
     * @param line String that is read in the text file
     * @return String of lowercase characters
     */
    private static String cleanupText(final String line)
    {
        final StringBuilder sb = new StringBuilder();
        for (final char c : line.toLowerCase().toCharArray())
        {
            if (c == ' ' || Character.isLetter(c))
            {
                sb.append(c);
            }
            else
            {
                sb.append(' ');
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Method readFile reads the text file provided and counts the frequency of each word in the file.
     * @param fileName The text file that is to be read.
     * @throws FileNotFoundException is thrown if the fileName is not found.
     * @throws IOException is thrown if failed or interrupted I/O operations.
     */
    private static void readFile(final String fileName)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            String[] words;
            while ((line = br.readLine()) != null)
            {
                words = cleanupText(line).trim().split(" ");
                for (final String word : words)
                {
                    if (word.trim().length() == 0)
                    {
                        continue;
                    }
                    
                    //find each word and count number of times used.
                    if(map.containsKey(word))
                    {
                        int count = map.get(word);
                        count++;
                        map.put(word, count);
                    } else {
                        map.put(word, 1);
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found - " + fileName);
            System.exit(1);
        }
        catch (IOException e)
        {
            System.out.println("I/O error - " + fileName);
            System.exit(1);
        }
    }
    
    /**
     * Method printWordCounts prints each word found in the text file and its frequency.
     */
    public static void printWordCounts()
    {
        //create iterator 
        Iterator<String> values = map.keySet().iterator();
        //print out each word and its frequency
        while(values.hasNext())
        {
            String key = values.next();
            System.out.println(" " + map.get(key) + "    : " + key);
        }
    }
    
    /**
     * Main Method to make sure file name is specified and calls readFile() and printWordCounts() methods.
     * @param args Array of strings found in Main Method.
     */
    public static void main(String[] args)
    {
        if (args.length != 1) 
        {
            System.out.println("File name must be specified!");
            System.exit(1);
        }
        readFile(args[0]);
        printWordCounts();
    }
}
