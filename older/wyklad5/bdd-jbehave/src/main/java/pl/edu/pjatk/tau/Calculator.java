package pl.edu.pjatk.tau;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    List<Double> operands =
     new ArrayList<Double>();
    
    void pushNumber(double arg0) {
        operands.add(arg0);
    }
    void add() {
        double r = operands.get(0) + operands.get(1);
        operands.clear();
        operands.add(r);
    }
    double popResult() {
        double r = operands.get(0);
        operands.clear();
        return r;
    }
}
