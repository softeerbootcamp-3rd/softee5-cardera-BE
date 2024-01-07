package com.warmingup.cardera.dto;

public enum CarpoolType {

    oneWay(1), roundTrip(2);

    private final int numericValue;
    CarpoolType(int numericValue) {
        this.numericValue = numericValue;
    }

    public int getNumericValue() {
        return numericValue;
    }
}
