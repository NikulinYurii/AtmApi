package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "bank_cards")
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "card_number", length = 16)
    private String cardNumber;

    @Column(name = "score")
    private double score;

    @Column(name = "card_pass", nullable = false)
    private String pass;

    public BankCard() {
    }

    public BankCard(String cardNumber, double score, String pass, User user) {
        this.cardNumber = cardNumber;
        this.score = score;
        this.pass = pass;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "id=" + id +
                ", user=" + user +
                ", cardNumber='" + cardNumber + '\'' +
                ", score=" + score +
                ", pass='" + pass + '\'' +
                '}';
    }
}
