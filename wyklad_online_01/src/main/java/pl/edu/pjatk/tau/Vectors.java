package pl.edu.pjatk.tau;

import java.util.List;

public class Vectors {
    public double calculateLength(List<Double> v) {
        double sum = 0.0;
        for (Double x : v) sum += x*x;
        return Math.sqrt(sum);
    }
}