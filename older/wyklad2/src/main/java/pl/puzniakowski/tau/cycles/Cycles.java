package pl.puzniakowski.tau.cycles;

import java.util.ArrayList;
import java.util.List;

public class Cycles {
    // {1,0,0}
    public static int maxCycleSize(List<Integer> l) {
        int maxCycle = 0;
        for (int i = 0; i < l.size(); i++) {
            List<Boolean> visited = new ArrayList<Boolean>(l.size());
            for (int j = 0; j < l.size(); j++) visited.add(false);
            int e = i;
            int n = 0;
            while (!visited.get(e)) {
                visited.set(e, true);
                e = l.get(e);
                n ++;
            }
            if (n > maxCycle) maxCycle = n;
        }
        return maxCycle;
    }
}