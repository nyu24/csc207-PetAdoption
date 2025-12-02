package use_case.buttons;
import entities.Pet;

// repository interface for buttons
public interface ButtonsRepository {
    Pet load();
    void save(Pet pet);
}
