package app.dto;

public class AuthenticationBankCardDTO {

    private String cardNumber;
    private String userPass;

    public AuthenticationBankCardDTO() {
    }

    public AuthenticationBankCardDTO(String cardNumber, String userPass) {
        this.cardNumber = cardNumber;
        this.userPass = userPass;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
