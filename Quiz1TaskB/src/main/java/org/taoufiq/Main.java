package org.taoufiq;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.taoufiq.Model.Apartment;
import org.taoufiq.Model.Lease;
import org.taoufiq.service.ApartmentService;
import org.taoufiq.service.RevenueService;
import org.taoufiq.util.JsonUtil;

public class Main {
    public static void main(String[] args) {
        List<Apartment> apartments = new ArrayList<>();
        apartments.add(new Apartment("B1102", "The Cameron House", 11, 790, 3));
        apartments.add(new Apartment("A705", "The Cameron House", 7, 855, 4));
        apartments.add(new Apartment("C1210", "Pointe Palace", 12, 1000, 4));
        apartments.add(new Apartment("A1371", "Pointe Palace", 13, 1000, 4));

        List<Lease> leases = List.of(
            new Lease(3128874121L, LocalDate.of(2025, 2, 1), LocalDate.of(2026, 2, 1), 1750.50, "Michael Philips", "A705"),
            new Lease(3128874122L, LocalDate.of(2024, 7, 1), LocalDate.of(2025, 7, 1), 1600.00, "Sarah Johnson", "B1102"),
            new Lease(3128874123L, LocalDate.of(2025, 1, 15), LocalDate.of(2026, 1, 15), 1950.00, "John Smith", "C1210")
        );

        // Attach leases to apartments
        for (Lease lease : leases) {
            apartments.stream()
                .filter(ap -> ap.getApartmentNo().equals(lease.getApartmentNo()))
                .findFirst()
                .ifPresent(ap -> ap.addLease(lease));
        }

        ApartmentService apartmentService = new ApartmentService();
        RevenueService revenueService = new RevenueService();

        List<Apartment> sortedApartments = apartmentService.sortApartments(apartments);

        System.out.println("\nJSON Output of Apartments:");
        System.out.println(JsonUtil.toJson(sortedApartments));

        double totalRevenue = revenueService.calculateTotalRevenue(apartments);
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("\nTotal Revenue: " + format.format(totalRevenue));
    }
}