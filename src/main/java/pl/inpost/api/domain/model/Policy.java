package pl.inpost.api.domain.model;

public enum Policy {

    AMOUNT_BASED("AMOUNT-BASED"),
    PERCENTAGE_BASED("PERCENTAGE-BASED");

    private String value;

    Policy(String value) {
        this.value = value;
    }
}

