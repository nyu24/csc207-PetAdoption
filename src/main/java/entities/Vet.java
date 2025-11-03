package entities;

import java.awt.image.BufferedImage;

public class Vet {
    private String name;
    private BufferedImage vetSprite;
    private int errorMargin;

    public int getErrorMargin() {
        return errorMargin;
    }

    public void setErrorMargin(int errorMargin) {
        this.errorMargin = errorMargin;
    }

    public BufferedImage getVetSprite() {
        return vetSprite;
    }

    public void setVetSprite(BufferedImage vetSprite) {
        this.vetSprite = vetSprite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void checkPetHealth(Pet pet){}

    public void endGameMessage(){}

}
