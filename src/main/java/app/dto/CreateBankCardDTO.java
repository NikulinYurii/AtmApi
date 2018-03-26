package app.dto;

import app.model.Address;

public class CreateBankCardDTO {

    private String user_name;
    private String user_surname;
    private String card_number;
    private String card_pass;
    private String user_birthday;
    private String sex;
    private Address address;

    public CreateBankCardDTO() {
    }

    public CreateBankCardDTO(String user_name, String user_surname, String card_number, String card_pass, String user_birthday, String sex, Address address) {
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.card_number = card_number;
        this.card_pass = card_pass;
        this.user_birthday = user_birthday;
        this.sex = sex;
        this.address = address;
    }

    public String getCard_pass() {
        return card_pass;
    }

    public void setCard_pass(String card_pass) {
        this.card_pass = card_pass;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
