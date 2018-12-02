package com.whitedeveloper.custom;

public class PinTCOD
{
    public static final String PIN = "P";
    public static final String TYPE_PIN = "T";
    public static final String TYPE_PIN_DIGITAL = "D";
    public static final String TYPE_PIN_DIGITAL_ANALOG = "A";
    public static final String STATUS = "S";
    public static final String STATUS_HIGH = "H";
    public static final String STATUS_LOW = "L";
    public static final String VALUE = "V";

    private String typePin;
    private int pin;
    private int value;

    public String getTypePin() {
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

    public PinTCOD(String typePin, int pin) {
        this.typePin = typePin;
        this.pin = pin;

    }
}
