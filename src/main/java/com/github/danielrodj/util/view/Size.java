package com.github.danielrodj.util.view;

public class Size {

    public static final Size DEFAULT_SIZE = new Size(570, 525, 1000, 640, Double.MAX_VALUE, Double.MAX_VALUE);

    private final double minWidth, minHeight;
    private final double prefWidth, prefHeight;
    private final double maxWidth, maxHeight;

    public Size(double minWidth, double minHeight, double prefWidth, double prefHeight, double maxWidth,
            double maxHeight) {
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public Size(double allWidth, double allHeight){
        this.minWidth = allWidth;
        this.minHeight = allHeight;
        this.prefWidth = allWidth;
        this.prefHeight = allHeight;
        this.maxWidth = allWidth;
        this.maxHeight = allHeight;
    }

    public double getMinWidth() {
        return minWidth;
    }

    public double getMinHeight() {
        return minHeight;
    }

    public double getPrefWidth() {
        return prefWidth;
    }

    public double getPrefHeight() {
        return prefHeight;
    }

    public double getMaxWidth() {
        return maxWidth;
    }

    public double getMaxHeight() {
        return maxHeight;
    }
}