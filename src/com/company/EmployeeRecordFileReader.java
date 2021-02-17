package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeRecordFileReader {

    public static final int CORRECT_NUMBER_OF_STRINGS_SEPARATED_WITH_SPACE_PER_LINE = 4;

    public static List<Record> getRecordsFromFile(String path){
        Scanner scanner = getScannerFromThePath(path);

        List<Record> records = new ArrayList<>();
        int counter = 1;
        while(scanner.hasNext()){
            String[] line = scanner.nextLine().split(", ");

            if(isLineCorrect(line)){
                Record record = Record.parseRecord(line);
                if(record != null){
                    records.add(record);
                }
            } else {
                System.out.println("The record on line " + counter + " is in wrong format and as such it is skipped");
            }

            counter++;
        }

        scanner.close();
        return records;
    }

    private static boolean isLineCorrect(String[] line) {
        return line.length == CORRECT_NUMBER_OF_STRINGS_SEPARATED_WITH_SPACE_PER_LINE;
    }

    private static Scanner getScannerFromThePath(String path) {
        try {
            return new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            System.out.println("The file is missing or the location you have entered is incorrect. " +
                    System.lineSeparator() + "Please try again.");
            System.exit(0);
        }
        return null;
    }
}
