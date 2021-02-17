package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TeamService {

    public static Team getTheTeamWhoHasWorkedTogetherTheLongest(Map<EmployeePair, Team> teams) {
        TreeSet<Team> sortedTeams = new TreeSet<>(Comparator.comparing(Team::getDaysWorkingTogether));
        sortedTeams.addAll(teams.values());
        return sortedTeams.last();
    }

    public static Map<EmployeePair, Team> getAllTeamsWithOverlap(List<Record> records) {
        Map<EmployeePair, Team> teams = new HashMap<>();
        for (int i = 0; i < records.size() - 1; i++) {
            Record firstEmployee = records.get(i);
            for (int j = i + 1; j < records.size(); j++) {
                Record secondEmployee = records.get(j);

                if (hasThePairWorkedTogether(firstEmployee, secondEmployee)) {
                    Team updatedTeam = getTeamWithUpdatedOverlap(teams, firstEmployee, secondEmployee);
                    teams.put(updatedTeam.getPair(), updatedTeam);
                }
            }
        }
        return teams;
    }

    private static Team getTeamWithUpdatedOverlap(Map<EmployeePair, Team> teams, Record firstEmployee, Record secondEmployee) {
        EmployeePair pair = new EmployeePair(firstEmployee.getEmployeeId(), secondEmployee.getEmployeeId());
        long overlapDays = calculateOverlapForOneProjectOnly(firstEmployee, secondEmployee);
        if (teams.containsKey(pair)) {
            overlapDays += teams.get(pair).getDaysWorkingTogether();
        }
        return new Team(pair, overlapDays);
    }

    private static boolean hasThePairWorkedTogether(Record firstEmployee, Record secondEmployee) {
        return isNotTheSameEmployee(firstEmployee, secondEmployee) &&
                isTheSameProject(firstEmployee, secondEmployee) &&
                hasOverlap(firstEmployee, secondEmployee);
    }

    private static boolean isTheSameProject(Record firstEmployee, Record secondEmployee) {
        return firstEmployee.getProjectId() == secondEmployee.getProjectId();
    }

    private static boolean isNotTheSameEmployee(Record firstEmployee, Record secondEmployee) {
        return firstEmployee.getEmployeeId() != secondEmployee.getEmployeeId();
    }

    private static long calculateOverlapForOneProjectOnly(Record firstEmployee, Record secondEmployee) {
        LocalDate startDate = getStartDate(firstEmployee, secondEmployee);
        LocalDate endDate = getEndDate(firstEmployee, secondEmployee);

        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    private static LocalDate getEndDate(Record firstEmployee, Record secondEmployee) {
        return firstEmployee.getDateTo().isBefore(secondEmployee.getDateTo()) ?
                firstEmployee.getDateTo() : secondEmployee.getDateTo();
    }

    private static LocalDate getStartDate(Record firstEmployee, Record secondEmployee) {
        return firstEmployee.getDateFrom().isBefore(secondEmployee.getDateFrom()) ?
                secondEmployee.getDateFrom() : firstEmployee.getDateFrom();
    }

    private static boolean hasOverlap(Record firstEmployee, Record secondEmployee) {
        return (firstEmployee.getDateFrom().isBefore(secondEmployee.getDateTo()) ||
                firstEmployee.getDateFrom().isEqual(secondEmployee.getDateTo())) &&
                (secondEmployee.getDateTo().isAfter(firstEmployee.getDateFrom()) ||
                        secondEmployee.getDateTo().isEqual(firstEmployee.getDateFrom()));
    }
}

