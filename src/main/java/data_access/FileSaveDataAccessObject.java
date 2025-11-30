package data_access;

import entities.APIPet;
import entities.Pet;
import entities.SaveFile;
import org.jetbrains.annotations.NotNull;
import use_case.load_game.LoadGameDataAccessInterface;
import use_case.save_game.SaveGameDataAccessInterface;

import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileSaveDataAccessObject implements SaveGameDataAccessInterface, LoadGameDataAccessInterface {
    private final File jsonFile;
    private SaveFile saveFile;

    public FileSaveDataAccessObject(String path) {

        jsonFile = new File(path);

        if (jsonFile.length() != 0) {
            int MAX_STAT_VAL = 100;
            String saveString = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {

                String row;
                while ((row = reader.readLine()) != null) {
                    saveString += row;
                }

            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            JSONArray jsonArray = new JSONArray(saveString);

            JSONObject outerObj = jsonArray.getJSONObject(0);
            int timeLeft = outerObj.getInt("timeLeft");
            int currScore = outerObj.getInt("currScore");

            JSONObject apiPetInfo = outerObj.getJSONObject("apiPetInfo");
            APIPet apiPet = getApiPet(apiPetInfo);

            JSONObject petInfo = outerObj.getJSONObject("petInformation");
            Pet currPet = getCurrPet(MAX_STAT_VAL, petInfo);
            currPet.setApiPet(apiPet);

            this.saveFile = new SaveFile(timeLeft, currScore, currPet, apiPet);

        }

        }

    @NotNull
    private static Pet getCurrPet(int maxStatValue, JSONObject petInfo) {
        Pet currPet = new Pet(maxStatValue, maxStatValue, maxStatValue, maxStatValue);
        currPet.setName(petInfo.getString("name"));
        currPet.setPetSpritePath(petInfo.getString("petSpritePath"));
        currPet.setHunger(petInfo.getInt("hunger"));
        currPet.setThirst(petInfo.getInt("thirst"));
        currPet.setCleanliness(petInfo.getInt("cleanliness"));
        currPet.setHappiness(petInfo.getInt("happiness"));
        return currPet;
    }

    @NotNull
    private static APIPet getApiPet(JSONObject apiPetInfo) {
        APIPet apiPet = new APIPet();
        apiPet.setName(apiPetInfo.getString("name"));
        apiPet.setImage(apiPetInfo.getString("image"));
        apiPet.setUrl(apiPetInfo.getString("url"));
        apiPet.setType(apiPetInfo.getString("type"));
        apiPet.setBreed(apiPetInfo.getString("breed"));
        apiPet.setGender(apiPetInfo.getString("gender"));
        apiPet.setDescription(apiPetInfo.getString("description"));
        apiPet.setCoat(apiPetInfo.getString("coat"));
        apiPet.setColour(apiPetInfo.getString("colour"));
        return apiPet;
    }

    public void save() {
        StringBuilder savefile = new StringBuilder();
        Pet currPet = this.saveFile.getCurrPet();
        APIPet apiPet = this.saveFile.getApiPet();
        savefile.append("[\n");
        savefile.append("  {\n");
        savefile.append("    \"timeLeft\": ").append(saveFile.getTimeLeft()).append(",\n");
        savefile.append("    \"currScore\": ").append(saveFile.getCurrScore()).append(",\n");
        savefile.append("    \"apiPetInfo\": {\n");
        savefile.append("      \"name\": \"").append(apiPet.getName()).append("\",\n");
        savefile.append("      \"image\": \"").append(apiPet.getImage()).append("\",\n");
        savefile.append("      \"url\": \"").append(apiPet.getUrl()).append("\",\n");
        savefile.append("      \"type\": \"").append(apiPet.getType()).append("\",\n");
        savefile.append("      \"breed\": \"").append(apiPet.getBreed()).append("\",\n");
        savefile.append("      \"gender\": \"").append(apiPet.getGender()).append("\",\n");
        savefile.append("      \"description\": \"").append(apiPet.getDescription()).append("\",\n");
        savefile.append("      \"coat\": \"").append(apiPet.getCoat()).append("\",\n");
        savefile.append("      \"colour\": \"").append(apiPet.getColour()).append("\"\n");
        savefile.append("    },\n");
        savefile.append("    \"petInformation\": {\n");
        savefile.append("      \"name\": \"").append(currPet.getName()).append("\",\n");
        savefile.append("      \"petSpritePath\": \"").append(currPet.getPetSpritePath()).append("\",\n");
        savefile.append("      \"hunger\": ").append(currPet.getHunger()).append(",\n");
        savefile.append("      \"thirst\": ").append(currPet.getThirst()).append(",\n");
        savefile.append("      \"cleanliness\": ").append(currPet.getCleanliness()).append(",\n");
        savefile.append("      \"happiness\": ").append(currPet.getHappiness()).append("\n");
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
