package entities;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

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

    private int getPoints(Pet pet){
        int points = 0;
        List<Float> petStats = pet.getPetStats();
        for (float stat: petStats)
        {
            if (stat <= this.errorMargin)
            {
                points += 100;
            }
        }
        return points;
    }

    public boolean checkWin(Pet pet)
    {
        int points = this.getPoints(pet);
        if (points >= 200)
        {
            return true;
        }
        return false;
    }

    public void endGameMessage(){}

}
