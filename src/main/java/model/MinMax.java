package model;

import java.io.Serializable;

public class MinMax implements Serializable{
    double min = 0;
    double max = 0;

    public MinMax(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return min + " - " + max;
    }
}
