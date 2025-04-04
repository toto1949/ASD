package org.taoufiq.Model;


import java.util.ArrayList;
import java.util.List;

public class Apartment {
    private String apartmentNo;
    private String propertyName;
    private int floorNo;
    private int size;
    private int numberOfRooms;
    private List<Lease> leases = new ArrayList<>();

    public Apartment(String apartmentNo, String propertyName, int floorNo, int size, int numberOfRooms) {
        this.apartmentNo = apartmentNo;
        this.propertyName = propertyName;
        this.floorNo = floorNo;
        this.size = size;
        this.numberOfRooms = numberOfRooms;
    }

    // Getters and Setters
    public String getApartmentNo() { return apartmentNo; }
    public String getPropertyName() { return propertyName; }
    public int getFloorNo() { return floorNo; }
    public int getSize() { return size; }
    public int getNumberOfRooms() { return numberOfRooms; }
    public List<Lease> getLeases() { return leases; }
    public void addLease(Lease lease) { leases.add(lease); }
}
