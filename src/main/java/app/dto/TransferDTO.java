package app.dto;

public class TransferDTO {

    private String senderCardNumber;
    private String recipientCardNumber;
    private double amountForTranster;
    private String senderPass;

    public TransferDTO() {
    }

    public TransferDTO(String senderCardNumber, String recipientCardNumber, double amountForTranster, String senderPass) {
        this.senderCardNumber = senderCardNumber;
        this.recipientCardNumber = recipientCardNumber;
        this.amountForTranster = amountForTranster;
        this.senderPass = senderPass;
    }

    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(String senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    public String getRecipientCardNumber() {
        return recipientCardNumber;
    }

    public void setRecipientCardNumber(String recipientCardNumber) {
        this.recipientCardNumber = recipientCardNumber;
    }

    public double getAmountForTranster() {
        return amountForTranster;
    }

    public void setAmountForTranster(double amountForTranster) {
        this.amountForTranster = amountForTranster;
    }

    public String getSenderPass() {
        return senderPass;
    }

    public void setSenderPass(String senderPass) {
        this.senderPass = senderPass;
    }
}
