package com.company;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Record> records = EmployeeRecordFileReader.getRecordsFromFile(getFilePath());

        Map<EmployeePair, Team> teams = TeamService.getAllTeamsWithOverlap(records);

        if (!teams.isEmpty()) {
            Team teamWorkingLongestTogether = TeamService.getTheTeamWhoHasWorkedTogetherTheLongest(teams);

            System.out.println("The employees with id " +
                    teamWorkingLongestTogether.getPair().getFirstEmployeeId() + " and " +
                    teamWorkingLongestTogether.getPair().getSecondEmployeeId() +
                    " has worked together the longest for a total of " +
                    teamWorkingLongestTogether.getDaysWorkingTogether() + " days.");
        } else {
            System.out.println("There are no pair of employees working together on the same projects at the same time.");
        }
    }

    private static String getFilePath() {
        System.out.println("Please enter the location of the file in this format - D:\\folderName\\fileName.txt");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
