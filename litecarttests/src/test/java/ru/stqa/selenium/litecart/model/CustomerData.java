package ru.stqa.selenium.litecart.model;

public class CustomerData {
    private String firstName;
    private String lastName;
    private String address;
    private String postCode;
    private String city;
    private String email;
    private String country;
    private String phoneNumber;
    private String password;



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }


    public CustomerData withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public CustomerData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerData withAddress(String address) {
        this.address = address;
        return this;
    }

    public CustomerData withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public CustomerData withCity(String city) {
        CustomerData.this.city = city;
        return this;
    }

    public CustomerData withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerData withCountry(String country) {
        this.country = country;
        return this;
    }

    public CustomerData withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CustomerData withPassword(String password) {
        this.password = password;
        return this;

    }
}
