package entities;

public class User {

    private int points;
    private boolean metRequirements;
    public User() {
        this.points = 0;
        this.metRequirements = false;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setMetRequirements(boolean metRequirements) {
        this.metRequirements = metRequirements;
    }
}
