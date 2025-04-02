package org.taoufiq.models;


import java.time.LocalDate;
import java.util.Optional;

public class Employee {
    private long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private double yearlySalary;
    private Optional<PensionPlan> pensionPlan;

    public Employee(long employeeId, String firstName, String lastName, LocalDate employmentDate, double yearlySalary, PensionPlan pensionPlan) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
        this.pensionPlan = Optional.ofNullable(pensionPlan);
    }

    public boolean isEligibleForPension(LocalDate nextQuarterStart, LocalDate nextQuarterEnd) {
        return employmentDate.plusYears(3).isAfter(nextQuarterStart) &&
                employmentDate.plusYears(3).isBefore(nextQuarterEnd) &&
                pensionPlan.isEmpty();
    }

    public long getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getEmploymentDate() { return employmentDate; }
    public double getYearlySalary() { return yearlySalary; }
    public Optional<PensionPlan> getPensionPlan() { return pensionPlan; }
}
