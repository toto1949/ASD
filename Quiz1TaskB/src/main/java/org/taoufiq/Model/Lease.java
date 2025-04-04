package org.taoufiq.Model;


import java.time.LocalDate;

public class Lease {
    private long leaseId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double monthlyRent;
    private String tenantName;
    private String apartmentNo;

    public Lease(long leaseId, LocalDate startDate, LocalDate endDate, double monthlyRent, String tenantName, String apartmentNo) {
        this.leaseId = leaseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyRent = monthlyRent;
        this.tenantName = tenantName;
        this.apartmentNo = apartmentNo;
    }

    // Getters and Setters
    public long getLeaseId() { return leaseId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getMonthlyRent() { return monthlyRent; }
    public String getTenantName() { return tenantName; }
    public String getApartmentNo() { return apartmentNo; }
}
