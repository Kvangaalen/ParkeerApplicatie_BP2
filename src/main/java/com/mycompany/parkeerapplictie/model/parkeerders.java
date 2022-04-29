package com.mycompany.parkeerapplictie.model;

public class parkeerders {

    public String naam;
    public String email;

    public parkeerders(String naam, String email) {
        this.naam = naam;
        this.email = email;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
