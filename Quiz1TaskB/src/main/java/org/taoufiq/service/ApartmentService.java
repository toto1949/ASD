package org.taoufiq.service;



import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.taoufiq.Model.Apartment;

public class ApartmentService {
    public List<Apartment> sortApartments(List<Apartment> apartments) {
        return apartments.stream()
                .sorted(Comparator.comparingInt(Apartment::getSize).reversed()
                        .thenComparing(Apartment::getApartmentNo))
                .collect(Collectors.toList());
    }
}
