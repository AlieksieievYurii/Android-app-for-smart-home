package com.whitedeveloper.controlhome.view.elementarduino;

import com.whitedeveloper.custom.PinArduino;


public class ElementArduino
{
    public static  final char BTN = 'B';
    public static  final char SKB = 'S';

    private PinArduino pinArduino;
    private int id_view;
    private char typeView;
    private String textName;

    public PinArduino getPinArduino() {
        return pinArduino;
    }

    public int getId_view() {
        return id_view;
    }

    public char getTypeView() {
        return typeView;
    }

    public String getTextName() {
        return textName;
    }

    public ElementArduino(char typeView, int id_view, PinArduino pinArduino, String textName) {
        this.pinArduino = pinArduino;
        this.id_view = id_view;
        this.typeView = typeView;
        this.textName = textName;
    }
}
