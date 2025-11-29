package data_access;

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

        JSONObject petInfo = outerObj.getJSONObject("petInformation");
        String name = petInfo.getString("name");
        String spritePath = petInfo.getString("petSpritePath");
        int hunger = petInfo.getInt("hunger");
        int thirst = petInfo.getInt("thirst");
        int cleanliness = petInfo.getInt("cleanliness");
        int happiness = petInfo.getInt("happiness");
        this.saveFile = new SaveFile(timeLeft, name, spritePath, hunger, thirst, cleanliness, happiness);
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
