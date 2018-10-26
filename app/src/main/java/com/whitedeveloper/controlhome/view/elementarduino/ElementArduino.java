package com.whitedeveloper.controlhome.view.elementarduino;

import com.whitedeveloper.custom.PinArduino;
import com.whitedeveloper.custom.textview.intervals.Intervals;

public class ElementArduino
{
    public static  final char BTN = 'B';
    public static  final char SKB = 'S';

    private PinArduino pinArduino;
    private int id_view;
    private Intervals intervals;
    private char typeView;
    private String textName;

    public Intervals getIntervals() {
        return intervals;
    }

    public void setIntervals(Intervals intervals) {
        this.intervals = intervals;
    }

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
