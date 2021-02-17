package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Record {

    private final long employeeId;
    private final long projectId;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public Record(long employeeId, long projectId, LocalDate dateFrom) {
        this(employeeId, projectId, dateFrom, LocalDate.now());
    }

    public Record(long employeeId, long projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public long getProjectId() {
        return projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public static Record parseRecord(String[] line) {
        long employeeId = Long.parseLong(line[0]);
        long projectId = Long.parseLong(line[1]);
        try {
            LocalDate dateFrom = LocalDate.parse(line[2]);

            if (line[3].equalsIgnoreCase("NULL")) {
                return new Record(employeeId, projectId, dateFrom);
            } else {
                LocalDate dateTo = LocalDate.parse(line[3]);
                return new Record(employeeId, projectId, dateFrom, dateTo);
            }
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }
}
