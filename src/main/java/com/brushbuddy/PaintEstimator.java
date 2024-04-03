package com.brushbuddy;

public class PaintEstimator {

    public static int calculatePaintCansRequired(double totalPaintableArea, double coveragePerCan) {
        int cansRequired = (int) Math.ceil(totalPaintableArea / coveragePerCan);
        return cansRequired;
    }
}

