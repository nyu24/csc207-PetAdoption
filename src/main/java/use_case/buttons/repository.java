package use_case.buttons;
import main.classes.entities.*;

// repository interface for buttons
public interface repository {
    Pet load();
    void save(Pet pet);
}
