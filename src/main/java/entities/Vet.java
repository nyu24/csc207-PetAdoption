package entities;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vet {

    private final int errorMargin;
    private final int passLevel;

    public Vet(int passLevel, int errorMargin) {
        this.passLevel = passLevel;
        this.errorMargin = errorMargin;
    }

    /**
     * Check if val is within the acceptable range of passLevel +/- errorMargin.
     * @param val the value to check.
     * @return true if val is within the acceptable range, false otherwise.
     */
    public boolean inRange(int val) {

        return Math.abs(val - passLevel) <= errorMargin;
    }

    /**
     * Check the pet stats against the requirements.
     * @param petStats the pet stats to check.
     * @return a list of lists of strings, where each inner list contains the stat name and "PASSED" or "FAILED".
     */
    public List<List<String>> checkRequirements(Map<String, Integer> petStats) {
        List<List<String>> result = new ArrayList<>();
        for (String stat : petStats.keySet()) {
            List<String> row = new ArrayList<>();
            row.add(stat);
            float statValue = petStats.get(stat);
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
