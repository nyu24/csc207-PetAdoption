package use_case.buttons;
import entities.Pet;

// repository interface for buttons
public interface Repository {
    Pet load();
    void save(Pet pet);
}
