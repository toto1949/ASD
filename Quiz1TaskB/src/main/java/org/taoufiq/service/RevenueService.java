package org.taoufiq.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.taoufiq.Model.Apartment;
import org.taoufiq.Model.Lease;

public class RevenueService {

    public double calculateTotalRevenue(List<Apartment> apartments) {
        return apartments.stream()
                .flatMap(a -> a.getLeases().stream())
                .mapToDouble(this::calculateLeaseRevenue)
                .sum();
    }

    private double calculateLeaseRevenue(Lease lease) {
        long months = ChronoUnit.MONTHS.between(lease.getStartDate(), lease.getEndDate());
        return months * lease.getMonthlyRent();
    }
}
