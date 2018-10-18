package com.whitedeveloper.custom;

public class PinArduino
{
    private char typePin;
    private int pin;
    private int value;

    public char getTypePin() {
        return typePin;
    }

    public int getPin() {
        return pin;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public PinArduino(char typePin, int pin) {
        this.typePin = typePin;
        this.pin = pin;

    }
}
