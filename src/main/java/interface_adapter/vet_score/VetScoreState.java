package interface_adapter.vet_score;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class VetScoreState {
    private User user;

    private List<List<String>> requirements =  new ArrayList<>();




    public VetScoreState(VetScoreState copy) {
        this.user = copy.user;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public VetScoreState() {
        //default list for testing
//        List<String> row = new ArrayList<>();
//        row.add("Hunger");
//        row.add("Passed");
//        this.requirements.add(row);
//        row = new ArrayList<>();
//        row.add("Thirst");
//        row.add("Passed");
//        this.requirements.add(row);
//        row = new ArrayList<>();
//        row.add("Cleanliness");
//        row.add("Failed");
//        this.requirements.add(row);
//        row = new ArrayList<>();
//        row.add("Happiness");
//        row.add("Failed");
//        this.requirements.add(row);
    }

    public User getUser() {return this.user;}

    public void setUser(User user) {
        this.user = user;
    }

    public void SetUserWin(boolean win) {
        this.user.setMetRequirements(win);
    }

    public void setMetRequirements(List<List<String>> requirements) {
        this.requirements = requirements;
    }

    public List<List<String>> getRequirements() {return this.requirements;}
}
