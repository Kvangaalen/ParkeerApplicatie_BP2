package com.mycompany.parkeerapplictie.model;

public class parkeergarages {

    // static
    public String identifier;
    private String name;
    private int totaalParkingCapacity, chargingPointCapacity;
    private double latitude, longitude;
    private double minimumHeightInMeters;
    // dynamic
    private String identifie;
    private boolean full;
    private boolean open;
    private Long vacantSpacesl;

    public parkeergarages(String identifier, String name, int totaalParkingCapacity, double latitude, double longitude, int chargingPointCapacity, double minimumHeightInMeters, boolean full, boolean open, Long vacantSpacesl) {
        this.identifier = identifier;
        this.name = name;
        this.totaalParkingCapacity = totaalParkingCapacity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.chargingPointCapacity = chargingPointCapacity;
        this.minimumHeightInMeters = minimumHeightInMeters;
        this.open = open;
        this.full = full;
        this.vacantSpacesl = vacantSpacesl;
    }
    public parkeergarages(boolean blOpen, boolean blFull, Long intVacant) {
        this.open = blOpen;
        this.full = blFull;
        this.vacantSpacesl = intVacant;
    }
    public parkeergarages(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

    }

    // static
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getTotaalParkingCapacity() {
        return totaalParkingCapacity;
    }

    public void setTotaalParkingCapacity(int totaalParkingCapacity) {
        this.totaalParkingCapacity = totaalParkingCapacity;
    }

    public int getChargingPointCapacity() {
        return chargingPointCapacity;
    }

    public void setChargingPointCapacity(int chargingPointCapacity) {
        this.chargingPointCapacity = chargingPointCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getMinimumHeightInMeters() {
        return minimumHeightInMeters;
    }

    public void setMinimumHeightInMeters(double minimumHeightInMeters) {
        this.minimumHeightInMeters = minimumHeightInMeters;
    }

    // dynamic
    public String getIdentifie() {
        return identifie;
    }

    public void setIdentifie(String identifie) {
        this.identifie = identifie;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Long getVacantSpacesl() {
        return vacantSpacesl;
    }

    public void setVacantSpacesl(Long vacantSpacesl) {
        this.vacantSpacesl = vacantSpacesl;
    }

}
