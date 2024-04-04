package com.brushbuddy;

public class PaintEstimator {

    public static int calculatePaintCansRequired(double totalPaintableArea, double coveragePerCan) {
        if (coveragePerCan <= 0) {
            throw new IllegalArgumentException("Coverage per can must be positive.");
        }

        int cansRequired = (int) Math.ceil(totalPaintableArea / coveragePerCan);
        return cansRequired;
    }
}

