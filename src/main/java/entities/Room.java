package entities;

import java.awt.image.BufferedImage;
import java.util.List;

public class Room {

    private List furnitureList;
    private BufferedImage background;

    public List getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(List furnitureList) {
        this.furnitureList = furnitureList;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    private void drawBackground() {}
}
