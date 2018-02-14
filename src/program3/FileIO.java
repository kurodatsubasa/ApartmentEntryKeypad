/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
