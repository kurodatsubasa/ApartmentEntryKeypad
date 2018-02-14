/*
 * Hai Le, Scott Mitchell
 * 02/27/18
 * Program 3
 * The purpose of this program is to simulate a keypad at apartment entrance.
 */
package program3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO {

    public static void readFile(String fileName, ApartmentRecords list) {
        // reads the file using Java 7 Paths and Files
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                list.add(currentLine);
            }
        } catch (IOException e) {
            System.out.println(e);
        } // end try catch
    } // end readFile
}
