package data_access;

import entities.APIPet;
import entities.Pet;
import entities.SaveFile;
import use_case.load_game.LoadGameDataAccessInterface;
import use_case.save_game.SaveGameDataAccessInterface;

import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileSaveDataAccessObject implements SaveGameDataAccessInterface, LoadGameDataAccessInterface {
    private final File jsonFile;
    private SaveFile saveFile;

    public static void main(String[] args) {
        new FileSaveDataAccessObject("savedata.json");
    }

    public FileSaveDataAccessObject(String path) {
        int MAX_STAT_VAL = 100;
        jsonFile = new File(path);
        String saveString = "";
        if (jsonFile.length() == 0) {
            save();

        } else {

                try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {

                    String row;
                    while ((row = reader.readLine()) != null) {
                        saveString += row;
                    }

                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
        }

        JSONArray jsonArray = new JSONArray(saveString);

        JSONObject outerObj = jsonArray.getJSONObject(0);
        int timeLeft = outerObj.getInt("timeLeft");
        int currScore = outerObj.getInt("currScore");

        JSONObject apiPetInfo = outerObj.getJSONObject("petInformation");
        APIPet apiPet = new APIPet();
        currPet.setName(petInfo.getString("name"));
        currPet.setPetSpritePath(petInfo.getString("petSpritePath"));
        currPet.setHunger(petInfo.getInt("hunger"));
        currPet.setThirst(petInfo.getInt("thirst"));
        currPet.setCleanliness(petInfo.getInt("cleanliness"));
        currPet.setHappiness(petInfo.getInt("happiness"));

        JSONObject petInfo = outerObj.getJSONObject("petInformation");
        Pet currPet = new Pet(MAX_STAT_VAL, MAX_STAT_VAL, MAX_STAT_VAL, MAX_STAT_VAL);
        currPet.setName(petInfo.getString("name"));
        currPet.setPetSpritePath(petInfo.getString("petSpritePath"));
        currPet.setHunger(petInfo.getInt("hunger"));
        currPet.setThirst(petInfo.getInt("thirst"));
        currPet.setCleanliness(petInfo.getInt("cleanliness"));
        currPet.setHappiness(petInfo.getInt("happiness"));

        this.saveFile = new SaveFile(timeLeft, currScore, currPet, apiPet);
        }

    public void save() {
        StringBuilder savefile = new StringBuilder();
        savefile.append("[\n");
        savefile.append("  {\n");
        savefile.append("    \"timeLeft\": ").append(saveFile.getTimeLeft()).append(",\n");
        savefile.append("    \"petInformation\": {\n");
        savefile.append("      \"name\": \"").append(saveFile.getName()).append("\",\n");
        savefile.append("      \"petSpritePath\": \"").append(saveFile.getSpritePath()).append("\",\n");
        savefile.append("      \"hunger\": ").append(saveFile.getHunger()).append(",\n");
        savefile.append("      \"thirst\": ").append(saveFile.getThirst()).append(",\n");
        savefile.append("      \"cleanliness\": ").append(saveFile.getCleanliness()).append(",\n");
        savefile.append("      \"happiness\": ").append(saveFile.getHappiness()).append("\n");
        savefile.append("    }\n");
        savefile.append("  }\n");
        savefile.append("]");

        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(jsonFile));
            writer.write(savefile.toString());


            writer.close();

        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void save(SaveFile saveFile) {
        this.saveFile = saveFile;
        this.save();
    }

    public SaveFile load() {
        return this.saveFile;
    }

    @Override
    public boolean saveDataExists() {
        return jsonFile.length() != 0;
    }
}
