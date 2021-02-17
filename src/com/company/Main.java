package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Record> records = EmployeeRecordFileReader.getRecordsFromFile(getFilePath());

        Map<EmployeePair, Team> teams = TeamService.getAllTeamsWithOverlap(records);

        if (!teams.isEmpty()) {
            Team teamWorkingLongestTogether = TeamService.getTheTeamWhoHasWorkedTogetherTheLongest(teams);
            System.out.println("The people who has worked together the longest are:");
            System.out.println(teamWorkingLongestTogether);
        } else {
            System.out.println("There are no pair of employees working together on the same projects at the same time.");
        }
    }

    private static String getFilePath() {
        System.out.println("Please enter the location of the file in this format D:\\folderName\\fileName.txt");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
