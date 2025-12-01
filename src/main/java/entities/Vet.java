package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vet {
    private int errorMargin;
    private int passLevel;

    public Vet(int passLevel, int errorMargin) {
        this.passLevel = passLevel;
        this.errorMargin = errorMargin;
    }

    /**
     * Check if a value is within the acceptable range.
     * @param val the value to check.
     * @return boolean.
     */
    public boolean inRange(int val) {
        return Math.abs(val - passLevel) <= errorMargin;
    }

    /**
     * Check the pet stats against the requirements.
     * @param petStats the pet stats.
     * @return a list of lists containing the stat name and whether it passed or failed.
     */
    public List<List<String>> checkRequirements(Map<String, Integer> petStats) {
        final List<List<String>> result = new ArrayList<>();
        for (String stat : petStats.keySet()) {
            final List<String> row = new ArrayList<>();
            row.add(stat);
            final float statValue = petStats.get(stat);
            if (Math.abs(statValue - passLevel) <= errorMargin) {
                row.add("PASSED");
            }
            else {
                row.add("FAILED");
            }

            result.add(row);

        }
        return result;
    }

}
