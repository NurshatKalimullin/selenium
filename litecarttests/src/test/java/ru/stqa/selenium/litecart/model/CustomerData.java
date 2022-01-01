package ru.stqa.selenium.litecart.model;

public class CustomerData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String postCode;
    private final String city;
    private final String email;
    private final String country;
    private final String phoneNumber;
    private final String password;


    public CustomerData(String firstName, String lastName, String address, String postCode, String city,
                        String email, String country, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postCode = postCode;
        this.city = city;
        this.email = email;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


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
}
