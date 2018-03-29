package app.dto;

import app.model.Address;

public class CreateBankCardDTO {

    private String userName;
    private String userSurname;
    private String cardNumber;
    private String cardPass;
    private String userBirthday;
    private String sex;
    private Address address;

    public CreateBankCardDTO() {
    }

    public CreateBankCardDTO(String userName, String userSurname, String cardNumber, String cardPass, String userBirthday, String sex, Address address) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.cardNumber = cardNumber;
        this.cardPass = cardPass;
        this.userBirthday = userBirthday;
        this.sex = sex;
        this.address = address;
    }

    public String getCardPass() {
        return cardPass;
    }

    public void setCardPass(String cardPass) {
        this.cardPass = cardPass;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
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
