package com.company;

import java.util.Objects;

public class Team {

    private final EmployeePair pair;
    private final long daysWorkingTogether;

    public Team(EmployeePair pair, long daysWorkingTogether) {
        this.pair = pair;
        this.daysWorkingTogether = daysWorkingTogether;
    }

    public EmployeePair getPair() {
        return pair;
    }

    public long getDaysWorkingTogether() {
        return daysWorkingTogether;
    }

    @Override
    public String toString() {
        return "Employees with id " +
                pair.getFirstEmployeeId() +
                " and " + pair.getSecondEmployeeId() +
                " has worked together for a total of " + daysWorkingTogether +
                " days.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return daysWorkingTogether == team.daysWorkingTogether && Objects.equals(pair, team.pair);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pair, daysWorkingTogether);
    }
}
