package entities;

import java.awt.image.BufferedImage;

public class APIPet {
    //variables TODO: TEMPORARY VALUES WILL ADJUST AS I DEVELOP THE FILTER STUFF whoa
    private int id;
    private String name;
    private String type;
    private String breed;
    private String gender;
    private String description;
    private String location;
    private BufferedImage image;
    private String coat;

    //getters and setters TODO: SOME OF THESE MAY BE REDUNDANT TBH
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCoat() {
        return coat;
    }
    public void setCoat(String coat) {
        this.coat = coat;
    }
}
