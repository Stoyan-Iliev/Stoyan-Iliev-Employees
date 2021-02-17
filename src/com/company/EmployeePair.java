package com.company;

import java.util.Objects;

public class EmployeePair {
    private final long firstEmployeeId;
    private final long secondEmployeeId;

    public EmployeePair(long firstEmployeeId, long secondEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
        this.secondEmployeeId = secondEmployeeId;
    }

    public long getFirstEmployeeId() {
        return firstEmployeeId;
    }

    public long getSecondEmployeeId() {
        return secondEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePair that = (EmployeePair) o;
        return (firstEmployeeId == that.firstEmployeeId && secondEmployeeId == that.secondEmployeeId) ||
                (firstEmployeeId == that.secondEmployeeId && secondEmployeeId == that.firstEmployeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstEmployeeId, secondEmployeeId) + Objects.hash(secondEmployeeId, firstEmployeeId);
    }
}
