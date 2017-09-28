package demo.atm.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Card {
    public static final int MAX_NUMBER_OF_PIN_TRIES = 4;

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private String pinCode;
    private String pinCodeSalt;
    private boolean blocked;
    private boolean deleted;
    private int pinTries = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getPinCodeSalt() {
        return pinCodeSalt;
    }

    public void setPinCodeSalt(String pinCodeSalt) {
        this.pinCodeSalt = pinCodeSalt;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getPinTries() {
        return pinTries;
    }

    public void setPinTries(int pinTries) {
        this.pinTries = pinTries;
    }
}
