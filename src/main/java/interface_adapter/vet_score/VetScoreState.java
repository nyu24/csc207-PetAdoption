package interface_adapter.vet_score;

import entities.User;

public class VetScoreState {
    private User user;

    public VetScoreState(VetScoreState copy) {
        this.user = copy.user;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public VetScoreState() {

    }

    public User getUser() {return this.user;}

    public void setUser(User user) {
        this.user = user;
    }

    public void SetUserWin(boolean win) {
        this.user.setMetRequirements(win);
    }
}
